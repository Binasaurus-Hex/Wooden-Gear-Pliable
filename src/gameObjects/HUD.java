package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Drawable;
import game.Game;
import game.ID;

public class HUD extends GameObject{
	public int score;
	
	public HUD(Game game) {
		super(100, 100, 0, 0, ID.HUD, game);
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		Drawable ammo = (graphics)->{
			Font font = new Font("Impact",Font.PLAIN,30);
			graphics.setFont(font);
			graphics.setColor(Color.red);
			graphics.drawString("ammo", (int)x,(int)y);
		};
		this.renderStatic(g, ammo);
		
	}
	
	

}
