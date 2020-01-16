package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

import main.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;

	private Handler handler;

	Random random = new Random();

	private HUD hud;
	private Shop shop;

	private float r, r1, r2, r3;
	private float gr, gr1, gr2, gr3;
	private float b, b1, b2, b3;


	public Menu(Game game, Handler handler, HUD hud, Shop shop) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.shop = shop;

		
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.Menu) {
			// Play button.
			if (mouseOver(mx, my, 285, 200, 330, 100)) {

				game.gameState = STATE.Select;

				AudioPlayer.getSound("menu_sound").play();
				return;

			}

			// Help button.
			if (mouseOver(mx, my, 285, 350, 330, 100)) {
				game.gameState = STATE.Help;

				AudioPlayer.getSound("menu_sound").play();
			}

			// Quit button.
			if (mouseOver(mx, my, 285, 495, 330, 100)) {
				if (game.gameState == STATE.Menu) {
					System.exit(1);

				}
			}
		}

		if (game.gameState == STATE.Select) {
			// normal button.
			if (mouseOver(mx, my, 285, 200, 330, 100)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 16, Game.HEIGHT / 2 - 16, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 20), random.nextInt(Game.HEIGHT - 52),
						ID.BasicEnemy, handler));

				game.diff = 0;

				AudioPlayer.getSound("menu_sound").play();

			}

			// Hard button.
			if (mouseOver(mx, my, 285, 350, 330, 100)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 16, Game.HEIGHT / 2 - 16, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 20), random.nextInt(Game.HEIGHT - 52),
						ID.BasicEnemy, handler));

				game.diff = 1;

				AudioPlayer.getSound("menu_sound").play();
			}

			// Back button.
			if (mouseOver(mx, my, 285, 495, 330, 100)) {
				if (game.gameState == STATE.Select) {
					game.gameState = STATE.Menu;
					AudioPlayer.getSound("menu_sound").play();
					return;
				}
			}
		}

		// Back button for Help.
		if (mouseOver(mx, my, 285, 495, 330, 100)) {
			if (game.gameState == STATE.Help) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
		// Try again button.
		if (mouseOver(mx, my, 285, 495, 330, 100)) {
			if (game.gameState == STATE.End) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				HUD.bounds = 0;
				handler.spd = 5;
				hud.setCoins(0);
				shop.reset();
				shop.undefeateSkin = false;
				AudioPlayer.getSound("menu_sound").play();
			}
		}
	}

	public void mouseRelease(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		// if mouse is in blocks.

		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}

	public void tick() {
		// Tao mau
		r = (float) Game.clamp(random.nextFloat(), 0, 255);
		gr = (float) Game.clamp(random.nextFloat(), 0, 255);
		b = (float) Game.clamp(random.nextFloat(), 0, 255);

		r1 = (float) Game.clamp(random.nextFloat(), 0, 255);
		gr1 = (float) Game.clamp(random.nextFloat(), 0, 255);
		b1 = (float) Game.clamp(random.nextFloat(), 0, 255);

		r2 = (float) Game.clamp(random.nextFloat(), 0, 255);
		gr2 = (float) Game.clamp(random.nextFloat(), 0, 255);
		b2 = (float) Game.clamp(random.nextFloat(), 0, 255);

		r3 = (float) Game.clamp(random.nextFloat(), 0, 255);
		gr3 = (float) Game.clamp(random.nextFloat() / 2f, 0, 255);
		b3 = (float) Game.clamp(random.nextFloat() / 2f, 0, 255);

	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 65);
			Font fnt2 = new Font("arial", 1, 50);

			g.setFont(fnt);
			g.setColor(Color.MAGENTA);
			g.drawString("SP3RM GAME", 223, 110);

			// Play button.
			g.setFont(fnt2);
			g.setColor(new Color(r, gr, b));
			g.fillRect(285, 220, 330, 100);
			g.setColor(Color.black);
			g.fillRect(289, 224, 322, 92);
			g.setColor(Color.cyan);
			g.drawString("Play", 395, 290);

			// Help button.
			g.setFont(fnt2);
			g.setColor(new Color(r1, gr1, b1));
			g.fillRect(285, 360, 330, 100);
			g.setColor(Color.black);
			g.fillRect(289, 364, 322, 92);
			g.setColor(Color.red);
			g.drawString("Help", 395, 430);

			// Quit button.
			g.setFont(fnt2);
			g.setColor(new Color(r2, gr2, b2));
			g.fillRect(285, 495, 330, 100);
			g.setColor(Color.BLACK);
			g.fillRect(289, 499, 322, 92);
			g.setColor(Color.blue);
			g.drawString("Quit", 395, 565);

		} else if (game.gameState == STATE.Game) {
			Font fnt = new Font("arial", 1, 24);
			g.setColor(Color.cyan);
			g.drawRect(750, 565, 120, 50);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Options", 765, 600);

		} else if (game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 75);
			Font fnt2 = new Font("arial", 1, 50);
			Font fnt3 = new Font("arial", 1, 25);

			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("Help", 365, 130);

			g.setFont(fnt2);
			g.setColor(new Color(r2, gr2, b2));
			g.fillRect(285, 495, 330, 100);
			g.setColor(Color.BLACK);
			g.fillRect(289, 499, 322, 92);
			g.setColor(Color.blue);
			g.drawString("Back", 390, 565);
			
			g.setFont(fnt3);
			g.setColor(Color.cyan);
			g.drawString("Use W A S D to move the square.", 250, 230);
			g.setColor(Color.magenta);
			g.drawString("Try to dodge ememies and get high score.", 250, 330);
			g.setColor(Color.yellow);
			g.drawString("Collect coins to buy stuff in Shop.", 250, 430);
			
		} else if (game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 80);
			Font fnt2 = new Font("arial", 1, 55);
			Font fnt3 = new Font("arial", 1, 40);

			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("Game Over!", 225, 140);

			g.setFont(fnt3);
			g.setColor(Color.yellow);
			g.drawString("You lost with a score of: " + hud.getScore(), 180, 360);

			g.setFont(fnt2);
			g.setColor(new Color(r2, gr2, b2));
			g.fillRect(285, 495, 330, 100);
			g.setColor(Color.BLACK);
			g.fillRect(289, 499, 322, 92);
			g.setColor(Color.blue);
			g.drawString("Try Again", 324, 565);

		} else if (game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 55);
			Font fnt2 = new Font("arial", 1, 45);

			g.setFont(fnt);
			g.setColor(Color.pink);
			g.drawString("SELECT DIFFICULTY", 170, 110);

			// Normal button.
			g.setFont(fnt2);
			g.setColor(new Color(r, gr, b));
			g.fillRect(285, 220, 330, 100);
			g.setColor(Color.black);
			g.fillRect(289, 224, 322, 92);
			g.setColor(Color.cyan);
			g.drawString("Normal", 368, 288);

			// Hard button.
			g.setFont(fnt2);
			g.setColor(new Color(r1, gr1, b1));
			g.fillRect(285, 360, 330, 100);
			g.setColor(Color.black);
			g.fillRect(289, 364, 322, 92);
			g.setColor(Color.red);
			g.drawString("Hard", 395, 430);

			// Back button.
			g.setFont(fnt2);
			g.setColor(new Color(r2, gr2, b2));
			g.fillRect(285, 495, 330, 100);
			g.setColor(Color.BLACK);
			g.fillRect(289, 499, 322, 92);
			g.setColor(Color.blue);
			g.drawString("Back", 390, 565);

		}
	}

}
