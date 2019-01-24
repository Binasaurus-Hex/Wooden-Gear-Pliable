package game;

import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import UI.MenuLabel;
import gameObjects.GameObject;

public class ControlMenuCreator {
	private Game game;
	
	public ControlMenuCreator(Game game){
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjects(){
		CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
		objects.addAll(getButtons());
		objects.addAll(getLabels());
		return objects;
	}
	
	public CopyOnWriteArrayList<GameObject> getButtons(){
		CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		int menuWidth = 300;
		int menuHeight = 100;
		int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		
		buttons.add(new MenuButton(middleX,middleY+200,menuWidth,menuHeight,MenuID.Menu,game));
		
		return buttons;
	}
	
	public CopyOnWriteArrayList<GameObject> getLabels(){
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		int menuWidth = 400;
		int menuHeight = 100;
		int middleX = (int)((game.getWindowWidth()/2)-(menuWidth/2));
		int middleY = (int)((game.getWindowHeight()/2)-(menuHeight/2));
		
		MenuLabel controlsTitle = new MenuLabel(middleX,middleY-450,400,100,MenuID.Label,game,Game.menuFont);
		controlsTitle.addLine("Controls");
		controlsTitle.addLine("");
		labels.add(controlsTitle);
		
		MenuLabel controls = new MenuLabel(middleX,middleY-350,400,500,MenuID.Label,game,Game.menuFontMedium);
		controls.addText("W : UP \n\n"
				+ "A : LEFT \n\n"
				+ "S : DOWN \n\n"
				+ "D : RIGHT \n\n"
				+ "Mouse : aim / shoot\n\n"
				+ "ESC : main menu \n\n"
				+ "P : pause menu\n\n"
				+ "O : music on/off\n\n"
				+ "B : reset game");
		
		labels.add(controls);
		return labels;
	}

}
