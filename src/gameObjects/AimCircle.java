package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import Physics.MathsMethods;
import game.Cursor;
import game.Game;
import game.ID;

public class AimCircle extends CircleObject {
	private Player player;
	private Camera camera;
	private Cursor cursor;

	public AimCircle(double x, double y, double radius,ID id, Game game,Player player) {
		super(x, y, radius, 1, id, game);
		this.player = player;
		camera = game.camera;
		cursor = game.cursor;
		
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		x = player.x;
		y = player.y;
		
		
		double cursorX = x+cursor.getX()-(game.getWindowWidth()/2);
		double cursorY = y+cursor.getY()-(game.getWindowHeight()/2);
		
		double distance = Math.sqrt(Math.pow(cursorX-x, 2)+Math.pow(cursorY-y, 2));
		double[] unitVector = MathsMethods.getUnitVector(x, y,cursorX,cursorY);
		rotation.setLocation(unitVector[0], unitVector[1]);
		
		if(distance>(radius/2)&& camera.followPlayer){
			camera.setX(x+(rotation.getX()*(radius/2)));
			camera.setY(y+(rotation.getY()*(radius/2)));
		}
		else if(camera.followPlayer){
			camera.setX(cursorX);
			camera.setY(cursorY);	
		}
	}

	@Override
	public void render(Graphics g) {
		//g.drawOval((int)(x-radius),(int)(y-radius),(int)(radius*2),(int)(radius*2));
		//g.drawOval((int)(cursor.getX()+camera.getX()-(game.getWindowWidth()/2)),(int)(cursor.getY()+camera.getY()-(game.getWindowHeight()/2)), 10, 10);
		//g.setColor(Color.red);
	}
}
