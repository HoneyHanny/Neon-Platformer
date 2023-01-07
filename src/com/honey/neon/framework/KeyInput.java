package com.honey.neon.framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.honey.neon.window.Handler;

public class KeyInput implements KeyListener {

	private Handler handler;

	private int speed = 5;
	private int playerVel;
	private int[] velocity = new int[2];
	private int[] bgVel = new int[2];

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// if (velocity[0] + velocity[1] == 0) {
		// 	Game.bg.setVelX(0);
		// } else {
		// 	if (key == KeyEvent.VK_D)
		// 		bgVel[0] = -1;
		// 	if (key == KeyEvent.VK_A)
		// 		bgVel[1] = 1;
		// 	if (key == KeyEvent.VK_A || key == KeyEvent.VK_D)
		// 		Game.bg.setVelX(bgVel[0] + bgVel[1]);
		// }
		
		
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getObjectId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D) velocity[0] = speed;
				if (key == KeyEvent.VK_A) velocity[1] = -speed;
				if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
					tempObject.setVelY(-10);
					tempObject.setJumping(true);
				}
				
				playerVel = velocity[0] + velocity[1];
				tempObject.setVelX(playerVel);
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		// if (velocity[0] + velocity[1] == 0) {
		// 	Game.bg.setVelX(0);
		// } else {
		// 	if (key == KeyEvent.VK_D)
		// 		bgVel[0] = -1;
		// 	if (key == KeyEvent.VK_A)
		// 		bgVel[1] = 1;
		// 	if (key == KeyEvent.VK_A || key == KeyEvent.VK_D)
		// 		Game.bg.setVelX(bgVel[0] + bgVel[1]);
		// }

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getObjectId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D) velocity[0] = 0;
				if (key == KeyEvent.VK_A) velocity[1] = 0;

				playerVel = velocity[0] + velocity[1];
				tempObject.setVelX(playerVel);
			}
		}		
	}
	
}
