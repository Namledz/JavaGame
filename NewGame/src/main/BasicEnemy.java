package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = 5;
		velY = 5;
	}


	public void tick() {
		x += velX;
		y += velY;

		if (y <= 0 || y >= Game.HEIGHT - 52)
			velY *= -1;

		if (x <= 0 || x >= Game.WIDTH - 20)
			velX *= -1;

		// when the object touch the bar , it goes back to another way.

		handler.addObject(new Trail((int) x, (int) y, ID.Trail, 12, 12, 0.1f, handler, Color.RED));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, 12, 12);

	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 12, 12);
	}

}
