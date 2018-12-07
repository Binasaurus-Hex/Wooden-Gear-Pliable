package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
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
import game.Sound;

public class Player extends RectangleObject {
	private AimCircle aimCircle;
	private BufferedImage sprite1;
	private BufferedImage sprite2;
	private double baseAttackTime;
	Timer timer;
	private boolean canShoot;
	private boolean shooting = false;
	public int ammo;
	public final int clipSize = 6;
	private boolean reset = true;
	private Sound shoot;
	private Sound empty;
	

	public Player(double x, double y,double width,double height,Game game) {
		super(x, y, width,height, 1,ID.Player, game);
		this.vX = 0.9;
		this.vY = 0.9;
		aimCircle = new AimCircle(x,y,600,ID.AimCircle,game,this);
		ImageLoader loader = new ImageLoader();
		sprite1 = loader.loadImage("/spy1.png");
		sprite2 = loader.loadImage("/spy2.png");
		baseAttackTime = 200;
		timer = new Timer();
		canShoot = true;
		rotation = aimCircle.getRotation();
		ammo = clipSize;
		shoot = new Sound("/gunSilencer.wav");
		empty = new Sound("/gunEmpty.wav");
		
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		aimCircle.update(objects);
		if(ammo<1) {
			canShoot = false;
		}
		
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
		if(game.cursor.isDown()&&canShoot&&reset){
			shootBullet();
			canShoot = false;
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					shoot.stop();
					canShoot = true;
					shooting = false;
				}
			}, (long) baseAttackTime);
		}
		if(game.cursor.isDown()&&ammo<1){
			if(!empty.isPlaying())empty.play();
		}
		if(!game.cursor.isDown()){
			reset = true;
			empty.stop();
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
		shooting = true;
		reset = false;
		ammo--;
		if(!shoot.isPlaying())shoot.play();
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
			if(obj.id == ID.Goal) {
				Goal goal = (Goal)obj;
				if(this.isColliding(goal)) {
					game.win();
				}
			}
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		BufferedImage sprite;
		if(shooting){
			sprite = sprite2;
		}
		else{
			sprite = sprite1;
		}
		g.setColor(Color.white);
		aimCircle.render(g);
		Drawable action = (graphics)->{
			graphics.setColor(Color.green);
			//graphics.drawRect((int)(x-halfWidth),(int)(y-halfHeight),(int)(width),(int)(height));
			g.setColor(Color.red);
			//graphics.drawRect((int)(x-halfWidth+10),(int)(y-halfHeight+20),(int)(width-10),(int)(height-40));
			graphics.drawImage(sprite,(int)(x-65), (int)(y-65),(int)(130),(int)(130), null);
		};
		renderRotated(g,action);
	}

	public AimCircle getAimCircle() {
		return aimCircle;
	}

	public void setAimCircle(AimCircle aimCircle) {
		this.aimCircle = aimCircle;
	}
	

}
