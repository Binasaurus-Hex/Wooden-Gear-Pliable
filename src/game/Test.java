package game;

import java.awt.geom.Point2D;

import Physics.MathsMethods;

public class Test {

	public static void main(String[] args) {
		Point2D.Double vec1 = new Point2D.Double(-4, 5);
		Point2D.Double vec2 = new Point2D.Double(5, 4);
		System.out.println(Math.toDegrees(MathsMethods.getVectorAngle(vec1, vec2)));
	}
}
