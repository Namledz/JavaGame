package main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Game game;

	private int chance = 0;

	private int scoreKeep = 0;

	Random r = new Random();

	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	public void tick() {
		scoreKeep++;
		chanceCoin();
		if (scoreKeep >= 250) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			hud.setCoins(hud.getCoins() + 200);

			if (game.diff == 0) { // Normal level.
				// Increase the level of game.
				if (hud.getLevel() == 2) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));

				} else if (hud.getLevel() == 3) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));

				} else if (hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));

				} else if (hud.getLevel() == 7) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));

				} else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 80, -120, ID.EnemyBoss, handler, hud));
					handler.addObject(new UplineBoss(0, 0, ID.UplineBoss, handler, hud, game));

				} else if (hud.getLevel() == 21) {
					for (int i = 0; i < 4; i++) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.BasicEnemy, handler));
					}
				} else if (hud.getLevel() == 23) {
					for (int i = 0; i < 3; i++) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.SmartEnemy, handler));
					}

				} else if (hud.getLevel() == 25) {
					for (int i = 0; i < 4; i++) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.FastEnemy, handler));
					}

				} else if (hud.getLevel() == 27) {
					for (int i = 0; i < 5; i++) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.SmartEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.FastEnemy, handler));
					}
				} else if (hud.getLevel() == 33) {
					for (int i = 0; i < 6; i++) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.FastEnemy, handler));
					}
				} else if (hud.getLevel() == 37) {
					for (int i = 0; i < 5; i++) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.FastEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.BasicEnemy, handler));
					}
					for (int i = 0; i < 4; i++) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.BasicEnemy, handler));
					}
				} else if (hud.getLevel() == 40) {
					for (int i = 0; i < 3; i++) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.SmartEnemy, handler));
					}
				} else if (hud.getLevel() == 45) {
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 80, -120, ID.EnemyBoss, handler, hud));
					handler.addObject(new UplineBoss(0, 0, ID.UplineBoss, handler, hud, game));

				}

			} else if (game.diff == 1) { // Hard level.

				if (hud.getLevel() == 2) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));

				} else if (hud.getLevel() == 3) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));

				} else if (hud.getLevel() == 4) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));

				} else if (hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));

				} else if (hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));

				} else if (hud.getLevel() == 7) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));

				} else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new EnemyBossHard((Game.WIDTH / 2) - 80, -120, ID.EnemyBossHard, handler, hud));
					handler.addObject(new UplineBoss(0, 0, ID.UplineBoss, handler, hud, game));

				} else if (hud.getLevel() == 21) {
					for (int i = 0; i < 4; i++) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.HardEnemy, handler));
					}
				} else if (hud.getLevel() == 23) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
							ID.HardEnemy, handler));
					for (int i = 0; i < 3; i++) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.SmartEnemy, handler));
					}

				} else if (hud.getLevel() == 25) {
					for (int i = 0; i < 4; i++) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.FastEnemy, handler));
						if (i < 2) {
							handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
									ID.HardEnemy, handler));
						}
					}

				} else if (hud.getLevel() == 27) {
					for (int i = 0; i < 3; i++) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.SmartEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.FastEnemy, handler));
						if (i < 2) {
							handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
									ID.HardEnemy, handler));
						}
					}
				} else if (hud.getLevel() == 33) {
					for (int i = 0; i < 7; i++) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.FastEnemy, handler));
					}
				} else if (hud.getLevel() == 37) {
					for (int i = 0; i < 5; i++) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.FastEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.HardEnemy, handler));
					}
					for (int i = 0; i < 4; i++) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.SmartEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.HardEnemy, handler));
					}
				} else if (hud.getLevel() == 40) {
					for (int i = 0; i < 6; i++) {
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 50),
								ID.SmartEnemy, handler));
					}
				}
			}

		}
	}

	public void chanceCoin() {
		this.chance = 200;
		int chances = r.nextInt(this.chance);
		if (chances == 0) {
			int x = r.nextInt(Game.WIDTH - 35);
			int y = r.nextInt(Game.HEIGHT - 70);
//			AudioPlayer.getSound("coins_sound").play();
			handler.addObject(new Coins(x, y, ID.Coins, handler));
		}
	}

}
