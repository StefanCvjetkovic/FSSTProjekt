package jumpandrun.calc;

import java.awt.geom.Point2D;

public class Collision
{
    private final Point2D.Double collisionPoint;
    private final Side side;
    private final Edge edge;

    public Collision(Point2D.Double collisionPoint, Side side, Edge edge)
    {
	this.collisionPoint = collisionPoint;
	this.side = side;
	this.edge = edge;
    }

    public Point2D.Double getCollisionPoint()
    {
	return collisionPoint;
    }

    public Side getSide()
    {
	return side;
    }

    public Edge getEdge()
    {
	return edge;
    }
}
