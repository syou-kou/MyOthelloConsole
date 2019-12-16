import java.util.Scanner;

public class Config {
	private final int size;				// オセロ盤の一辺(8, 10, 12, 14, 16)
	private final char playerColor;		// プレイヤーの石の色
	private final char otherColor;		// 相手の石の色

	public Config() {
		this.size = this.askBoardSize();
		this.playerColor = this.askPlayerColor();
		if (this.playerColor == DiscState.BLACK) {
			this.otherColor = DiscState.WHITE;
		} else {
			this.otherColor = DiscState.BLACK;
		}
	}

	public int getSize() {
		return this.size;
	}
	public char getPlayerColor() {
		return this.playerColor;
	}
	public char getOtherColor() {
		return this.otherColor;
	}

	// オセロ盤のサイズが決まるまで入力を受け付ける
	private int askBoardSize() {
		while (true) {
			System.out.println("\nオセロ盤の一辺の長さを決めてください。");
			System.out.print("[6, 8, 10, 12, 14, 16 のいずれか]：");
			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine();
			if ("6".equals(line) || "8".equals(line) || "10".equals(line) || "12".equals(line) ||
					"14".equals(line) || "16".equals(line)) {
				System.out.println("オセロ盤の一辺の長さは" + line + "です。");
				return Integer.parseInt(line);
			}
			System.out.println("入力が間違っています。");
		}
	}

	// プレイヤーの石の色が決まるまで入力を受け付ける
	private char askPlayerColor() {
		while (true) {
			System.out.println("\nあなたの石を決めてください。");
			System.out.println("[b (黒), w (白) のいずれか]：");
			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine();
			if ("b".equals(line)) {
				System.out.println("あなたの石は黒です。");
				return DiscState.BLACK;
			} else if ("w".equals(line)) {
				System.out.println("あなたの石は白です。");
				return DiscState.WHITE;
			}
			System.out.println("入力が間違っています。");
		}
	}
}
