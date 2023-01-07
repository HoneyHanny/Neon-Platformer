package com.honey.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.honey.neon.framework.GameObject;
import com.honey.neon.framework.ObjectId;
import com.honey.neon.framework.Texture;
import com.honey.neon.window.Animation;
import com.honey.neon.window.Game;
import com.honey.neon.window.Handler;

public class Player extends GameObject {

	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;

	private Handler handler;
	private Animation walkRight;
	private Animation walkLeft;

	Texture tex = Game.getInstance();

	public Player(float x, float y, float width, float height, ObjectId id, Handler handler) {
		super(x, y, width, height, id);
		this.handler = handler;

		// walkRight = new Animation();
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (falling || jumping) {
			velY += gravity;

			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}

		collision(object);
	}

	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getObjectId() == ObjectId.Block) {

				// Top
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + tempObject.getHeight();
					velY = 0;
				}

				// Bottom
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}

				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
					Game.bg.stop = true;
				} else {
					Game.bg.stop = false;
				}
					
				// Left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + width;
					Game.bg.stop = true;
				} else {
					Game.bg.stop = false;
				}

			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) (x + width / 4), (int) (y + height / 2), (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) (x + width / 4), (int)y, (int)width / 2, (int) height / 2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) (x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

}
