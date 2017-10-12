/**
 * MILAD AZH
 * 500440426
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Edge extends GraphElement {
	
	Point2D init;
	Point2D fin;
	
	/**
	 * Maximum distance from cursor.
	 */
	final double MAXIMUM_DIST_CURSOR = 0.9;
	/**
	 * Minimum length that a line object can have.
	 * There should be a limit to how short the edge is drawn,
	 *  making it possible to be selected and/or removed.
	 */
	final double MINIMUM_EDGE_LENGTH = 20.0;
	/**
	 * Sets the size for the outline of all shape elements.
	 */
	final float STROKE_SIZE = 2;
	
	public Edge()
	{
		super();
	}
	
	/** 
	 * Constructs a line segment from one point to another 
	 * @param init starting point
	 * @param fin ending point
	 */
	public Edge(Point2D init, Point2D fin)
	{
		this.init = init;
		this.fin = fin;
	}


	@Override
	void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		Line2D edge = new Line2D.Double (init, fin);
		
		// change color if selected
		g2.setColor(getColor());
		
		// set width
		BasicStroke wid = new BasicStroke(STROKE_SIZE);
		g2.setStroke(wid);
		
		double edgeLength = edge.getP2().distance(edge.getP1());
		
		// draw the edge.  
		if (edgeLength >= MINIMUM_EDGE_LENGTH)
			g2.draw(edge);
		
//		setColor(Color.BLACK);
	}

	@Override
	boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		Point2D cursorPos = new Point2D.Double(x, y);
		
		Line2D edge = new Line2D.Double (init, fin);
		
//		if (edge.ptLineDist(cursorPos) <= 0.9 )
		if (edge.ptLineDistSq(cursorPos) <= MAXIMUM_DIST_CURSOR)
		{
			setColor(Color.BLUE);
			return true;
		}
		
		return false;
	}
	
	
	/**	
	 * @Override 
	 * @return false since does not take labels 
	 */
	public boolean applyLabel()
	{
		return false;
	}

}
