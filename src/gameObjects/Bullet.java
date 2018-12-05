package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;

public class Bullet extends CircleObject {
	private int collideCount;
	private int collideLimit;
	private Timer killTimer;
	private long killTime;
	private boolean kill = false;

	public Bullet(double x, double y, double vX,double vY,Game game) {
		super(x, y, 5, 1,ID.Bullet, game);
		this.vY = vY;
		this.vX = vX;
		this.setVisible(true);
		collideCount = 0;
		collideLimit = 4;
		killTime = 2000;
		killTimer = new Timer();
		killTimer.schedule(new TimerTask(){
			@Override
			public void run() {
				kill = true;
			}
		}, killTime);
		
		
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		
		x+=vX;
		y+=vY;
		
		checkCollisions(objects);
		if((collideCount>=collideLimit)||kill){
			game.getHandler().remove(this);
		}
		
		
		

	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.red);
		g2d.fillOval((int)(x-radius),(int)(y-radius),(int)(radius*2),(int)(radius*2));

	}
	
	private void checkCollisions(CopyOnWriteArrayList<GameObject> objects){
		for(GameObject obj:objects){
			if(obj.id == ID.Wall){
				Wall wall = (Wall)obj;
				if(this.isColliding(wall)){
					
					this.collide(wall);
					collideCount++;
				}
			}
		}
	}

}
