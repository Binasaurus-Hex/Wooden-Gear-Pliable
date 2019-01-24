package gameObjects;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

import Physics.MathsMethods;
import game.Game;
import game.ID;

public abstract class Enemy extends RectangleObject{
	protected int fieldOfVeiw = 90;
	protected Point2D.Double playerDirection;
	protected boolean canSeePlayer = false;
	protected Point2D.Double playerLastPosition;
	protected CopyOnWriteArrayList<GameObject> objects;
	protected boolean seenPlayer = false;
	protected boolean onPath = true;
	protected boolean searching = false;
	protected PathList path;
	protected Timer searchTimer;
	private Point2D.Double nextPoint;
	private Point2D.Double currentPos;
	

	public Enemy(double x, double y,double width,double height,ID id, Game game,PathList path) {
		super(x, y,width,height, 1, id, game);
		playerDirection = new Point2D.Double();
		playerLastPosition = new Point2D.Double();
		this.searchTimer = new Timer();
		this.nextPoint = path.nextPoint;
		currentPos = new Point2D.Double();
		this.path = path;
	}
	
	protected boolean canSeePlayer() {
		double angle = MathsMethods.getVectorAngle(playerDirection, rotation);
		double angleDegrees = Math.toDegrees(angle);
		double distance = MathsMethods.length(playerDirection.x, playerDirection.y);
		if((angleDegrees<45)&&(distance<500)&& isSightClear()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	protected void moveToPoint(Point2D.Double point) {
		rotation.setLocation(point.getX()-x, point.getY()-y);
		double[] unitVector = MathsMethods.getUnitVector(x,y,point.getX(),point.getY());
		if(MathsMethods.distance(x, y, point.getX(), point.getY())>1) {
			
			x+=unitVector[0]*vX;
			y+=unitVector[1]*vY;
		}
		
		
	}
	
	protected Point2D.Double getPlayerPosition(){
		try {
			Player player = this.getPlayer();
			Point2D.Double playerPos = new Point2D.Double(player.getX(), player.getY());
			return (Double) playerPos.clone();
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	
	protected void followPath() {
		currentPos.setLocation(x, y);
		if(path.hasReachedNext(currentPos)) {
			nextPoint = path.getNextPoint();
		}
		else {
			moveToPoint(nextPoint);
		}
		
	}
	
	private boolean isSightClear(){
		try {
			Player player = this.getPlayer();
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
			return false;
		}
		
	}
	
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		this.objects = objects;
	}
	
	protected void setLastPlayerPosition() throws Exception {
		Player player = this.getPlayer();
		playerLastPosition.setLocation(player.getX(), player.getY());
	}
	
	protected void setPlayerDirection() throws Exception {
		Player player = getPlayer();
		double pX = player.getX();
		double pY = player.getY();
		playerDirection.setLocation(pX-x, pY-y);
		
	}
	
	private Player getPlayer() throws Exception{
		for(GameObject obj:objects) {
			if(obj.id == ID.Player) {
				Player player = (Player)obj;
				return player;
			}
		}
		Exception e = new Exception("player not found");
		throw e;
	}

	public PathList getPath() {
		return path;
	}

	public void setPath(PathList path) {
		this.path = path;
	}
}
