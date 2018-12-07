package game;

import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import UI.MenuLabel;
import gameObjects.GameObject;

public class WinMenuCreator {
	private Game game;
	public WinMenuCreator(Game game) {
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjects(){
		CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
		objects.addAll(getButtons());
		objects.addAll(getLabels());
		return objects;
	}
	
	private CopyOnWriteArrayList<GameObject> getButtons() {
		 CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		 int menuWidth = 300;
		 int menuHeight = 100;
		 int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		 int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		 buttons.add(new MenuButton(middleX,middleY+100,menuWidth,menuHeight,MenuID.PlayAgain,game));
		 
		 return buttons;
	}
	
	private  CopyOnWriteArrayList<GameObject> getLabels() {
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		int menuWidth = 500;
		int menuHeight = 100;
		int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		MenuLabel win = new MenuLabel(middleX,middleY-50,menuWidth,menuHeight,MenuID.Label,game);
		win.setFontSize(40);
		win.addLine("You Win");
		labels.add(win);
		
		return labels;
		
	}

}
