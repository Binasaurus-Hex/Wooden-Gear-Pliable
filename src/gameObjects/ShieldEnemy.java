package gameObjects;

import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;

public class ShieldEnemy extends Enemy {

	public ShieldEnemy(double x, double y, double width,double height,Game game) {
		super(x, y, width,height,ID.ShieldEnemy, game);
		
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

}
