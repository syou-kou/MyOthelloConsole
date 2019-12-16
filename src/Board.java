import java.awt.Point;
import java.util.ArrayList;

public class Board {
	private final int size;					// オセロ盤の一辺(8, 10, 12, 14, 16)
	private char[][] squares;				// 各マスの石の有無、石がある場合は色を表す
	private int blackCounter;				// 黒石の個数
	private int whiteCounter;				// 白石の個数

	public Board(int size) {
		this.size = size;
		this.squares = new char[this.size][this.size];
		this.blackCounter = 0;
		this.whiteCounter = 0;
	}

	public int getSize() {
		return this.size;
	}
	public char[][] getSquares() {
		return this.squares;
	}
	public char getSquareState(Point p) {
		return this.squares[p.y][p.x];
	}
	public int getCounter(char color) {
		if (color == DiscState.BLACK) {
			return this.blackCounter;
		} else if (color == DiscState.WHITE) {
			return this.whiteCounter;
		} else {
			return this.size*this.size - this.blackCounter - this.whiteCounter;
		}
	}

	// オセロ盤をゲーム開始時の状態にする
	public void initializeBoard() {
		for (int y = 0; y < this.size; y ++) {
			for (int x = 0; x < this.size; x ++) {
				squares[y][x] = 'N';
			}
		}
		// 中央4マスだけに石を置く
		this.putDisc(DiscState.BLACK, new Point(this.size/2 - 1, this.size/2 - 1));
		this.putDisc(DiscState.BLACK, new Point(this.size/2, this.size/2));
		this.putDisc(DiscState.WHITE, new Point(this.size/2, this.size/2 - 1));
		this.putDisc(DiscState.WHITE, new Point(this.size/2 - 1, this.size/2));
	}

	// オセロ盤の指定された座標に石を置き、同時に石の個数を更新する
	public void putDisc(char color, Point p) {
		this.squares[p.y][p.x] = color;
		if (color == DiscState.BLACK) {
			this.blackCounter ++;
		} else if (color == DiscState.WHITE) {
			this.whiteCounter ++;
		}
	}

	// オセロ盤の指定された座標の石をひっくり返し、同時に石の個数を更新する
	public void flipAllDiscs(ArrayList<Point> discs) {
		for (Point disc : discs) {
			this.flipDisc(disc);
		}
	}

	// オセロ盤の指定された座標の石をひっくり返し、同時に石の個数を更新する
	private void flipDisc(Point p) {
		if (this.squares[p.y][p.x] == DiscState.BLACK) {
			this.squares[p.y][p.x] = DiscState.WHITE;
			this.blackCounter --;
			this.whiteCounter ++;
		} else if (this.squares[p.y][p.x] == DiscState.WHITE) {
			this.squares[p.y][p.x] = DiscState.BLACK;
			this.blackCounter ++;
			this.whiteCounter --;
		}
	}
}
