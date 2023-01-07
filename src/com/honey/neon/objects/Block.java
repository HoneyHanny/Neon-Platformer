package com.honey.neon.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.honey.neon.framework.GameObject;
import com.honey.neon.framework.ObjectId;
import com.honey.neon.framework.Texture;
import com.honey.neon.window.Game;

public class Block extends GameObject {

	Texture tex = Game.getInstance();
	private int type;
	
	public Block(float x, float y, float width, float height, int type, ObjectId id) {
		super(x, y, width, height, id);
		this.type = type;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		if (type == 0) // dirt block
			g.drawImage(tex.block[0], (int) x, (int) y, null);
		else if (type == 1) // grass block
			g.drawImage(tex.block[1], (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

}