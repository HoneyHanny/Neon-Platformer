package com.honey.neon.window;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import com.honey.neon.framework.KeyInput;
import com.honey.neon.framework.ObjectId;
import com.honey.neon.framework.Texture;
import com.honey.neon.objects.Block;
import com.honey.neon.objects.BreakingBlock;
import com.honey.neon.objects.Player;

public class Game extends Canvas implements Runnable {
	
	public static final String TITLE = "Neon Platformer Prototype";
	public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = screenSize.width;
	public static final int HEIGHT = screenSize.height;
	public static final float SCREEN_RATIO_X = 1920f / WIDTH;
	public static final float SCREEN_RATIO_Y = 1080f / HEIGHT;

	private boolean running = false;
	
	private Player player;
	public static Background bg;
	private Thread thread;
	private Handler handler;
	private Camera cam;
	private static Texture tex;
	private BufferedImage level = null;

	public Game() {

		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/res/sprites/levels/level1.png");

		handler = new Handler();
		cam = new Camera(0, 0);

		loadImageLevel(level);
		bg = new Background("/res/sprites/backgrounds/background2.jfif", player);

		// handler.addObject(new Player(100, 100, 32, 64, ObjectId.Player, handler));
		// handler.createLevel();
		setCurosrVisible(true);

		addKeyListener(new KeyInput(handler));

	}

	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		// long timer = System.currentTimeMillis();
		// int updates = 0;
		// int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				// updates++;
				delta--;
			}
			render();
			// frames++;

			// if (System.currentTimeMillis() - timer > 1000) {
			// 	timer += 1000;
				// System.out.println("FPS: " + frames + " TICKS: " + updates);
			// 	frames = 0;
			// 	updates = 0;
			// }
		}

		stop();
	}

	private void tick() {
		handler.tick();
		bg.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getObjectId() == ObjectId.Player) {
				cam.tick(handler.object.get(i));
			}
		}
	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, getWidth(), getHeight());
		
		
		bg.render(g);
		g2d.translate(cam.getX(), cam.getY()); // begin of cam
		
		handler.render(g);
		
		// g2d.translate(-cam.getX(), -cam.getY()); // end of cam

		g2d.dispose();
		g.dispose();
		bs.show();
		
	}

	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = pixel & 0xff;

				if (red == 255 && green == 255 && blue == 255)
					handler.addObject(new Block(xx * 32, yy * 32, 32, 32, 0, ObjectId.Block));
				else if (red == 0 && green == 0 && blue == 255) {
					player = new Player(xx * 32, yy * 32, 32, 62, ObjectId.Player, handler);
					handler.addObject(player);
				}
				else if (red == 195 && green == 195 && blue == 195)
					handler.addObject(new Block(xx * 32, yy * 32, 32, 32, 1, ObjectId.Block));
				else if (red == 0 && green == 255 && blue == 0) {
					handler.addObject(new BreakingBlock(xx * 32, yy * 32, 32, 32, ObjectId.BreakingBlock));
				}
			}
		}
	}

	public void setCurosrVisible(boolean visible) {
		if (visible) {
			int[] pixels = new int[16 * 16];
			Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
			Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0),
					"invisibleCursor");
			setCursor(transparentCursor);		
		} else {
			setCursor(null);
		}
	}

	public static Texture getInstance() {
		return tex;
	}

	public static void main(String[] args) {
		new Window(WIDTH, HEIGHT, TITLE, new Game());
	}

}
