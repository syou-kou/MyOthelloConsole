import java.awt.Point;
import java.util.ArrayList;

public class Directions {
	public static final ArrayList<Point> directions;

	static {
		directions = new ArrayList<Point>();
		directions.add(new Point(1, 0));		//   0度
		directions.add(new Point(1, 1));		//  45度
		directions.add(new Point(0, 1));		//  90度
		directions.add(new Point(-1, 1));		// 135度
		directions.add(new Point(-1, 0));		// 180度
		directions.add(new Point(-1, -1));		// 225度
		directions.add(new Point(0, -1));		// 270度
		directions.add(new Point(1, -1));		// 315度
	}
}
