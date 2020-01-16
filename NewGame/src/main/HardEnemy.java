package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject {

	private Handler handler;

	private Random r = new Random();

	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = 5;
		velY = 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (y <= 0 || y >= Game.HEIGHT - 52) {
			if (velY < 0) {
				velY = -(r.nextInt(7) + 1) * -1;
			} else if (velY > 0) {
				velY = (r.nextInt(7) + 1) * -1;
			}
		}

		if (x <= 0 || x >= Game.WIDTH - 20) {
			if (velX < 0) {
				velX = -(r.nextInt(7) + 1) * -1;
			} else if (velX > 0) {
				velX = (r.nextInt(7) + 1) * -1;
			}

		}

		// when the object touch the bar , it goes back to another way.

		handler.addObject(new Trail((int) x, (int) y, ID.Trail, 12, 12, 0.1f, handler, Color.yellow));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, 12, 12);

	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 12, 12);
	}

}
