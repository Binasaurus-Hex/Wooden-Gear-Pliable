package game;

import java.util.concurrent.CopyOnWriteArrayList;

import gameObjects.BasicEnemy;
import gameObjects.Camera;
import gameObjects.GameObject;
import gameObjects.Player;
import gameObjects.Wall;

public class ObjectCreator {
	private Game game;
	public ObjectCreator(Game game){
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjects(){
		CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<GameObject>();
		
		game.camera = new Camera((game.getWindowWidth()/2),(game.getWindowHeight()/2),0,0,ID.Camera,game);
		gameObjects.add(game.camera);
		gameObjects.addAll(getMap());
		BasicEnemy enemy = new BasicEnemy(640,-300,100,100,game);
		enemy.setVisible(true);
		gameObjects.add(enemy);
		Player player = new Player(640,360,120,100,game);
		player.setVisible(true);
		gameObjects.add(player);
		
		return gameObjects;
		
	}
	
	public CopyOnWriteArrayList<GameObject> getMap(){
		CopyOnWriteArrayList<GameObject> mapObjects = new CopyOnWriteArrayList<GameObject>();
		
		Wall wall1 = new Wall(65,300,50,650,ID.Wall,game);
		wall1.setVisible(true);
		mapObjects.add(wall1);
		
		Wall wall2 = new Wall(1215,300,50,650,ID.Wall,game);
		wall2.setVisible(true);
		mapObjects.add(wall2);
		
		Wall wall3 = new Wall(640,635,1200,50,ID.Wall,game);
		wall3.setVisible(true);
		mapObjects.add(wall3);
		
		Wall wall4 = new Wall(640,-100,600,50,ID.Wall,game);
		wall4.setVisible(true);
		mapObjects.add(wall4);
		
		
		return mapObjects;
	}
	
}
