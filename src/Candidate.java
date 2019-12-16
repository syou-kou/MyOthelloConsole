import java.awt.Point;
import java.util.ArrayList;

public class Candidate extends Point {
	private ArrayList<Point> allFlippedDiscs;

	public Candidate(Point point) {
		super(point);
	}
	public Candidate(Point point, ArrayList<Point> allFlippedDiscs) {
		super(point);
		this.allFlippedDiscs = allFlippedDiscs;
	}
	public void setAllFlippedDiscs(ArrayList<Point> allFlippedDiscs) {
		this.allFlippedDiscs = allFlippedDiscs;
	}
	public ArrayList<Point> getAllFlippedDiscs() {
		return this.allFlippedDiscs;
	}
}
