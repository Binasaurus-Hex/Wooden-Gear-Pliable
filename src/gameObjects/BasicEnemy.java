package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Drawable;
import game.Game;
import game.ID;

public class BasicEnemy extends Enemy {

	public BasicEnemy(double x, double y, double width,double height,Game game) {
		super(x, y, width,height,ID.BasicEnemy, game);
		this.rotation = new Point2D.Double(1,0);
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		setPlayerDirection(objects);
		if(canSeePlayer()) {
			canSeePlayer = true;
			Point2D.Double clone = (Double) playerDirection.clone();
			setRotation(clone);
		}
		else {
			canSeePlayer = false;
		}

	}

	@Override
	public void render(Graphics g) {
		Color color = Color.blue;
		if(canSeePlayer) {
			color = Color.GREEN;
		}
		g.setColor(color);
		Drawable enemy = (graphics)->graphics.drawRect((int)(x-halfWidth),(int)(y-halfHeight),(int)(width),(int)(height));
		renderRotated(g,enemy);

	}

}
