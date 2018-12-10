package game;

import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import UI.MenuLabel;
import UI.TextEntry;
import gameObjects.GameObject;

public class WinMenuCreator {
	private Game game;
	public WinMenuCreator(Game game) {
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjects(){
		CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
		objects.addAll(getButtons());
		objects.add(getTextEntry());
		objects.addAll(getLabels());
		return objects;
	}
	
	private CopyOnWriteArrayList<GameObject> getButtons() {
		 CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		 int menuWidth = 300;
		 int menuHeight = 100;
		 int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		 int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		 buttons.add(new MenuButton(middleX,middleY+200,menuWidth,menuHeight,MenuID.PlayAgain,game));
		 
		 
		 return buttons;
	}
	
	private  CopyOnWriteArrayList<GameObject> getLabels() {
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		int menuWidth = 500;
		int menuHeight = 100;
		int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		MenuLabel win = new MenuLabel(middleX,middleY-150,menuWidth,menuHeight,MenuID.Label,game,Game.titleFont);
		win.addLine("You Win");
		labels.add(win);
		
		MenuLabel enterName = new MenuLabel(middleX-300,middleY,400,100,MenuID.Label,game,Game.menuFont);
		enterName.background = new Color(0,0,0,0);
		enterName.addLine("Enter your name: ");
		enterName.addLine("Press enter to save score");
		labels.add(enterName);
		return labels;
		
	}
	
	private TextEntry getTextEntry(){
		int menuWidth = 300;
		int menuHeight = 100;
		int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		
		return new TextEntry(middleX,middleY,menuWidth,menuHeight,MenuID.Label,game);
	}

}
