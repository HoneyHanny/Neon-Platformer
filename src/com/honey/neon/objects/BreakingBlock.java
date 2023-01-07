package com.honey.neon.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.honey.neon.framework.GameObject;
import com.honey.neon.framework.ObjectId;
import com.honey.neon.framework.Texture;
import com.honey.neon.window.Animation;
import com.honey.neon.window.Game;

public class BreakingBlock extends GameObject{

	private Texture tex;
	private Animation breaking;
	
	public BreakingBlock(float x, float y, float width, float height, ObjectId id) {
		super(x, y, width, height, id);
		tex = Game.getInstance();
		breaking = new Animation(10, tex.breaking[0], tex.breaking[1], tex.breaking[2], tex.breaking[3],
				tex.breaking[4], tex.breaking[5], tex.breaking[6], tex.breaking[7], tex.breaking[8], tex.breaking[9]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		breaking.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		breaking.drawAnimation(g, x, y);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

}
