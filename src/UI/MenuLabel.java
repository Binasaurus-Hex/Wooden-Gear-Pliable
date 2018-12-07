package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import gameObjects.GameObject;

public class MenuLabel extends UI_Object {
	Font font = new Font("Impact",Font.PLAIN,30);

	public MenuLabel(double x, double y, double width, double height,MenuID menuID, Game game) {
		super(x, y, width, height, menuID, game);
		text = new TextBox((x+halfWidth),(y+halfHeight),font,game);
		
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		text.update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)(x),(int)(y), (int)width, (int)height);
		g.setColor(Color.white);
		text.render(g);
		
	}
	
	public void setFontSize(int size){
		Font font = new Font("Impact",Font.PLAIN,size);
		text = new TextBox((x+halfWidth),(y+halfHeight),font,game);
	}
	
	public void addText(String block){
		text.addText(block);
	}
	
	public void addLine(String line){
		text.addLine(line);
	}

}
