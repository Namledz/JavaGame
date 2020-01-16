package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coins extends GameObject {

	private Handler handler;

	private int timer = 300;

	public Coins(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

	}

	@Override
	public void tick() {
		if (timer <= 0) {
			handler.removeObject(this);
		} else {
			timer--;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int) x, (int) y, 30, 30);
		g.setColor(Color.BLACK);
		g.fillRect((int) (x+11),(int) (y+11),9, 9);

	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 30, 30);
	}

}
