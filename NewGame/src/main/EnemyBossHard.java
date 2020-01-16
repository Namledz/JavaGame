package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossHard extends GameObject {

	private Handler handler;

	private int timer = 70;

	private int timer2 = 50;

	private int timer3 = 40;

	private HUD hud;

	private int chance = 10;

	Random r = new Random();

	public EnemyBossHard(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);

		this.handler = handler;
		this.hud = hud;

		velX = 0;
		velY = 2;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (timer <= 0) {
			velY = 0; // if timer = 0 , stop the block ( enemy boss).
		} else {
			timer--;
		}

		if (timer <= 0)
			timer2--; // When enemy boss has velY = 0 , start decline timer2 .
		if (timer2 <= 0) {
			if (velX == 0) { // When timer2 = 0 , set velX = 3 to enemy boss can move .
				velX = 2;
			}
			if (velX > 0) {
				velX += 0.005f;
			} else if (velX < 0) {
				velX -= 0.005f;
			}

			velX = Game.clamp(velX, -6, 6);

			if (hud.getLevel() >= 44) {
				chance = 8;
			}

			int spawn = r.nextInt(chance);
			if (spawn == 0) {
				EnemyBossBulletHard enemyBossBulletHard = new EnemyBossBulletHard((int) x + 70, (int) y + 70,
						ID.EnemyBossBulletHard, handler);
				handler.addObject(enemyBossBulletHard);
				if (hud.getLevel() == 21 || hud.getLevel() == 51) {
					handler.removeObject(enemyBossBulletHard);
				}
			}
		}

		if (x <= 0 || x >= Game.WIDTH - 180)
			velX *= -1;

		// when the object touch the bar , it goes back to another way.

		if ((hud.getLevel() >= 20 && hud.getLevel() <= 21) || (hud.getLevel() >= 50 && hud.getLevel() <= 51)) {
			if (timer3 <= 0) {
				velY = -1; // if timer = 0 , stop the block ( enemy boss).
				if (hud.getLevel() == 21 || hud.getLevel() == 51) {
					handler.removeObject(this);
				}
			} else {
				timer3--;
			}
		}

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int) (x + 20), (int) y, 100, 70);
		g.fillRect((int) (x - 10), (int) (y + 50), 60, 40);
		g.fillRect((int) (x + 90), (int) (y + 50), 60, 40);
		g.setColor(Color.black);
		g.fillRect((int) (x + 10), (int) (y + 70), 120, 20);
		g.fillOval((int) (x + 35), (int) (y + 20), 15, 15);
		g.fillOval((int) (x + 90), (int) (y + 20), 15, 15);
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 160, 90);
	}

	public int getTimer() {
		return this.timer;
	}

}
