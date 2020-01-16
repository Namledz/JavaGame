package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class UplineBoss extends GameObject {

	Handler handler;

	HUD hud;

	Game game;

	private int timer = 65;

	private int timer2 = 70;

	public UplineBoss(int x, int y, ID id, Handler handler, HUD hud, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		this.game = game;

		velX = 0;
		velY = 2;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (timer <= 0) {
			velY = 0;
		} else {
			timer--;
		}

		if (hud.getLevel() >= 20 && hud.getLevel() <= 21) {
			if (timer2 <= 0) {
				velY = -1;
				if (hud.getLevel() == 20) {
					handler.removeObject(this);
				}
			} else {
				timer2--;
			}
		}

	}

	@Override
	public void render(Graphics g) {

		if (game.diff == 0) {
			g.setColor(Color.RED);
		} else if (game.diff == 1) {
			g.setColor(Color.yellow);
		}

		g.fillRect((int) x, (int) y, 1280, 5);
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 1280, 5);
	}

}
