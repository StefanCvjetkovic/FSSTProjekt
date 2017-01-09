package jumpandrun.entity;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import jumpandrun.gui.GamePanel;

public class Solid extends Sprite
{
	public Solid(BufferedImage image, double x, double y, int size, long delay, GamePanel p)
	{
		super(image, x, y, size, p);
	}
	
	public Solid(BufferedImage image, double x, double y, int size, GamePanel p)
	{
		super(image, x, y, size, p);
	}
}