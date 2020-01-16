package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

	private Handler handler;

	private GameObject player;

	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) { // set player to an object player.
				player = handler.object.get(i);
			}
		}

	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		float diffX = x - player.getX() - 8; // the distance between x of an enemy and x of a player.
		float diffY = y - player.getY() - 8; // the distance between y of an enemy and y of a player.
		float distance = (float) Math
				.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

		velX = (float) ((-1.0 / distance) * diffX);
		velY = (float) ((-1.0 / distance) * diffY);

		if (y <= 0 || y >= Game.HEIGHT - 52)
			velY *= -1;

		if (x <= 0 || x >= Game.WIDTH - 20)
			velX *= -1;

		// when the object touch the bar , it goes back to another way.

		handler.addObject(new Trail(x, y, ID.Trail, 12, 12, 0.02f, handler, Color.green));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, 12, 12);

	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 12, 12);
	}

}
