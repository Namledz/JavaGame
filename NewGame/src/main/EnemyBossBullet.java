package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

	private Handler handler;

	Random r = new Random();

	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = (r.nextInt(5 - -5) + -5); // velX random from -5 to 5.
		velY = 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (y > Game.HEIGHT)
			handler.removeObject(this);

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
