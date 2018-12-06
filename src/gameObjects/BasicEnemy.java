package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.concurrent.CopyOnWriteArrayList;

import Physics.Collision;
import Physics.MathsMethods;
import game.Drawable;
import game.Game;
import game.ID;

public class BasicEnemy extends Enemy {

	public BasicEnemy(double x, double y, double width,double height,Game game) {
		super(x, y, width,height,ID.BasicEnemy, game);
		this.rotation = new Point2D.Double(1,0);
		vX = 0.5;
		vY = 0.5;
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		super.update(objects);
		setPlayerDirection();
		checkForPlayer();
		if(seenPlayer) {
			this.moveToPoint(playerLastPosition);
			
		}
		checkCollisions();
		
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
					Collision.resolveCollision(this, player);
				}
			}
		}
	}
	

	@Override
	public void render(Graphics g) {
		
		Drawable vision = (graphics)->{
			Color visionColor = new Color(0, 255, 0, 100);
			if(canSeePlayer) {
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
