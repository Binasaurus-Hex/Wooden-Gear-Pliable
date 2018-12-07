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
	public boolean followPlayer = true;
	public boolean debug = false;
	

	public Camera(double x, double y, double width, double height, ID id, Game game) {
		super(x, y, width, height, ID.Camera, game);
		this.setVisible(true);
		this.vX = 3;
		this.vY = 3;
		if(debug) {
			followPlayer = false;
		}
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
		//remove -200 from x and +400 to y and set scale 1 for both x and y
		if(debug) {
			at.translate((game.getWindowWidth()/2)-xOffset-200,(game.getWindowHeight()/2)-yOffset+400);
			at.scale(0.3, 0.3);
			return at;
		}
		else {
			at.translate((game.getWindowWidth()/2)-xOffset,(game.getWindowHeight()/2)-yOffset);
			at.scale(1,1);
			return at;
		}
		
		
		
	}
	
	
	
}
