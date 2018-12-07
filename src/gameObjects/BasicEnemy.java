package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import Physics.Collision;
import game.Drawable;
import game.Game;
import game.ID;

public class BasicEnemy extends Enemy {

	public BasicEnemy(double x, double y, double width,double height,Game game,PathList path) {
		super(x, y, width,height,ID.BasicEnemy, game,path);
		this.rotation = new Point2D.Double(1,0);
		vX = 0.5;
		vY = 0.5;
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		super.update(objects);
		setPlayerDirection();
		checkForPlayer();
		if(seenPlayer&&canSeePlayer) {
			this.moveToPoint(playerLastPosition);
			
		}
		else if(seenPlayer&&!canSeePlayer){
			search();
		}
		else {
			followPath();
		}
		checkCollisions();
		
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
			setLastPlayerPosition();
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
				if(this.isColliding(player)) {
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
		};
		renderRotated(g,enemy);
		renderRotated(g,vision);

	}

}
