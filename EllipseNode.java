/**
 * MILAD AZH
 * 500440426
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * creates an ellipse node object by clicking anywhere in the boundaries of the application
 * @author milad
 *
 */
public class EllipseNode extends GraphElement{
	
	public EllipseNode ()
	{
		super();
	}
	
	/**
	 * constructs an ellipse node given a point(x and y coordinates)
	 *  with fixed width and height
	 * @param x the x-coordinate on the screen
	 * @param y the y-coordinate on the screen
	 */
	public EllipseNode (int x, int y)
	{
		super(x,y);
		
	}

	@Override
	void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		Ellipse2D e = new 
		Ellipse2D.Double (getXPos(), getYPos(), WIDTH, HEIGHT);
		
		// change color of selected object if it's selected
		g2.setColor(getColor());
		
		// set stroke width
		BasicStroke wid = new BasicStroke(2);
		g2.setStroke(wid);

		// draw edge
		g2.draw(e);

		// apply label
		if (getLabel() != null)
			g2.drawString( getLabel(), getCenterX() - 3*getLabel().length(), getCenterY() );

//		setColor(Color.BLACK);
	}

	@Override
	boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		Point2D cursorPos = new Point2D.Double(x,y);
		
		Ellipse2D e = new 
		Ellipse2D.Double (getXPos(), getYPos(), WIDTH, HEIGHT);
		
		if (e.contains(cursorPos))
		{
			setColor(Color.BLUE);
			return true;
		}
		
		return false;
	}
		
}
