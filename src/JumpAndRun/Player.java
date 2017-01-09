package JumpAndRun;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Sprite
{
	//speeds
	private double spx = 0;
	private double spy = 0;
	
	public Player(BufferedImage[] image, double x, double y, int size, long delay, GamePanel p)
	{
		super(image, x, y, size, delay, p);
	}

	@Override
	public void drawObject(Graphics g)
	{
		//evtl. statt parent.cam.x getDrift() einbauen 
		g.drawImage(getPic(), (int) x, (int) y, (int) width, (int) height, null);
	}
	
	//Control player
	public void move(int up, int down, int left, int right, long delta)
	{
		//calculate speeds
		spx = (left + right) * 100 * (delta/1e9);
		spy = (up + down) * 100 * (delta/1e9);
		
		//calculate new x and y positions
		Rectangle.Double newPositionX = new Rectangle.Double(x+spx, y, width, height);
		Rectangle.Double newPositionY = new Rectangle.Double(x, y+spy, width, height);
		
		//Check collision with blocks
		for (Solid block : parent.solidBlocks)
		{
			if (newPositionX.intersects(block))
			{
				if (spx > 0)
				{
					x = block.getMinX() - width;
				}
				else if (spx < 0)
				{
					x = block.getMaxX();
				}
				spx = 0;
			}
			if (newPositionY.intersects(block))
			{
				if (spy > 0)
				{
					y = block.getMinY() - height;
				}
				else if (spy < 0)
				{
					y = block.getMaxY();
				}
				spy = 0;
			}
			x += spx;
			y += spy;
		}
	}
	
	public void doLogic()
	{
		
	}
}