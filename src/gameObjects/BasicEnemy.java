package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import Physics.Collision;
import game.Drawable;
import game.Game;
import game.ID;
import game.ImageLoader;
import game.Sound;

public class BasicEnemy extends Enemy {
	
	private BufferedImage sprite1;
	private BufferedImage sprite2;
	private BufferedImage currentSprite;
	private int tick = 0;

	public BasicEnemy(double x, double y, double width,double height,Game game,PathList path) {
		super(x, y, width,height,ID.BasicEnemy, game,path);
		this.rotation = new Point2D.Double(1,0);
		vX = 0.6;
		vY = 0.6;
		ImageLoader loader = new ImageLoader();
		sprite1 = loader.loadImage("/sprites/enemy/robot1.png");
		sprite2 = loader.loadImage("/sprites/enemy/robot2.png");
		currentSprite = sprite1;
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		super.update(objects);
		updateSprite();
		try {
			setPlayerDirection();
		} catch (Exception e) {
			vX = 0.6;
			vY = 0.6;
			followPath();
			return;
		}
		checkForPlayer();
		if(seenPlayer&&canSeePlayer) {
			this.vX = 1.5;
			this.vY = 1.5;
			this.moveToPoint(playerLastPosition);
			
		}
		else if(seenPlayer&&!canSeePlayer){
			this.vX = 0.5;
			this.vY = 0.5;
			search();
		}
		else {
			followPath();
		}
		checkCollisions();
		
	}
	
	private void updateSprite(){
		if(tick>30){
			tick = 0;
			if(currentSprite.equals(sprite1))currentSprite = sprite2;
			else currentSprite = sprite1;
		}
		else{
			tick++;
		}
	}
	
	private void search() {
		if(!searching) {
			this.searchTimer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					seenPlayer = false;
					searching = false;
				}
			}, 3000);
			searching = true;
		}
		else {
			moveToPoint(playerLastPosition);
		}
	}
	
	private void checkForPlayer(){
		if(canSeePlayer()) {
			canSeePlayer = true;
			seenPlayer = true;
			try {
				setLastPlayerPosition();
			} catch (Exception e) {
				canSeePlayer = false;
				seenPlayer = false;
			}
		}
		else{
			canSeePlayer = false;
		}
	}
	
	
	
	
	private void checkCollisions() {
		for(GameObject obj:objects) {
			if(obj.id == ID.Wall) {
				Wall wall = (Wall)obj;
				if(this.isColliding(wall)) {
					Collision.resolveCollision(this, wall);
				}
			}
			else if(obj.id == ID.Player) {
				Player player = (Player)obj;
				if(this.isColliding(player)&& this.canSeePlayer) {
					objects.remove(player);
				}
			}
			else if(obj.id == ID.Bullet) {
				Bullet bullet = (Bullet)obj;
				if(this.isColliding(bullet)) {
					objects.remove(bullet);
					objects.remove(this);
					
				}
			}
		}
	}
	

	@Override
	public void render(Graphics g) {
		for(Point2D.Double point:this.path) {
			g.setColor(Color.red);
			g.drawRect((int)(point.getX()-5),(int)(point.getY()-5),10,10);
		}
		if(mass == 5){
			
		}
		
		Drawable vision = (graphics)->{
			Color visionColor = new Color(0, 255, 0, 100);
			if(seenPlayer) {
				visionColor = new Color(255,0,0,100);
			}
			graphics.setColor(visionColor);
			int length = 1000;
			graphics.fillArc((int)(x-length/2),(int)(y-length/2),length,length, 315, 45);
			graphics.fillArc((int)(x-length/2),(int)(y-length/2),length,length, 0, 45);
		};
		Drawable enemy = (graphics)->{
			Color enemyColor = Color.WHITE;
			graphics.setColor(enemyColor);
			graphics.fillRect((int)(x-halfWidth),(int)(y-halfHeight),(int)(width),(int)(height));
			g.drawImage(currentSprite,(int)(x-100),(int)(y-100),(int)(200),(int)(200), null);
		};
		
		renderRotated(g,vision);
		renderRotated(g,enemy);
	}

}
