package JumpAndRun;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Vector;

public class Sprite extends Rectangle.Double
{
	long delay = 0;
	long animation = 0;
	GamePanel parent;
	BufferedImage[] pics;
	int currentpic = 0;
	int drift = 1;
	
	//Create sprite with an animation
	public Sprite(BufferedImage[] image, double x, double y, int size, long delay, GamePanel p)
	{
		pics = image;
		this.x = x;
		this.y = y;
		this.delay = delay;
		this.width = pics[0].getWidth() * size;
		this.height = pics[0].getHeight() * size;
		parent = p;
	}
	
	//Create sprite without an animation
	public Sprite(BufferedImage image, double x, double y, int size, GamePanel p)
	{
		pics = new BufferedImage[1];
		pics[0] = image;
		this.x = x;
		this.y = y;
		this.width = pics[0].getWidth() * size;
		this.height = pics[0].getHeight() * size;
		parent = p;
	}
	
	//draw sprite relative to the camera
	public void drawObject(Graphics g)
	{
		//evtl. statt parent.cam.x getDrift() einbauen 
		g.drawImage(getPic(), (int) x, (int) y, (int) width, (int) height, null);
	}

	//draw rect for debugging
	public void debugdrawObject(Graphics g)
	{
		g.drawRect((int) (x), (int) y,(int) width,(int) height);
	}
	
	//animate the sprite
	public void animate(long delta)
	{
		animation += (delta/1000000);
		if (animation > delay)
		{
			animation = 0;
			computeAnimation();
		}
	}

	//compute what frame to show
	private void computeAnimation()
	{
		currentpic++;
		if (currentpic>=pics.length)
		{
			currentpic = 0;
		}
	}
	
	protected BufferedImage getPic()
	{
		return pics[currentpic];
	}
	
	public void doLogic(long delta)
	{
		animate(delta);
	}
}