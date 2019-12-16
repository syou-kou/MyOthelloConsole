import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Strategy {
	private Config config;						// 初期設定
	private Board board;						// オセロ盤の状態
	private char nextColor;						// 置こうとしている石の色
	ArrayList<Candidate> candidates;			// 石を置ける(＝相手の石をひっくり返せる)マスの一覧

	public Strategy(Config config, Board board, char nextColor) {
		this.config = config;
		this.board = board;
		this.nextColor = nextColor;
		this.candidates = new ArrayList<Candidate>();
	}

	// 次に石を置けるマスがあるかどうかを判定する
	public boolean hasCandidates() {
		this.searchCandidates();
		if (this.candidates.size() > 0) {
			return true;
		} else {
			return false;
		}
	}


	// 次に石を置くべきマスを1つ選ぶ
	public Candidate getNextMove() {
		return this.getNextMoveRandom();
	}

	// 次に石を置くべきマスをランダムに1つ選ぶ
	private Candidate getNextMoveRandom() {
		return this.candidates.get(new Random().nextInt(this.candidates.size()));
	}

	// 石を置ける(＝相手の石をひっくり返せる)マスを探索する
	private void searchCandidates() {
		for (int y = 0; y < this.board.getSize(); y ++) {
			for (int x = 0; x < this.board.getSize(); x ++) {
				Point currentSquare = new Point(x, y);
				Flip flip = new Flip(this.board, this.nextColor, currentSquare);
				if (flip.isAvailableSquare()) {
					this.candidates.add(new Candidate(currentSquare, flip.getAllFlippedDiscs()));
				}
			}
		}
	}
}
