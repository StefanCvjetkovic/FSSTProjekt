package jumpandrun.test;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.junit.Test;

public class TestLineCollision {

	@Test
	public void test() {
		Line2D.Double line = new Line2D.Double(0, 100, 100, 100);
		Rectangle2D.Double rect = new Rectangle2D.Double(50, 75, 25, 50);
		
		boolean intersects = line.intersects(rect);
		assertEquals(true, intersects);
	}

}
