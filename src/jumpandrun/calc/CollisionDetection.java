package jumpandrun.calc;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import jumpandrun.entity.Player;

public class CollisionDetection
{
    private Player obj;

    public CollisionDetection(Player obj)
    {
	this.obj = obj;
    }

    /**
     * 
     * @param speedX
     *            The x speed
     * @param speedY
     *            The y speed
     * @param rect
     *            The rect to check collision
     * @return An Collision object if a collision happened, null if not
     */
    public Collision calculateCollision(double speedX, double speedY, Rectangle2D rect)
    {
	Collision c = null;
	// TODO
	for (Edge edge : Edge.values())
	{
	    double x = obj.getX() + edge.getOffsX() * obj.getWidth();
	    double y = obj.getY() + edge.getOffsY() * obj.getHeight();
	    Point2D coord = new Point2D.Double(x, y);
	    Point2D dest = new Point2D.Double(x + speedX, y + speedY);
	    Line2D raytrace = new Line2D.Double(coord, dest);
	    if (raytrace.intersects(rect))
	    {

	    }
	}

	return c;
    }
}
