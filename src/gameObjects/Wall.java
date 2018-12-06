package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;

public class Wall extends RectangleObject {
	private String name;

	public Wall(double x, double y, double width, double height,Game game,String name) {
		super(x, y, width, height, 1, ID.Wall, game);
		this.name = name;
		
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.green);
		g.fillRect((int)(x-halfWidth),(int)(y-halfHeight),(int)width,(int)(height));
		g.setColor(Color.black);
		Font font = new Font("Impact",Font.PLAIN,20);
		g.setFont(font);
		g.drawString(name,(int)x,(int)y);
	}

}
