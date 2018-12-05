package gameObjects;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.concurrent.CopyOnWriteArrayList;

import Physics.MathsMethods;
import game.Game;
import game.ID;

public abstract class Enemy extends RectangleObject{
	protected int fieldOfVeiw = 90;
	protected Point2D.Double playerDirection;
	protected boolean canSeePlayer = false;
	

	public Enemy(double x, double y,double width,double height,ID id, Game game) {
		super(x, y,width,height, 1, id, game);
		playerDirection = new Point2D.Double();
		
		
	}
	
	protected boolean canSeePlayer() {
		double angle = MathsMethods.getVectorAngle(playerDirection, rotation);
		double angleDegrees = Math.toDegrees(angle);
		double distance = MathsMethods.length(playerDirection.x, playerDirection.y);
		if((angleDegrees<45)&&(distance<1000)&& isSightClear()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isSightClear(){
		CopyOnWriteArrayList<GameObject> objects = game.getHandler().getObjects();
		
		try {
			Player player = this.getPlayer(objects);
			for(GameObject obj:objects){
				if(obj.id == ID.Wall){
					Wall wall = (Wall)obj;
					
					//topLine
					boolean isTop = MathsMethods.linesIntersect(x, y,player.x,player.y,wall.left,wall.top,wall.right,wall.top);
					//bottom
					boolean isBottom = MathsMethods.linesIntersect(x, y,player.x,player.y,wall.left,wall.bottom,wall.right,wall.bottom);
					//left
					boolean isLeft = MathsMethods.linesIntersect(x, y,player.x,player.y,wall.left,wall.top,wall.left,wall.bottom);
					//right
					boolean isRight = MathsMethods.linesIntersect(x, y,player.x,player.y,wall.right,wall.top,wall.right,wall.bottom);
					if(isTop||isBottom||isLeft||isRight){
						return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		
	}
	
	protected void setPlayerDirection(CopyOnWriteArrayList<GameObject> objects) {
		try {
			Player player = getPlayer(objects);
			playerDirection.setLocation(player.getX()-x, player.getY()-y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Player getPlayer(CopyOnWriteArrayList<GameObject> objects) throws Exception{
		for(GameObject obj:objects) {
			if(obj.id == ID.Player) {
				Player player = (Player)obj;
				return player;
			}
		}
		Exception e = new Exception("player not found");
		throw e;
	}
	
	

}
