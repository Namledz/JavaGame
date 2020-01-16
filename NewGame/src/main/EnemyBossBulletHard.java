package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBulletHard extends GameObject {

	private Handler handler;

	Random r = new Random();

	Game game;

	public EnemyBossBulletHard(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = (r.nextInt(5 - -5) + -5); // velX random from -5 to 5.
		velY = 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		int chance = r.nextInt(15);
		if (chance == 0) {
			if (velX < 0) {
				velX = -(r.nextInt(7) + 1) * -1;
			} else if (velX > 0) {
				velX = (r.nextInt(7) + 1) * -1;
			}
		}

		if (y > Game.HEIGHT || y < 0) {
			handler.removeObject(this);
		}

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
