/**
 * @author Prof. T.MCINERNEY (Original Author)
 * @author MILAD AZH
 * CPS 209
 * RYERSON UNIVERSITY
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

// General element of a graph (nodes and edges)

abstract public class GraphElement // extends Rectangle2D
{
	 private double xPos;
	 private double yPos;
	 protected String label;
	     
	 public final int WIDTH = 100;
	 public final int HEIGHT = 50;
	 private Color color;
	 
	 public GraphElement()
	 {
	    xPos = 0;
	    yPos = 0;
	 }
	   
	 /**
	  * Constructs an element of a rectangle, ellipse or line object.
	  * @param x x-coordinate to position the element
	  * @param y y-coordinate to position the element
	  */
	 public GraphElement(double x, double y)
	 {
	    xPos = x;
	    yPos = y;
	    
	 }
	  
	 /**
	  * @return the x-coordinate of the element
	  */
	 public final double getXPos()
	 {
	    return xPos;
	 }
	 
	 /**
	  * @return the x-coordinate of the element
	  */
	 public final double getYPos()
	 {
	    return yPos;
	 }

	 /**
	  * translates selected object to desired location
	  * @param xLoc the x-cooridante of the final position
	  * @param yLoc the y-cooridante of the final position
	  */
	 public void moveTo (double xLoc, double yLoc)
	 {
	    xPos = xLoc;
	    yPos = yLoc;
	 }
	   
	 
	 public String toString()
	 {
	    String str = "(X,Y) Position: (" + xPos + "," + yPos + ")\n";
	    return str;
	 }
	   
	 abstract void    draw(Graphics2D g2);	
     abstract boolean isSelected(double x, double y);
  
     /**
      * determines whether an element is capable of being labeled
      * @return true if a label(text string) can be applied to the element
      */
     boolean applyLabel()
     {
	    return true;
     }
  
     /**
      * @return text string of the label
      */
     public String getLabel()
     {
       return label;
     }
		  
     public void setLabel(String label)
     {
       this.label = label;
     }

     
//   my methods:
     
     public void setColor(Color color)
     {
       this.color = color;
     }
     
     /** 
      * Selection Color
      * @return color used to paint elements
      */
     public Color getColor()
     {
    	 return color;
     }
     
     /**
      * X-coordinate of the center-point of some element
      * @return the x-coordinate of the center of a shape element
      */
     public int getCenterX()
     {
    	 return (int) (getXPos() + WIDTH/2);
     }
     
     /**
      * Y-coordinate of the center-point of some element
      * @return the y-coordinate of the center of a shape element
      */
     public int getCenterY()
     {
    	 return (int) (getYPos() + HEIGHT/2);
     }
     

}
