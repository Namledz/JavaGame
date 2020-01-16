package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {

	private Handler handler;

	Random r = new Random();

	private Color col; 

	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = (r.nextInt(6 - -6) + -6);
		velY = (r.nextInt(6 - -6) + -6);
		
		if(velX==0) velX=1;
		if(velY==0) velY=1;
		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (y <= 0 || y >= Game.HEIGHT - 52)
			velY *= -1;

		if (x <= 0 || x >= Game.WIDTH - 15)
			velX *= -1;

		// when the object touch the bar its go back to another way.

		handler.addObject(new Trail(x, y, ID.Trail, 15, 15, 0.03f, handler, col));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int) x, (int) y, 15, 15);

	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
