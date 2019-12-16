public class OthelloBoard {
	private Config config;										// 初期設定
	private Board board;										// オセロ盤の状態
	private int turnCountMax;									// ターン数の最大値(1辺*1辺-4)

	private int turnCounter;									// 現在ターン数
	private int skipCounter;									// 連続スキップ回数(2になればオセロ終了)
	private boolean isPlayerTurn;								// 現在ターンがプレイヤーの番ならばtrue
	private char nextColor;										// 現在のターンがどちらの色の手番か

	// コンストラクタ
	public OthelloBoard() {
		System.out.println("オセロを始めます。");
		// 初期設定
		this.config = new Config();
		this.board = new Board(this.config.getSize());
		this.board.initializeBoard();
		this.turnCountMax = this.config.getSize()*this.config.getSize() - 4;
		Print print = new Print(this.board);
		print.printBoard();
		print.printDiscNumber(this.config.getPlayerColor());

		// 第1ターンのみの処理
		this.turnCounter = 1;
		this.skipCounter = 0;
		this.isPlayerTurn = this.getFirstMove();
		this.nextColor = this.getNextColor();
	}

	// オセロを開始する
	public void start() {
		// 毎ターンの処理
		while (this.turnCounter <= this.turnCountMax) {
			// ターンをスキップするかどうか判定する
			Strategy strategy = new Strategy(this.config, this.board, this.nextColor);
			if (!strategy.hasCandidates()) {
				// 現在のターンを敵側にゆずる
				System.out.println("ターンがスキップされました。");
				this.skipCounter ++;
				if (this.skipCounter == 2) {
					System.out.println("ターンが連続でスキップされたため、オセロを終了します。");
					break;
				}
				this.isPlayerTurn = !this.isPlayerTurn;
				this.nextColor = this.getNextColor();
				continue;
			}
			// 以下、ターンをスキップしない場合
			// 次に石を置く場所を決める
			this.skipCounter = 0;
			Candidate nextMove;
			if (this.isPlayerTurn) {
				// プレイヤーのターン
				System.out.println("\nTurn " + this.turnCounter + ":あなたのターンです。");
				Player player = new Player(this.board, this.nextColor);
				nextMove = player.askNextMove();
			} else {
				// 相手のターン
				System.out.println("\nTurn " + this.turnCounter + ":相手のターンです。");
				nextMove = strategy.getNextMove();
			}
			// ひっくり返した後の盤面を表示する
			this.board.putDisc(this.nextColor, nextMove);
			this.board.flipAllDiscs(nextMove.getAllFlippedDiscs());
			Print print = new Print(this.board);
			print.printBoard();
			print.printDiscNumber(this.config.getPlayerColor());
			print.printAllFlippedDiscs(nextMove.getAllFlippedDiscs());
			// 次ターンのための処理
			this.turnCounter ++;
			this.isPlayerTurn = !this.isPlayerTurn;
			if (this.isPlayerTurn) {
				this.nextColor = this.config.getPlayerColor();
			} else {
				this.nextColor = this.config.getOtherColor();
			}
		}
		// 勝敗の判定
		this.printResult();
	}

	// ゲームの勝敗を表示する
	private void printResult() {
		if (this.board.getCounter(DiscState.BLACK) > this.board.getCounter(DiscState.WHITE)) {
			System.out.println("黒石の勝ちです。");
		} else {
			System.out.println("白石の勝ちです。");
		}
	}

	// 現在のターンがどちらの色の手番か判定する
	private char getNextColor() {
		if (this.isPlayerTurn) {
			return this.config.getPlayerColor();
		} else {
			return this.config.getOtherColor();
		}
	}

	// 先手がどちらかを決める
	// プレイヤーが黒石ならプレイヤーが先手、白石なら相手が先手となる
	private boolean getFirstMove() {
		if (this.config.getPlayerColor() == DiscState.BLACK) {
			return true;
		} else {
			return false;
		}
	}
}
