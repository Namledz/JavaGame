package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 6691247796639148462L;

	// main class.

	public static final int WIDTH = 900, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;

	public static boolean paused = false;
	public int diff = 0;
	public static int frames = 0;
	// 0 = normal.
	// 1 = hard.

	private Handler handler;
	private Random r;

	private Spawn spawner;
	private HUD hud;
	private Shop shop;

	private Menu menu;

	public enum STATE {
		Menu, Game, Help, Select, Shop, End , Options
	};

	public static STATE gameState = STATE.Menu; // if it is STATE.Menu we can not play game . it must be STATE.Game.
	  
	public Game() {
		// A constructor of Game.

		handler = new Handler();
		// Place handler method first window for the game know what is handler to call
		// that method.
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud, shop);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		;

		// Load audio
		AudioPlayer.load();

		AudioPlayer.getMusic("music").loop();

		// Create a window.
		new Window(WIDTH, HEIGHT, "Funny Game !", this);

		spawner = new Spawn(handler, hud, this);
		r = new Random();

		// Beginning of the game.
		if (gameState == STATE.Game) { // Can play the game.
			
		} else {
			for (int i = 0; i < 15; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		// Start the run- method down there.
	}

	public synchronized void stop() {
		try {
			thread.join(); // Stop the thread.
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis(); // mili seconds
		while (running) {
			long now = System.nanoTime(); // thoi gian hien tai.
			delta += (now - lastTime) / ns; // nano seconds
			lastTime = now;
			while (delta >= 1) {	
				tick();
				// Call the tick method.
				delta--;
			}
			if (running)
				render();
			// Call the render method.

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}

		}
		stop();
	}

	private void tick() {

		if (gameState == STATE.Game) { // We can update heads up display , spawn , stuff.. Can play the game.

			// When game is not paused. everything like normal
			if (!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				// Call the handler.tick method.

				if (HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
					for (int i = 0; i < 15; i++) {
						handler.addObject(
								new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}
				}
			}

		} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select
				|| gameState == STATE.Help) {
			menu.tick();
			handler.tick();
			// Call the handler.tick method.
		}

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);


		if (gameState == STATE.Game) {
			 // can play the game.
			hud.render(g);
			handler.render(g);
			menu.render(g);
			
		} else if (gameState == STATE.Shop) {
			shop.render(g);

		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End
				|| gameState == STATE.Select) {
			// Display the menu.
			menu.render(g);
			handler.render(g);
		}
		if (paused) {
			Font fnt = new Font("arial", 1, 45);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.CYAN);
			g.drawString("PAUSED !", 350, 100);
			g.setFont(fnt2);
			g.drawString("Press P to continue the game ... ", 250, 400);

		}
		g.dispose();
		bs.show();

	}

	public static float clamp(float var, float min, float max) {
		// Method for the object can not get out of the box.

		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}

	public static void main(String[] args) {
		new Game();
	}

}
