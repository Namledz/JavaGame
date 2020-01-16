package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

	private float alpha = 1;
	private Handler handler;
	private Color color;

	private int width;
	private int height;

	private float life;
	// life = 0.001 - 0.1 .

	public Trail(float x, float y, ID id, int width, int height, float life, Handler handler, Color color) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	@Override
	public void tick() {
		if (alpha > life) {
			alpha -= (life - 0.00001f);
		} else {
			handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));

		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

		g2d.setComposite(makeTransparent(1));

	}

	private AlphaComposite makeTransparent(float alpha) { // The small of the number , the longer the life of partical
															// is going to be	
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));// Ham tao do trong suot.
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
