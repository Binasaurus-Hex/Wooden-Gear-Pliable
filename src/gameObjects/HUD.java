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
	private int score;
	private Player player;
	
	public HUD(Player player,Game game) {
		super(0, 0, 0, 0, ID.HUD, game);
		this.score = game.getScore();
		this.player = player;
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
			graphics.setColor(Color.white);
			graphics.drawString("Ammo : ", 50,50);
			g.drawRect(150, 20, 100, 40);
			g.drawString(String.valueOf(player.ammo)+" / "+player.clipSize, 170, 50);
		};
		Drawable score = (graphics)->{
			Font font = new Font("Impact",Font.PLAIN,30);
			graphics.setFont(font);
			graphics.setColor(Color.white);
			graphics.drawString("Score : ", 700,50);
			g.drawRect(800, 20, 200, 40);
			graphics.drawString(String.valueOf(game.getScore()), 850, 50);
		};
		this.renderStatic(g, ammo);
		this.renderStatic(g, score);
		
	}
	
	

}
