package game;

import java.awt.geom.Point2D;
import java.util.concurrent.CopyOnWriteArrayList;

import UI.LeaderBoard;
import UI.MenuID;
import gameObjects.BasicEnemy;
import gameObjects.Camera;
import gameObjects.Floor;
import gameObjects.GameObject;
import gameObjects.Goal;
import gameObjects.HUD;
import gameObjects.PathList;
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
		gameObjects.addAll(getEnemies());
		
		Player player = new Player(640,100,80,80,game);
		player.setVisible(true);
		gameObjects.add(player);
		
		HUD hud = new HUD(player,game);
		hud.setVisible(true);
		gameObjects.add(hud);
		
		return gameObjects;
		
	}
	
	private CopyOnWriteArrayList<GameObject> getEnemies(){
		CopyOnWriteArrayList<GameObject> enemies = new CopyOnWriteArrayList<GameObject>();
		
		PathList path1 = new PathList(new Point2D.Double(0,-300));
		path1.add(new Point2D.Double(900, -300));
		BasicEnemy enemy = new BasicEnemy(640,-300,100,100,game,path1);
		enemy.setVisible(true);
		enemies.add(enemy);
		
		PathList path2 = new PathList(new Point2D.Double(1550, -750));
		path2.add(new Point2D.Double(2100, -750));
		path2.add(new Point2D.Double(2100, -300));
		path2.add(new Point2D.Double(1550, -300));
		BasicEnemy enemy2 = new BasicEnemy(1550,-750,100,100,game,path2);
		enemy2.setMass(5);
		enemy2.setVisible(true);
		enemies.add(enemy2);
		
		PathList path3 = new PathList(new Point2D.Double(2700, -750));
		path3.add(new Point2D.Double(3300, -750));
		path3.add(new Point2D.Double(3300, -300));
		path3.add(new Point2D.Double(2700, -300));
		BasicEnemy enemy3 = new BasicEnemy(2500,-750,100,100,game,path3);
		enemy3.setVisible(true);
		enemies.add(enemy3);
		
		PathList path4 = new PathList(new Point2D.Double(2100, 100));
		path4.add(new Point2D.Double(2100, 500));
		path4.add(new Point2D.Double(1550, 500));
		path4.add(new Point2D.Double(1550,100));
		BasicEnemy enemy4 = new BasicEnemy(1550,100,100,100,game,path4);
		enemy4.setVisible(true);
		enemies.add(enemy4);
		
		PathList path5 = new PathList(new Point2D.Double(2700, 100));
		path5.add(new Point2D.Double(2700, 500));
		path5.add(new Point2D.Double(3300, 500));
		path5.add(new Point2D.Double(3300, 100));
		BasicEnemy enemy5 = new BasicEnemy(2700,100,100,100,game,path5);
		enemy5.setVisible(true);
		enemies.add(enemy5);
		return enemies;
	}
	
	private CopyOnWriteArrayList<GameObject> getMap(){
		CopyOnWriteArrayList<GameObject> mapObjects = new CopyOnWriteArrayList<GameObject>();
		
		mapObjects.addAll(getFloors());
		
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
		
		Wall wall12 = new Wall(1250,-362,125,50,game,"12");
		wall12.setVisible(true);
		mapObjects.add(wall12);
		
		Wall wall13 = new Wall(1250,-187,125,50,game,"13");
		wall13.setVisible(true);
		mapObjects.add(wall13);
		
		Wall wall14 = new Wall(1325,-637,50,600,game,"14");
		wall14.setVisible(true);
		mapObjects.add(wall14);
		
		Wall wall15 = new Wall(2415,-910,2200,50,game,"15");
		wall15.setVisible(true);
		mapObjects.add(wall15);
		
		Wall wall16 = new Wall(3500,-135,50,1600,game,"16");
		wall16.setVisible(true);
		mapObjects.add(wall16);
		
		Wall wall17 = new Wall(1325,225,50,875,game,"17");
		wall17.setVisible(true);
		mapObjects.add(wall17);
		
		Wall wall18 = new Wall(2275,640,1900,50,game,"18");
		wall18.setVisible(true);
		mapObjects.add(wall18);
		
		Wall block1 = new Wall(2400,-110,150,1100,game,"b1");
		block1.setVisible(true);
		mapObjects.add(block1);
		
		Wall block2 = new Wall(1800,-550,150,150,game,"b2");
		block2.setVisible(true);
		mapObjects.add(block2);
		
		Wall block3 = new Wall(1800,300,150,150,game,"b3");
		block3.setVisible(true);
		mapObjects.add(block3);
		
		Wall block4 = new Wall(3000,-550,150,150,game,"b4");
		block4.setVisible(true);
		mapObjects.add(block4);
		
		Wall block5 = new Wall(3000,300,150,150,game,"b4");
		block5.setVisible(true);
		mapObjects.add(block5);
		
		Goal goal = new Goal(3350,690,250,50,game);
		goal.setVisible(true);
		mapObjects.add(goal);
		
		
		
		
		
		return mapObjects;
	}
	
	private CopyOnWriteArrayList<GameObject> getFloors(){
		CopyOnWriteArrayList<GameObject> floors = new CopyOnWriteArrayList<GameObject>();
		
		return floors;
	}
	
}
