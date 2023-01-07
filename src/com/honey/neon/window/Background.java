package com.honey.neon.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.honey.neon.objects.Player;

public class Background {

	public boolean stop = true;
	
	private float x = 0, y = 0;
	private float x2 = x + Game.WIDTH, y2 = 0;
	private float velX = 0, velY = 0;

	private Player player;
	private BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage image1;
	private BufferedImage image2; 

	public Background(Player player) {
		this.player = player;
		image1 = image2 = loader.loadImage("/res/sprites/backgrounds/background2.jfif");
	}

	public Background(String path, Player player) {
		this.player = player;
		image1 = image2 = loader.loadImage(path);
	}

	public void tick() {
		// if (x > 0) {
		// 	x2 = x - Game.WIDTH;
		// } else if (x < 0) {
		// 	x = Game.WIDTH;
		// } else if (x > Game.WIDTH) {
		// 	x = 0;
		// } else {
		// 	x2 = x + Game.WIDTH;
		// }
		
		if (player.getVelX() == 0) {
			velX = 0;
		} else if (player.getVelX() < 0) {
			velX = 1;
		} else if (player.getVelX() > 0) {
			velX = -1;
		}

		if (stop) velX = 0;
		
		x += velX;
		
		if (x < 0) {
			x = Game.WIDTH;
		} else if (x > 0 && x < Game.WIDTH) {
			x2 = x - Game.WIDTH;
		} else if (x > Game.WIDTH) {
			x = 0;
		} else {
			x2 = x + Game.WIDTH;
		}
	}

	public void render(Graphics g) {
		g.drawImage(image1, (int)x, (int)y, Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(image2, (int)x2, (int)y2, Game.WIDTH, Game.HEIGHT, null);

		// BufferedImage scaledImage1 = image1;
		// BufferedImage scaledImage2 = image2;

		// Graphics2D g2d1 = scaledImage1.createGraphics();
		// Graphics2D g2d2 = scaledImage2.createGraphics();
		// g2d1.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		// g2d2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		// g2d1.drawImage(scaledImage1, (int)x, (int)y, Game.WIDTH, Game.HEIGHT, null);
		// g2d2.drawImage(scaledImage2, (int)x + Game.WIDTH, (int)y, Game.WIDTH, Game.HEIGHT, null);

	}

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

	public BufferedImage getImage() {
		return image1;
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
	
}
