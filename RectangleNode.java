/**
 * MILAD AZH
 * 500440426
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class RectangleNode extends GraphElement {

	public RectangleNode ()
	{
		super();
	}

	public RectangleNode(int x, int y)
	{
		super(x,y);
	}

	@Override
	void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		Rectangle2D r = new 
		Rectangle2D.Double( getXPos(), getYPos(), WIDTH, HEIGHT);
		
		//change color of selected object if it's selected
		g2.setColor(getColor());
		// set stroke width
		BasicStroke wid = new BasicStroke(2);
		g2.setStroke(wid);
		
		//draw rectangle
		g2.draw(r);
	
		// apply label
		if (getLabel() != null)
			g2.drawString( getLabel(), getCenterX() - 3*getLabel().length(), getCenterY() );
		
		//change paint color back to original
//		setColor(Color.BLACK);
	}

	@Override
	boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		
		//	store the position of the mouse
		Point2D cursorPos = new Point2D.Double(x,y);

		// make an "equal" rectangle without drawing it 
		Rectangle2D r = new 
		Rectangle2D.Double( getXPos(), getYPos(), WIDTH, HEIGHT);
		
		// check if cursor was over this element
		if (r.contains(cursorPos))
		{
			setColor(Color.BLUE);
			return true;
		}
		
		
		return false;
	}

}
