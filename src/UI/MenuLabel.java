package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import gameObjects.GameObject;

public class MenuLabel extends UI_Object {
	//Font font = new Font("Impact",Font.PLAIN,30);
	Font font;
	public Color foreground;
	public Color background;

	public MenuLabel(double x, double y, double width, double height,MenuID menuID, Game game,Font font) {
		super(x, y, width, height, menuID, game);
		this.font = font;
		text = new TextBox((x+halfWidth),(y+halfHeight),font,game);
		foreground = Color.white;
		background = Color.blue;
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		text.update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(background);
		g.fillRect((int)(x),(int)(y), (int)width, (int)height);
		g.setColor(foreground);
		text.render(g);
		
	}
	
	public void setFont(Font font){
		this.font = font;
	}
	
	public void addText(String block){
		text.addText(block);
	}
	
	public void addLine(String line){
		text.addLine(line);
	}

}
