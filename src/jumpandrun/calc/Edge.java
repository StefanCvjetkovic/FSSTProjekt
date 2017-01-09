package jumpandrun.calc;

/**
 * An Enum for all the edges of an objects BoundingBox which can possibly have a
 * collision
 *
 */
public enum Edge
{
    UPPER_RIGHT(1, 0),
    UPPER_LEFT(0, 0),
    LOWER_RIGHT(1, 1),
    LOWER_LEFT(1, 0);

    private final double offsX, offsY;

    /**
     * 
     * @param offsX
     *            The X between the upper left corner and this corner, measured
     *            in units of width
     * @param offsY
     *            The Y between the upper left corner and this corner, measured
     *            in units of height
     */
    private Edge(int offsX, int offsY)
    {
	this.offsX = offsX;
	this.offsY = offsY;
    }

    public double getOffsX()
    {
	return offsX;
    }

    public double getOffsY()
    {
	return offsY;
    }
}
