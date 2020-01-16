package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {

	Handler handler;
	HUD hud;

	// Money
	public int B1 = 1000;
	public int B2 = 1200;
	public int B3 = 1500;
	public int B4 = 200;
	public int B5 = 8000;
	public int B6 = 2400;

	// Stuff
	public int X6 = 1500;

	// Level
	private int L1 = 1;
	private int L2 = 1;
	private int L3 = 1;
	private int L5 = 1;

	public static boolean undefeateSkin = false;

	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 65);
		Font fnt2 = new Font("arial", 1, 28);
		Font fnt3 = new Font("arial", 1, 18);
		Font fnt4 = new Font("arial", 1, 22);
		Font fnt5 = new Font("arial", 1, 27);

		g.setFont(fnt);
		g.setColor(Color.CYAN);
		g.drawString("Shop", 375, 100);

		g.setFont(fnt4);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + hud.getScore(), 50, 60);
		g.setColor(new Color(222, 134, 31));
		g.drawString("Coins: " + hud.getCoins(), 50, 100);
		g.setFont(fnt2);
		g.setColor(Color.GRAY);
		g.drawString("Press Space to go back", 295, 590);

		// Box 1
		g.setFont(fnt3);
		g.setColor(new Color(216, 31, 222));
		g.drawString("Upgrade Health", 108, 225);
		g.drawString("Level: " + L1, 140, 263);
		g.drawString("Cost: " + B1, 132, 302);
		g.drawRect(75, 190, 200, 130);

		// Box 2
		g.setFont(fnt3);
		g.setColor(Color.red);
		g.drawString("Upgrade Speed", 382, 225);
		g.drawString("Level: " + L2, 415, 263);
		g.drawString("Cost: " + B2, 405, 302);
		g.drawRect(350, 190, 200, 130);

		// Box 3
		g.setFont(fnt3);
		g.setColor(Color.pink);
		g.drawString("Refill Health", 670, 225);
		g.drawString("Level: " + L3, 689, 263);
		g.drawString("Cost: " + B3, 680, 302);
		g.drawRect(625, 190, 200, 130);

		// Box 4
		g.setFont(fnt3);
		g.setColor(Color.green);
		g.drawString("Health", 145, 406);
		g.drawString("+ 50 Health ", 123, 444);
		g.drawString("Cost: " + B4, 135, 481);
		g.drawRect(75, 370, 200, 130);

		// Box 5
		g.setFont(fnt3);
		if (hud.getLevel() >= 27) {
			g.setColor(Color.yellow);
			g.drawString("Undefeatable in 20s", 366, 406);
		} else if (hud.getLevel() < 27) {
			g.setColor(Color.gray);
			g.drawString("Require Level 27", 375, 406);
		}
		g.drawString("Super Sayan", 395, 444);
		g.drawString("Cost: " + B5, 403, 481);

		g.drawRect(350, 370, 200, 130);

		// Box 6
		g.setFont(fnt3);
		g.setColor(Color.blue);
		g.drawString("Buy Score", 680, 406);
		g.drawString("+ 1500 Score", 667, 444);
		g.drawString("Cost: " + B6, 680, 481);
		g.drawRect(625, 370, 200, 130);

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		// Box 1
		if (mx >= 75 && mx <= 275) {
			if (my >= 190 && my <= 320) {
				// Selected box 1.
				if (hud.getCoins() >= B1) {
					hud.setCoins(hud.getCoins() - B1);
					B1 += 400;
					L1++;
					hud.bounds += 20; // Tang do rong mau
				}
			}
		}

		// Box 2
		if (mx >= 350 && mx <= 550) {
			if (my >= 190 && my <= 320) {
				// Selected box 2.
				if (hud.getCoins() >= B2) {
					hud.setCoins(hud.getCoins() - B2);
					B2 += 400;
					L2++;
					handler.spd++; // Tang toc do ng choi
				}
			}
		}
		// Box 3
		if (mx >= 625 && mx <= 825) {
			if (my >= 190 && my <= 320) {
				// Selected box 3.
				if (hud.getCoins() >= B3) {
					hud.setCoins(hud.getCoins() - B3);
					B3 += 400;
					L3++;
					HUD.HEALTH = 100 + (HUD.bounds / 2); // Hoi day mau
				}
			}
		}
		// Box 4
		if (mx >= 75 && mx <= 275) {
			if (my >= 370 && my <= 500) {
				// Selected box 4.
				if (hud.getCoins() >= B4) {
					hud.setCoins(hud.getCoins() - B4);
					B4 += 100;
					HUD.HEALTH += 25; // Cong them 50 mau.
				}
			}
		}
		// Box 5
		if (hud.getLevel() >= 27) {
			if (mx >= 350 && mx <= 550) {
				if (my >= 370 && my <= 500) {
					// Selected box 5.
					if (hud.getCoins() >= B5) {
						hud.setCoins(hud.getCoins() - B5);
						B5 += 1500;
						L5++;
						undefeateSkin = true; // Hoa Sieu nhan
					}
				}
			}
		}
		// Box 6
		if (mx >= 625 && mx <= 825) {
			if (my >= 370 && my <= 500) {
				// Selected box 6.
				if (hud.getCoins() >= B6) {
					hud.setCoins(hud.getCoins() - B6);
					hud.setScore(hud.getScore() + 1500);
					B6 += 800;
				}
			}
		}

	}

	public void mouseRelease(MouseEvent e) {

	}

	public void reset() {
		this.B1 = 1000;
		this.B2 = 1200;
		this.B3 = 1500;
		this.B4 = 200;
		this.B5 = 8000;
		this.B6 = 2400;
		this.L1 = 1;
		this.L2 = 1;
		this.L3 = 1;
		this.L5 = 1;
		this.X6 = 1500;
		this.undefeateSkin = false;
	}

	public boolean getUndefeateSkin() {
		return undefeateSkin;
	}
}
