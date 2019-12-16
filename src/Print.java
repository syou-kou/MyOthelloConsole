import java.awt.Point;
import java.util.ArrayList;

public class Print {
	private Board board;										// オセロ盤の状態
	private final String alphabets = "abcdefghijklmnop";		// 横方向の座標を示すアルファベット

	public Print(Board board) {
		this.board = board;
	}

	// オセロ盤をコンソール上に表示する
	public void printBoard() {
		this.printBoardAlphabetLine();							// アルファベット行
		this.printBoardOtherLine("┏", "┳", "┓");				// 上端
		for (int y = 0; y < this.board.getSize() - 1; y ++) {
			this.printBoardDiscLine(y);							// 石を表示する行
			this.printBoardOtherLine("┣", "╋", "┫");			// 行間の枠
		}
		this.printBoardDiscLine(this.board.getSize() - 1);		// 石を表示する行
		this.printBoardOtherLine("┗", "┻", "┛");				// 下端
	}

	// プレイヤーと相手の石の数を表示する
	public void printDiscNumber(char playerColor) {
		if (playerColor == DiscState.BLACK) {
			System.out.print("あなた = " + this.board.getCounter(DiscState.BLACK) + "  ");
			System.out.println("相手 = " + this.board.getCounter(DiscState.WHITE));
		} else if (playerColor == DiscState.WHITE) {
			System.out.print("あなた = " + this.board.getCounter(DiscState.WHITE) + "  ");
			System.out.println("相手 = " + this.board.getCounter(DiscState.BLACK));
		}
	}

	// ひっくり返した石の座標をすべて表示する
	public void printAllFlippedDiscs(ArrayList<Point> discs) {
		System.out.println("次の石をひっくり返しました。");
		int count = 0;
		for (Point disc : discs) {
			System.out.print(alphabets.substring(disc.x, disc.x + 1) + (disc.y + 1) + " ");
			count ++;
			if (count == 8) {
				System.out.println("");
				count = 0;
			}
		}
		System.out.println("");
	}

	// オセロ盤の列を示すアルファベットを表示する
	private void printBoardAlphabetLine() {
		String buf = "  ";
		for (int x = 0; x < this.board.getSize(); x ++) {
			buf += "   " + this.alphabets.charAt(x);
		}
		System.out.println(buf);
	}

	// オセロ盤の石がある行を1行分表示する
	private void printBoardDiscLine(int y) {
		String buf = String.format("%2d┃", y+1);
		for (int x = 0; x < this.board.getSize(); x ++) {
			if (this.board.getSquareState(new Point(x, y)) == DiscState.BLACK) {
				buf += "●┃";
			} else if (this.board.getSquareState(new Point(x, y)) == DiscState.WHITE) {
				buf += "○┃";
			} else {
				buf += "　┃";
			}
		}
		System.out.println(buf);
	}

	// オセロ盤の枠を表す罫線を1行分表示する
	private void printBoardOtherLine(String left, String middle, String right) {
		String buf = "  " + left;
		for (int x = 0; x < this.board.getSize() - 1; x ++) {
			buf += "━" + middle;
		}
		System.out.println(buf + "━" + right);
	}
}
