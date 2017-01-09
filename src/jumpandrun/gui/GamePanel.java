package jumpandrun.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jumpandrun.entity.Camera;
import jumpandrun.entity.PictureLoader;
import jumpandrun.entity.Player;
import jumpandrun.entity.Solid;
import jumpandrun.entity.Sprite;
import jumpandrun.level.LevelGenerator;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	private boolean game_running = true;

	private long delta = 0;
	private long last = 0;
	private long fps = 0;

	// Levels
	private Vector<Solid> solidBlocks;

	// Player Control Values
	/**
	 * 
	 */

	// Objects
	Player player;
	Vector<Sprite> actors;

	// Managing
	LevelGenerator levelGenerator;
	PictureLoader picLoader;
	Camera cam;

	public static void main(String[] args) {
		// Create JPanel with dimensions 800x600
		new GamePanel(800, 600);
	}

	// Constructor Gamepanel
	public GamePanel(int w, int h) {
		this.setPreferredSize(new Dimension(w, h));
		JFrame frame = new JFrame("Test");
		frame.setLocation(500, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		doInitializations();
		cam = new Camera();
		picLoader = new PictureLoader();
	}

	private void doInitializations() {
		picLoader = new PictureLoader();
		last = System.nanoTime();
		levelGenerator = new LevelGenerator(this);

		solidBlocks = levelGenerator.getSolidBlocks();
		actors = new Vector<>();

		// load images
		BufferedImage[] heli = picLoader.loadPic("pics/heli.png", 4);

		//
		player = new Player(heli, 400, 340, 1, 100, this);
		actors.add(player);
		actors.addAll(solidBlocks);
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (game_running) {
			computeDelta();
			repaint();

			player.doLogic(delta);
			player.move(delta);

			cam.move(delta);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}

	// calculate delta, last and fps
	private void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();

		fps = ((long) 1e9) / delta;
	}

	// draw every sprite
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.red);
		g.drawString("FPS: " + Long.toString(fps), 20, 10);

		if (actors != null) {
			for (Sprite draw : actors) {
				draw.drawObject(g);
				draw.debugdrawObject(g);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.setUp(-1);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.setDown(1);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setLeft(-1);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setRight(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.setUp(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.setDown(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setLeft(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setRight(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public Vector<Solid> getSolidBlocks() {
		return solidBlocks;
	}
}