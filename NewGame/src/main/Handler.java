package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	//Handle all object of the game. 

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	// A linkedList of Objects.

	public int spd = 4;

	public void tick() {
		try {
			for (int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);

				tempObject.tick();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	public void render(Graphics g) {
		try {
			for (int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);

				tempObject.render(g);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
		// To add Objects.
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);

		// To remove Objects.
	}

	public void clearEnemies() { // Clear all enemy and add another player into old position.
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			if (tempObject.getId() == ID.Player) {
				object.clear();
				if (Game.gameState != Game.STATE.End) {
					addObject(new Player(tempObject.getX(), tempObject.getY(), ID.Player, this));
				}
			}
		}
	}
}
