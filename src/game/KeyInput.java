package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Game game;
	private Key keys;
	public KeyInput(Game game){
		this.game = game;
		keys = game.key;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String key = KeyEvent.getKeyText(keyCode);
		switch(key){
		//goes to menu
		case "Escape":
			game.menu();
			break;
		case "B":
			game.reset();
			break;
			
		case "P":
			game.pause();
			break;
			
		case "O":
			game.musicOn ^= true;
			break;
		case "I":
			keys.setKeyDown(key);
			break;
		case "K":
			keys.setKeyDown(key);
			break;
		case "J":
			keys.setKeyDown(key);
			break;
		case "L":
			keys.setKeyDown(key);
			break;
		case "W":
			keys.setKeyDown(key);
			break;
		case "A":
			keys.setKeyDown(key);
			break;
		case "S":
			keys.setKeyDown(key);
			break;
		case "D":
			keys.setKeyDown(key);
			break;
		}
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String key = KeyEvent.getKeyText(keyCode);
		switch(key){
		case "I":
			keys.setKeyUp(key);
			break;
		case "K":
			keys.setKeyUp(key);
			break;
		case "J":
			keys.setKeyUp(key);
			break;
		case "L":
			keys.setKeyUp(key);
			break;
		case "W":
			keys.setKeyUp(key);
			break;
		case "A":
			keys.setKeyUp(key);
			break;
		case "S":
			keys.setKeyUp(key);
			break;
		case "D":
			keys.setKeyUp(key);
			break;
		}
		
	}
	
	
	
	

}
