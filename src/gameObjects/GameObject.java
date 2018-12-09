package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Drawable;
import game.Game;
import game.ID;

public abstract class GameObject {
	//setup by the constructor
	protected double x,y,width,height;
	protected ID id;
	
	//created for collision detection purposes
	protected double top,bottom,left,right;
	protected double halfWidth,halfHeight;
	protected boolean visible = false;
	protected Point2D.Double rotation;
	
	protected Game game;
	
	public GameObject(double x,double y,double width,double height,ID id,Game game){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		
		halfWidth = width/2;
		halfHeight = height/2;
		
		this.top = y-halfHeight;
		this.bottom = y+halfHeight;
		this.left = x-halfWidth;
		this.right = x+halfWidth;
		
		this.game = game;
		rotation = new Point2D.Double();
	}
	
	
	
	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}



	public double getWidth() {
		return width;
	}



	public void setWidth(double width) {
		this.width = width;
	}



	public double getHeight() {
		return height;
	}



	public void setHeight(double height) {
		this.height = height;
	}



	public ID getId() {
		return id;
	}



	public void setId(ID id) {
		this.id = id;
	}



	public double getTop() {
		this.top = y-halfHeight;
		return top;
	}
	

	public double getBottom() {
		this.bottom = y+halfHeight;
		return bottom;
	}


	public double getLeft() {
		this.left = x-halfWidth;
		return left;
	}


	public double getRight() {
		this.right = x+halfWidth;
		return right;
	}



	public double getHalfWidth() {
		return halfWidth;
	}



	public double getHalfHeight() {
		return halfHeight;
	}
	

	public boolean isVisible() {
		return visible;
	}



	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Point2D.Double getRotation() {
		return rotation;
	}



	public void setRotation(Point2D.Double rotation) {
		this.rotation = rotation;
	}
	
	protected void renderRotated(Graphics g,Drawable action) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform backup = g2d.getTransform();
		AffineTransform rotated = new AffineTransform();
		rotated.translate((game.getWindowWidth()/2)-game.camera.getX(),(game.getWindowHeight()/2)-game.camera.getY());
		rotated.rotate(rotation.getX(),rotation.getY(),x,y);
		g2d.setTransform(rotated);
		action.draw(g2d);
		g2d.setTransform(backup);
	}
	
	protected void renderStatic(Graphics g,Drawable action) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform original = g2d.getTransform();
		g2d.translate(game.camera.getX()-(game.getWindowWidth()/2), game.camera.getY()-(game.getWindowHeight()/2));
		action.draw(g2d);
		g2d.transform(original);
	}
	//updates the object(all logic code goes here)
	public abstract void update(CopyOnWriteArrayList<GameObject> objects);
	//renders the object onto the screen
	public abstract void render(Graphics g);

}
