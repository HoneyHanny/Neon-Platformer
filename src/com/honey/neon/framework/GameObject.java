package com.honey.neon.framework;

import java.util.LinkedList;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected float width, height;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;

	public GameObject(float x, float y, float width, float height, ObjectId id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
	}

	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public ObjectId getObjectId() {
		return id;
	}

	public boolean isFalling() {
		return falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
}
