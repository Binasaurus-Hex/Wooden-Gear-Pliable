package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.GameState;
import gameObjects.GameObject;

public class TextEntry extends UI_Object {
	private String line;
	private Font font = game.menuFont;
	private boolean canType;
	private Timer typeTimer;

	public TextEntry(double x, double y, double width, double height, MenuID menuID, Game game) {
		super(x, y, width, height, menuID, game);
		line = "";
		text = new TextBox((x+halfWidth),(y+halfHeight),font,game);
		canType = true;
		typeTimer = new Timer();
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		game.enteringText = true;
		text.update();
		if(game.currentState == GameState.Win && canType){
			canType = false;
			typeTimer.schedule(new TimerTask(){
				@Override
				public void run() {
					canType = true;
				}
			},80);
			String letter = game.key.currentKey;
			
			if(line.length()>0 && letter.equals("Backspace")){
				removeLastLetter();
			}
			else if(line.length()>0 && letter.equals("Space")){
				addLetter(" ");
			}
			else if(line.length()>0 && letter.equals("Enter")){
				game.leaderBoard.addScore(line, game.getScore());
				game.enteringText = false;
				game.menu();
			}
			else if(!letter.equals("Backspace")&& !letter.equals("Space") && !letter.equals("Shift") && !letter.equals("Escape")){
				addLetter(letter);
			}
		}
		text.clear();
		text.addLine(line);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x,(int)y,(int)width,(int)height);
		g.setColor(Color.white);
		text.render(g);
	}
	
	private void addLetter(String letter){
		line+=letter;
	}
	
	private void removeLastLetter(){
		line = (String)line.subSequence(0, line.length()-1);
	}

}
