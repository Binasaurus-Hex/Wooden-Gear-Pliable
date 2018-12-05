package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;

public class Wall extends RectangleObject {

	public Wall(double x, double y, double width, double height, ID id, Game game) {
		super(x, y, width, height, 1, id, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)(x-halfWidth),(int)(y-halfHeight),(int)width,(int)(height));
	}

}
