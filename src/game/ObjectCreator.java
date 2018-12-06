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
		
		Wall wall1 = new Wall(165,250,50,750,game,"1");
		wall1.setVisible(true);
		mapObjects.add(wall1);
		
		Wall wall2 = new Wall(1115,350,50,600,game,"2");
		wall2.setVisible(true);
		mapObjects.add(wall2);
		
		Wall wall3 = new Wall(640,635,1000,50,game,"3");
		wall3.setVisible(true);
		mapObjects.add(wall3);
		
		Wall wall4 = new Wall(765,50,750,50,game,"4");
		wall4.setVisible(true);
		mapObjects.add(wall4);
		
		Wall wall5 = new Wall(415,-50,50,150,game,"5");
		wall5.setVisible(true);
		mapObjects.add(wall5);
		
		Wall wall6 = new Wall(-100,-100,500,50,game,"6");
		wall6.setVisible(true);
		mapObjects.add(wall6);
		
		Wall wall7 = new Wall(800,-100,800,50,game,"7");
		wall7.setVisible(true);
		mapObjects.add(wall7);
		
		Wall wall8 = new Wall(-325,-275,50,400,game,"8");
		wall8.setVisible(true);
		mapObjects.add(wall8);
		
		Wall wall9 = new Wall(450,-450,1500,50,game,"9");
		wall9.setVisible(true);
		mapObjects.add(wall9);
		
		Wall wall10 = new Wall(1175,-400,50,125,game,"10");
		wall10.setVisible(true);
		mapObjects.add(wall10);
	
		Wall wall11 = new Wall(1175,-150,50,125,game,"11");
		wall11.setVisible(true);
		mapObjects.add(wall11);
		
		
		
		
		
		
		return mapObjects;
	}
	
}
