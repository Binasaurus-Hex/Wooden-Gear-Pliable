package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import Physics.Collision;
import game.Drawable;
import game.Game;
import game.ID;
import game.ImageLoader;

public class Player extends RectangleObject {
	private AimCircle aimCircle;
	private BufferedImage sprite;
	private double baseAttackTime;
	Timer timer;
	private boolean canShoot;
	

	public Player(double x, double y,double width,double height,Game game) {
		super(x, y, width,height, 1,ID.Player, game);
		this.vX = 0.9;
		this.vY = 0.9;
		aimCircle = new AimCircle(x,y,600,ID.AimCircle,game,this);
		ImageLoader loader = new ImageLoader();
		sprite = loader.loadImage("/gunMan.png");
		baseAttackTime = 100;
		timer = new Timer();
		canShoot = true;
		rotation = aimCircle.getRotation();
		
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		aimCircle.update(objects);
		
		checkCollisions(objects);
		
		if(game.key.isKeyDown("W")){
			y-=vY;
		}
		if(game.key.isKeyDown("S")){
			y+=vY;
		}
		if(game.key.isKeyDown("A")){
			x-=vX;
		}
		if(game.key.isKeyDown("D")){
			x+=vX;
		}
		if(game.cursor.isDown()&&canShoot){
			shootBullet();
			canShoot = false;
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					canShoot = true;
				}
			}, (long) baseAttackTime);
		}

	}
	
	private void shootBullet(){
		Point2D.Double right = new Point2D.Double(-rotation.getY()*22,rotation.getX()*22);
		Point2D.Double forward = new Point2D.Double(rotation.getX()*55,rotation.getY()*55);
		double xPos = x+right.getX()+forward.getX();
		double yPos = y+right.getY()+forward.getY();
		double xVel = rotation.getX()*5;
		double yVel = rotation.getY()*5;
		Bullet bullet = new Bullet(xPos,yPos,xVel,yVel,game);
		game.getHandler().add(bullet);
	}
	
	private boolean checkCollisions(CopyOnWriteArrayList<GameObject> objects){
		for(GameObject obj:objects){
			if(obj.id == ID.Wall){
				Wall wall = (Wall)obj;
				if(this.isColliding(wall)){
					
					Collision.resolveCollision(this, wall);
					return true;
				}
			}
			if(obj.id == ID.BasicEnemy) {
				BasicEnemy enemy = (BasicEnemy)obj;
				if(this.isColliding(enemy)) {
					Collision.resolveCollision(this, enemy);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		aimCircle.render(g);
		Drawable action = (graphics)-> graphics.drawImage(sprite,(int)(x-halfWidth), (int)(y-halfHeight),(int)(width),(int)(height), null);
		renderRotated(g,action);
	}

	public AimCircle getAimCircle() {
		return aimCircle;
	}

	public void setAimCircle(AimCircle aimCircle) {
		this.aimCircle = aimCircle;
	}
	

}
