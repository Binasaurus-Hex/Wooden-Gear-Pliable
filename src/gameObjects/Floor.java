package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;
import game.ImageLoader;

public class Floor extends GameObject {
	private BufferedImage textureImage;
	private int textureSize;

	public Floor(double x, double y, double width, double height,int textureSize,String filename,Game game) {
		super(x, y, width, height,ID.Floor, game);
		ImageLoader loader = new ImageLoader();
		textureImage = loader.loadImage(filename);
		this.textureSize = textureSize;
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		return;

	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		TexturePaint texture = new TexturePaint(textureImage,new Rectangle(0,0,textureSize,textureSize));
		g2d.setPaint(texture);
		g2d.fillRect((int)x,(int)y,(int)width,(int)height);

	}

}
