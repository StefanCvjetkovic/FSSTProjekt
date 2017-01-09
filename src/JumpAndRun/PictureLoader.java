package JumpAndRun;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class PictureLoader
{
	//load an animated picture
	public BufferedImage[] loadPic(String path, int pics)
	{
		BufferedImage[] anim = new BufferedImage[pics];
		BufferedImage source = null;
		
		URL pic_url = getClass().getClassLoader().getResource(path);

		try {
			source = ImageIO.read(pic_url);
		} catch (IOException e) {
		}

		for (int x = 0; x < pics; x++) {
			anim[x] = source.getSubimage(x * source.getWidth() / pics, 0, source.getWidth() / pics, source.getHeight());
		}
		return anim;
	}
		
	//load a picture
	public BufferedImage loadPic(String path)
	{
		BufferedImage source = null;
			
		URL pic_url = getClass().getClassLoader().getResource(path);
		try {
			source = ImageIO.read(pic_url);
		} catch (IOException e) {}
		return source;
	}
}
