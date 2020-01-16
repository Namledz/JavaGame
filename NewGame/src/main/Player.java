package main;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;

	private int timer = 600;

	private int timer2 = 700;

	private boolean touch = false;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		// The player can not get out of the box.
		x = Game.clamp(x, 0, Game.WIDTH - 30);
		y = Game.clamp(y, 0, Game.HEIGHT - 64);

		collision();

		Trail trail = new Trail(x, y, ID.Trail, 24, 24, 0.1f, handler, Color.white);

		handler.addObject(trail);

//		 handler.addObject(new Trail2(0, 0, ID.Trail, 0, 0, 0.009f, handler,
//		 Color.MAGENTA));

		if (HUD.level >= 30 && Shop.undefeateSkin == true) {
			if (timer2 > 0) {
				timer2--;
			} else {
				Shop.undefeateSkin = false;
			}
		}

	}

	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
					|| tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBossBulletHard
					|| tempObject.getId() == ID.EnemyBossBullet) { // tempOject
				// is
				// now
				// BasicEnemy...
				if (getBounds().intersects(tempObject.getBounds())) {
					// collision code. (intersects - when 2 blocks intersect each other => return
					// true else return false)
					HUD.HEALTH -= 2;
					AudioPlayer.getSound("hitting_sound").play();
					Trail2 trail2 = new Trail2(0, 0, ID.Trail, 0, 0, 0.02f, handler, Color.magenta);
					if (timer > 0) {
						handler.addObject(trail2);
						timer--;
					} else {
						timer = 600;
						handler.removeObject(trail2);
					}

				}
			}
			if (tempObject.getId() == ID.EnemyBoss || tempObject.getId() == ID.EnemyBossHard) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (Shop.undefeateSkin == false) {
						HUD.HEALTH -= 6;
						AudioPlayer.getSound("hitting_sound").play();
						Trail2 trail2 = new Trail2(0, 0, ID.Trail, 0, 0, 0.02f, handler, Color.magenta);
						if (timer > 0) {
							handler.addObject(trail2);
							timer--;
						} else {
							timer = 600;
							handler.removeObject(trail2);
						}
					}
				}
			}

			if (tempObject.getId() == ID.UplineBoss) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (Shop.undefeateSkin == false) {
						HUD.HEALTH -= 10;
						AudioPlayer.getSound("hitting_sound").play();
						Trail2 trail2 = new Trail2(0, 0, ID.Trail, 0, 0, 0.02f, handler, Color.magenta);
						if (timer > 0) {
							handler.addObject(trail2);
							timer--;
						} else {
							timer = 600;
							handler.removeObject(trail2);
						}
					}
				}
			}
			if (tempObject.getId() == ID.Coins) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (HUD.level >= 21) {
						HUD.coins += 300;
					} else if (HUD.level < 21) {
						HUD.coins += 200;
					}

					handler.removeObject(tempObject);

				}
			}
			if (Shop.undefeateSkin == true) {
				if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
						|| tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBossBulletHard
						|| tempObject.getId() == ID.EnemyBossBullet) {
					if (getBound2().intersects(tempObject.getBounds())) {
						handler.removeObject(tempObject);
						HUD.coins += 200;
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 24, 24);

		if (Shop.undefeateSkin == true) {
			g.drawOval((int) (x - 18), (int) (y - 19), 60, 60);
			// g.drawRect((int)(x-18), (int)(y-19), 60, 60);
		}

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 24, 24);
	}

	public Rectangle getBound2() {
		return new Rectangle((int) (x - 18), (int) (y - 19), 60, 60);
	}

}
