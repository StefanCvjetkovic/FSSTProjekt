package JumpAndRun;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	boolean game_running = true;
	
	long delta = 0;
	long last = 0;
	long fps = 0;
	
	//Levels
	Vector<Solid> solidBlocks;

	//Player Control Values
	int up = 0;
	int down = 0;
	int left = 0;
	int right = 0;
	
	//Objects
	Player player;
	Vector<Sprite> actors;

	//Managing
	LevelGenerator levelGenerator;
	PictureLoader picLoader;
	Camera cam;

	public static void main(String[] args)
	{
		//Create JPanel with dimensions 800x600
		new GamePanel(800, 600);
	}

	//Constructor Gamepanel
	public GamePanel(int w, int h)
	{
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

	private void doInitializations()
	{
		picLoader = new PictureLoader();
		last = System.nanoTime();
		levelGenerator = new LevelGenerator(this);
		
		solidBlocks = levelGenerator.getSolidBlocks();
		actors = new Vector<>();
		
		//load images
		BufferedImage[] heli = picLoader.loadPic("pics/heli.png", 4);
		
		//
		player = new Player(heli, 400, 340, 1, 100, this);
		actors.add(player);
		actors.addAll(solidBlocks);
		Thread t = new Thread(this);
		t.start();
	}


	@Override
	public void run()
	{
		while (game_running)
		{
			computeDelta();
			repaint();

			player.doLogic(delta);
			player.move(up, down, left, right, delta);

			cam.move(delta);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
	}

	// calculate delta, last and fps
	private void computeDelta()
	{
		delta = System.nanoTime() - last;
		last = System.nanoTime();

		fps = ((long) 1e9) / delta;
	}

	//draw every sprite
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.setColor(Color.red);
		g.drawString("FPS: " + Long.toString(fps), 20, 10);

		if (actors != null)
		{
			for (Sprite draw : actors)
			{
				draw.drawObject(g);
				draw.debugdrawObject(g);
			}
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = -1;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = -1;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}
}