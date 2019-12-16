import java.awt.Point;
import java.util.ArrayList;

public class Flip {
	private Board board;					// オセロ盤の状態
	private char nextColor;					// 置こうとしている石の色
	private Point nextMove;					// 石を置こうとしているマス

	public Flip(Board board, char nextColor, Point nextMove) {
		this.board = board;
		this.nextColor = nextColor;
		this.nextMove = nextMove;
	}

	// 指定されたマスが相手の石をひっくり返せるマスかどうか判定する
	public boolean isAvailableSquare() {
		// すでに石が置かれていないかどうか調べる
		if (!this.isEmptySquare(this.nextMove)) {
			return false;
		}
		// 各方向にひっくり返せる石があるかどうか調べる
		for (Point direction : Directions.directions) {
			if (this.searchFlippedDiscs(this.nextMove, direction).size() > 0) {
				// ひっくり返せる石があった場合
				return true;
			}
		}
		// どの方向にもひっくり返せる石がなかった場合
		return false;
	}

	// ひっくり返される石の座標の一覧を返す
	public ArrayList<Point> getAllFlippedDiscs() {
		ArrayList<Point> allFlippedDiscs = new ArrayList<Point>();
		for (Point direction : Directions.directions) {
			allFlippedDiscs.addAll(this.searchFlippedDiscs(this.nextMove, direction));
		}
		return allFlippedDiscs;
	}

	// 指定されたマスに石がないかどうか判定する
	private boolean isEmptySquare(Point square) {
		if (this.board.getSquareState(square) == DiscState.NONE) {
			return true;
		} else {
			return false;
		}
	}

	// 縦・横・斜めのうち1方向に対し、ひっくり返せる石の座標の一覧を取得する
	// 相手の石が連続する間は一覧に仮登録し続け、その直後に自分の石がきたらその一覧を返す
	// しかし、隣にマスがない(盤の外)または石がない場合は、一覧を全消去して返す(＝ひっくり返せない)
	private ArrayList<Point> searchFlippedDiscs(Point square, Point direction) {
		Point currentSquare = new Point(square);
		ArrayList<Point> flippedDiscs = new ArrayList<Point>();

		while(true) {
			// 隣のマスの座標を求める
			Point nextSquare = this.getNextSquare(currentSquare, direction);
			// 隣のマスの状況によりループを抜ける場合
			if (!this.isSquareInRange(nextSquare)) {
				// 隣にマスがない場合
				flippedDiscs.clear();
				break;
			} else if (board.getSquareState(nextSquare) == DiscState.NONE) {
				// 隣のマスに石がない場合
				flippedDiscs.clear();
				break;
			} else if (board.getSquareState(nextSquare) == this.nextColor) {
				// 隣のマスに自分の石がある場合
				break;
			}
			// 隣のマスに相手の石がある場合は、さらに隣のマスに進む
			flippedDiscs.add(nextSquare);
			currentSquare.setLocation(nextSquare);
		}
		return flippedDiscs;
	}

	// 指定された向きに関して隣のマスの座標を求める
	private Point getNextSquare(Point currentSquare, Point direction) {
		Point nextSquare = new Point(currentSquare.x, currentSquare.y);
		nextSquare.translate(direction.x, direction.y);
		return nextSquare;
	}

	// 指定されたマスがオセロ盤の中にあるかどうか調べる
	private boolean isSquareInRange(Point square) {
		if (0 <= square.x && square.x < this.board.getSize() &&
			0 <= square.y && square.y < this.board.getSize()) {
			return true;
		} else {
			return false;
		}
	}
}
