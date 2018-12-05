package game;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CopyOnWriteArrayList;

import Physics.MathsMethods;
import UI.UI_Object;
import gameObjects.GameObject;

public class MouseInput extends MouseAdapter {
	private Game game;
	public MouseInput(Game game){
		this.game = game;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point mouse = e.getPoint();
		switch(game.currentState){
		
		case Game:
			game.cursor.setDown(true);
			break;
		
		case MainMenu:
			CopyOnWriteArrayList<GameObject> mainMenuObjects = game.getMainMenuHandler().getObjects();
			menuClicked(mainMenuObjects,mouse);
			break;
		
		case PauseMenu:
			CopyOnWriteArrayList<GameObject> pauseMenuObjects = game.getPauseMenuHandler().getObjects();
			menuClicked(pauseMenuObjects,mouse);
			break;
			
		case Controls:
			CopyOnWriteArrayList<GameObject> controlMenuObjects = game.getControlMenuHandler().getObjects();
			menuClicked(controlMenuObjects,mouse);
			break;
		case Settings:
			break;
		case Win:
			CopyOnWriteArrayList<GameObject> winMenuObjects = game.getWinMenuHandler().getObjects();
			menuClicked(winMenuObjects,mouse);
		default:
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point mouse = e.getPoint();
		switch(game.currentState){
		
		case Game:
			game.cursor.setX(e.getX());
			game.cursor.setY(e.getY());
			break;
			
		case MainMenu:
			CopyOnWriteArrayList<GameObject> mainMenuObjects = game.getMainMenuHandler().getObjects();
			menuMoved(mainMenuObjects,mouse);
			break;
			
		case PauseMenu:
			CopyOnWriteArrayList<GameObject> pauseMenuObjects = game.getPauseMenuHandler().getObjects();
			menuMoved(pauseMenuObjects,mouse);
			break;
			
		case Controls:
			CopyOnWriteArrayList<GameObject> controlMenuObjects = game.getControlMenuHandler().getObjects();
			menuMoved(controlMenuObjects,mouse);
			break;
		case Settings:
			break;
		case Win:
			CopyOnWriteArrayList<GameObject> winMenuObjects = game.getWinMenuHandler().getObjects();
			menuMoved(winMenuObjects,mouse);
		default:
			break;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point mouse = e.getPoint();
		switch(game.currentState){
		case Game:
			game.cursor.setX(e.getX());
			game.cursor.setY(e.getY());
			break;
		case MainMenu:
			break;
		case PauseMenu:
			break;
		default:
			break;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		switch(game.currentState){
		case Game:
			game.cursor.setDown(false);
			//TODO
			break;
		case MainMenu:
			break;
		case PauseMenu:
			break;
		default:
			break;
		}
	}
	/*
	 * attemps to get the menu object that has intersected with the mouse, if one hasnt intersected this method returns null
	 * else it returns the menu object that has been intersected by the mouse
	 */
	private UI_Object getMenuObject(CopyOnWriteArrayList<GameObject> menuObjects, Point point){
		for(GameObject obj : menuObjects){
			if(obj.getId() == ID.UI_Object){
				UI_Object ui = (UI_Object)obj;
				if(ui.isColliding(point)){
					return ui;
				}
				else{
					ui.setHover(false);
				}
			}
		}
		return null;
	}
	
	private void menuClicked(CopyOnWriteArrayList<GameObject> menuObjects,Point mouse){
		//gets the object that has been clicked (returns null if one hasnt been clicked)
		UI_Object UI = getMenuObject(menuObjects,mouse);
		//returns if object is null (no object was clicked)
		if(UI == null)return;
		if(UI.isSelectable()&& UI.isHover()){
			UI.setSelected(true);
		}
		else{
			UI.setSelected(false);
		}
	}
	
	private void menuMoved(CopyOnWriteArrayList<GameObject> menuObjects,Point mouse){
		UI_Object UI = getMenuObject(menuObjects,mouse);
		if(UI == null)return;
		if(UI.isSelectable()){
			UI.setHover(true);
		}
		else{
			UI.setHover(false);
		}
	}

}
