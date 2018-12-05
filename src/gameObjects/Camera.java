package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;
import gameObjects.GameObject;

public class Camera extends GameObject{
	private int vX,vY;
	

	public Camera(double x, double y, double width, double height, ID id, Game game) {
		super(x, y, width, height, ID.Camera, game);
		this.setVisible(true);
		this.vX = 1;
		this.vY = 1;
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		if(game.key.isKeyDown("I")){
			y-=vY;
		}
		else if(game.key.isKeyDown("K")){
			y+=vY;
		}
		if(game.key.isKeyDown("J")){
			x-=vX;
		}
		else if(game.key.isKeyDown("L")){
			x+=vX;
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.transform(getTransform());
		
	}
	
	private AffineTransform getTransform() {
		AffineTransform at = new AffineTransform();
		int xOffset = (int) (x);
		int yOffset = (int) (y);
		
		at.translate((game.getWindowWidth()/2)-xOffset,(game.getWindowHeight()/2)-yOffset);
		return at;
		
	}
	
	
	
}
