import java.awt.Point;
import java.util.Scanner;

public class Player {
	private Board board;									// オセロ盤の状態
	private char nextColor;								// 置こうとしている石の色

	private Candidate nextMove;							// 次に石を置くマス
	private Flip flip;										// ひっくり返せる石の情報

	private final String alphabets = "abcdefghijklmnop";	// 横方向の座標を示すアルファベット

	public Player(Board board, char nextColor) {
		this.board = board;
		this.nextColor = nextColor;
		this.nextMove = new Candidate(new Point(0, 0));
	}

	// 次に石を置く場所が決まるまで入力を受け付ける
	public Candidate askNextMove() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			// 入力
			System.out.println("\n石を置く場所を決めてください。");
			System.out.print("[x座標 y座標](例 a 1)：");
			String line = sc.nextLine();
			// プレイヤーの入力した座標がオセロ盤の範囲内かどうか判定する
			if (!this.checkCoordinatesRange(line)) {
				// 座標が正しくない場合、再度入力させる
				System.out.println("入力が間違っています。");
				continue;
			}
			// 石を置ける(＝相手の石をひっくり返せる)マスかどうか判定する
			this.flip = new Flip(this.board, this.nextColor, this.nextMove);
			if (!this.flip.isAvailableSquare()) {
				System.out.println("そのマスに石を置くことはできません。");
				continue;
			}
			this.nextMove.setAllFlippedDiscs(this.flip.getAllFlippedDiscs());
			return this.nextMove;
		}
	}

	// プレイヤーの入力した座標がオセロ盤の範囲内かどうか判定する
	private boolean checkCoordinatesRange(String line) {
		String[] tokens = line.split(" ");
		// 1文字目のアルファベットから横の座標を読み取る
		int x = this.alphabets.indexOf(tokens[0]);
		if (tokens[0].length() != 1 || x < 0 || x >= this.board.getSize()) {
			return false;
		}
		// 残りの文字から縦の座標を読み取る
		int y;
		try {
			y = Integer.parseInt(tokens[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		if (y <= 0 || y > this.board.getSize()) {
			return false;
		}

		this.nextMove.setLocation(x, y - 1);
		return true;
	}
}
