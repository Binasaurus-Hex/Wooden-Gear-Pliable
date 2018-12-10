package game;

import java.util.concurrent.CopyOnWriteArrayList;

import UI.LeaderBoard;
import UI.MenuButton;
import UI.MenuID;
import gameObjects.GameObject;

public class ScoreBoardCreator {

	private Game game;
	public ScoreBoardCreator(Game game){
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjects(){
		CopyOnWriteArrayList<GameObject> menuObjects = new CopyOnWriteArrayList<GameObject>();
		menuObjects.addAll(getButtons());
		menuObjects.add(game.leaderBoard);
		
		return menuObjects;
	}
	
	private CopyOnWriteArrayList<GameObject> getButtons(){
		CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		int menuWidth = 300;
		int menuHeight = 100;
		int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		buttons.add(new MenuButton(middleX,middleY+game.getHeight()/4,menuWidth,menuHeight,MenuID.Menu,game));
		
		return buttons;
	}
	
	public LeaderBoard getNewScoreBoard(){
		int menuWidth = 500;
		int menuHeight = 700;
		int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		return new LeaderBoard(middleX,middleY-100,menuWidth,menuHeight,MenuID.Label,game);
	}

}
