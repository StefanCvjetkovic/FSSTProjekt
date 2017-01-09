package jumpandrun.level;
import java.awt.image.BufferedImage;
import java.util.Vector;

import jumpandrun.entity.PictureLoader;
import jumpandrun.entity.Solid;
import jumpandrun.gui.GamePanel;

public class LevelGenerator
{
	PictureLoader picLoader;
	Vector<Solid> solidBlocks;
	
	public LevelGenerator(GamePanel parent)
	{
		//load images
		picLoader = new PictureLoader();
		BufferedImage block = picLoader.loadPic("pics/block.png");
		
		//build the level
		solidBlocks = new Vector<>();
		solidBlocks.add(new Solid(block, 384, 384, 2, parent));
		solidBlocks.add(new Solid(block, 416, 384, 2, parent));
		solidBlocks.add(new Solid(block, 448, 384, 2, parent));
		solidBlocks.add(new Solid(block, 448, 352, 2, parent));
		solidBlocks.add(new Solid(block, 352, 352, 2, parent));
		solidBlocks.add(new Solid(block, 288, 352, 4, parent));
	}

	public Vector<Solid> getSolidBlocks()
	{
		return solidBlocks;
	}
}