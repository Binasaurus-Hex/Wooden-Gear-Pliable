package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;
import game.ImageLoader;

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
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(0,33,99));
		g2d.fillRect((int)(x-halfWidth),(int)(y-halfHeight),(int)width,(int)(height));
		g2d.setColor(Color.black);
		Font font = new Font("Impact",Font.PLAIN,20);
		g2d.setFont(font);
		g2d.drawString(name,(int)x,(int)y);
	}

}
