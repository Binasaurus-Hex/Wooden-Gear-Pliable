package game;

import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import UI.MenuLabel;
import gameObjects.GameObject;

public class MainMenuCreator {
	private Game game;
	public MainMenuCreator(Game game){
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjects(){
		CopyOnWriteArrayList<GameObject> menuObjects = new CopyOnWriteArrayList<GameObject>();
		
		menuObjects.addAll(getButtons());
		menuObjects.addAll(getLabels());
		
		return menuObjects;
	}
	
	private CopyOnWriteArrayList<GameObject> getButtons(){
		CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		int menuWidth = 300;
		int menuHeight = 100;
		int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		buttons.add(new MenuButton(middleX,middleY-menuHeight-50,menuWidth,menuHeight,MenuID.PlayGame,game));
		buttons.add(new MenuButton(middleX,middleY,menuWidth,menuHeight,MenuID.Controls,game));
		buttons.add(new MenuButton(middleX,middleY+menuHeight+50,menuWidth,menuHeight,MenuID.ScoreBoard,game));
		buttons.add(new MenuButton(middleX,middleY+menuHeight+200,menuWidth,menuHeight,MenuID.Exit,game));
		
		return buttons;
	}
	
	private CopyOnWriteArrayList<GameObject> getLabels(){
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		int menuWidth = 800;
		int menuHeight = 100;
		int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		MenuLabel title = new MenuLabel(middleX,100,800,menuHeight,MenuID.Label,game,Game.titleFont);
		title.background = new Color(0,0,0,0);
		title.addLine("Wooden Gear Pliable");
		labels.add(title);
		
		return labels;
	}

}
