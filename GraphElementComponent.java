/**
 * MILAD AZH
 * 500440426
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JComponent;


@SuppressWarnings("serial")
public class GraphElementComponent extends JComponent {

	/**
	 * Use these to position the nodes
	 */
	private int xCenter, yCenter;

	/**
	 * Index of the shape determines which shape should be drawn.
	 *  This variable ranges between 0 to 3.
	 *  It is set to 0 when no element is drawn,
	 * 		 1 when a rectangle node is drawn,
	 * 		 2 when an ellipse node is drawn,
	 * 	 and 3 when an edge element is drawn.
	 */
	
	private int shapeID;
	/**
	 * Determines whether an element was
	 * dragged and moved to a new position.
	 *  This variable is specifically used to avoid 
	 * applying a label to an object that had been dragged.
	 */
	private boolean wasDragged;		

	/**
	 * Starting-point of an edge element
	 */
	Point2D edgeHead; 

	/** Fixed width applied to all node elements */
	private final int WIDTH = 100;
	/** Fixed height applied to all node elements */
	private final int HEIGHT = 50;


	/** 
	 * Create an array to store node and edge elements.
	 *  This ArrayList stores every element created by the user.
	 *   When the repaint() method is called, the array elements
	 *   (re)draw themselves.
	 */
	ArrayList<GraphElement> shapeList = new ArrayList<GraphElement>();

	/**
	 * Defines and initializes a variable for selected object index.
	 *  This variable is used to indicate the index of the selected element 
	 *  inside shapeList.  Initialized to -1 to indicate no element is selected.
	 */
	int selected = -1;		 

	/**
	 * Constructs a GraphElement component, where actions are defined
	 * when the user selects or drags a graph element. The user is able to
	 * select and drag(double click) a node and move it to a desired position. 
	 */
	public GraphElementComponent()
	{

		super();

		xCenter = yCenter = 0;

		// mouse press: create a new Node based 
		// on the position of the cursor
		class MousePressListener implements MouseListener
		{

			public void mousePressed(MouseEvent e)
			{
				setWasDragged(false);

				// obtain the center coordinates of a Node 
				xCenter = e.getX() - WIDTH/2;
				yCenter = e.getY() - HEIGHT/2;

				// create certain object that corresponds to pressed button 
				switch (shapeID) {
				
				case 1:
					// add rectangle object
					shapeList.add(new RectangleNode(xCenter, yCenter));
					deselectAll();
					
					break;

				case 2:
					// add an eclipse object
					shapeList.add(new EllipseNode(xCenter, yCenter));
					deselectAll();
					
					break;

				case 3:
					// create starting point for an edge
					edgeHead = new Point2D.Double(e.getX(), e.getY());
					deselectAll();
					
					break;

				default:
				
					// SELECT-MODE
					// iterate thru the shape list, determine if the cursor
					// is over an element the moment user clicks
					deselectAll();
					
					for (int i = 0; i < shapeList.size(); i++)
					{
						if ( shapeList.get(i).isSelected(e.getX(), e.getY()) )
						{
							setSelected (i);

							// condition: some object is selected and 
							if (e.getButton() == MouseEvent.BUTTON3) 
								{
								shapeList.remove(selected);
								setSelected(-1);
								}
						}
					 // else DESELECT ALL	
					}
					break;
				
				
				}	// end switch
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

				// The final point is created and the edge is drawn
				if (shapeID == 3)
				{
					deselectAll();
					
					Point2D edgeTail = new Point2D.Double(e.getX(), e.getY());
					shapeList.add (new Edge(edgeHead, edgeTail));
				}

				// condition: an element is selected and dragged
				// if so change the color back to original
				if ( getWasDragged() )
					deselectAll();

				repaint();

				setShapeID(0);
			}
		}
		// add mouse press listener to the component
		MouseListener mousePressList = new MousePressListener();
		addMouseListener(mousePressList);



		class MouseMotList implements MouseMotionListener
		{
			public void mouseMoved(MouseEvent e)
			{

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub


				// condition: an object was selected to be moved to a new position
				if (getSelected() >= 0 &&  shapeList.get(selected).isSelected(e.getX(), e.getY()) )
				{
					xCenter = e.getX() - WIDTH/2;
					yCenter = e.getY() - HEIGHT/2;
					shapeList.get(selected).moveTo( xCenter, yCenter );

					setWasDragged(true);
				}

				repaint();
			}
		}

		// add mouse motion listener to the component
		MouseMotList mouseMotList = new MouseMotList();
		addMouseMotionListener(mouseMotList);

	}


	// mutator for shape type
	public void setShapeID(int shapeID)
	{
		this.shapeID = shapeID;
	}
	// accessor for shape type
	public int getShapeID()
	{
		return shapeID;
	}

	// mutator for selected element
	public void setSelected(int index)
	{
		selected = index;
	}
	// accessor for shape type
	public int getSelected()
	{
		return selected;
	}

	// mutator for dragged element
	public void setWasDragged(boolean b)
	{
		wasDragged = b;
	}
	// accessor for dragged element
	public boolean getWasDragged()
	{
		return wasDragged;
	}

	// deselect any object that was selected before
	public void deselectAll()
	{
		if ( getSelected() != -1 )
		{
			shapeList.get(selected).setColor(Color.BLACK);
			setSelected(-1);
		}			
	}

	/**
	 * Used as an accessible method to redraw the elements inside shapeList.
	 *  Calls all elements inside the array to be redrawn. 
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;


		// draw all elements of my array
		for (int i = 0; i < shapeList.size(); i++)
			if (shapeList.get(i) != null)
				shapeList.get(i).draw(g2);

	}


	// *BUG IMPROVMENT SECTION* (for personal use)
	public boolean hasConflict ()
	{
		return false;
	}


}
