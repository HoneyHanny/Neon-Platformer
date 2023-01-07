package com.honey.neon.framework;

import java.awt.image.BufferedImage;

import com.honey.neon.window.BufferedImageLoader;

public class Texture {

	private SpriteSheet bs, ps, bb;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage breaking_block = null;

	public BufferedImage player = null;
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] breaking = new BufferedImage[11];

	public Texture() {

		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/res/sprites/textures/block_sheet.png");
			breaking_block = loader.loadImage("/res/sprites/textures/break.png");
			player_sheet = loader.loadImage("/res/sprites/characters/player.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}

		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		bb = new SpriteSheet(breaking_block);

		getTextures();
	}

	private void getTextures() {
		
		// block
		block[0] = bs.grabImage(1, 1, 32, 32); // dirt block
		block[1] = bs.grabImage(2, 1, 32, 32); // grass block

		int brWidth = breaking_block.getWidth();
		int brHeight = breaking_block.getHeight();
		int col = brWidth / 32;
		int row = brHeight / 32;

		// breaking block
		int index = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				if (index == 10)
					break;
				breaking[index] = bb.grabImage(i, j, 32, 32);
				index++;
			}
		}

		
		
	}
}
