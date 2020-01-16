package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	// Health Bar

	// To upgrade the health bar.
	public static int bounds = 0;

	public static float HEALTH = 100;

	private float greenValue = 255;

	private int score = 0;
	public static int level = 1;
	public static int coins = 0;

	public void tick() {

		HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds / 2));

		greenValue = HEALTH * 2;

		greenValue = Game.clamp(greenValue, 0, 255);

		score++;
	}

	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 18);
		g.setColor(Color.GRAY);
		g.fillRect(20, 20, 200 + bounds, 32);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect(20, 20, (int) HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(20, 20, 200 + bounds, 32);

		g.setFont(fnt);
		g.drawString("Score: " + score, 20,555);
		g.drawString("Level: " + level, 20, 585);
		g.drawString("Coins: " + coins, 20, 615);
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

}
