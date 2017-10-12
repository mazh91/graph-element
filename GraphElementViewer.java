/**
 * MILAD AZH
 * 500440426
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GraphElementViewer {

	public static void main (String args[])
	{
		
		JFrame frame = new JFrame();

		final int FRAME_WIDTH  = 800;
		final int FRAME_HEIGHT = 600;
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("graphE Viewer");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout() );
		
		// add new graphE
		final GraphElementComponent graphE = new GraphElementComponent();
			
		frame.add(graphE, BorderLayout.CENTER);
		
		// create a new panel to store the buttons
		final JPanel panel = new JPanel();
		
		frame.add(panel, BorderLayout.NORTH);
		
		
		// create rectangle button
		JButton recBtn = new JButton("Rectangle");		

		
		class DrawRec implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub					
				graphE.setShapeID(1);
			}
			
		}
		// add listener to this button
		DrawRec drawRec = new DrawRec();
		recBtn.addActionListener(drawRec);
		
		
		// create ellipse button
		JButton elpBtn = new JButton("Ellipse");
		
		class DrawElp implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				graphE.setShapeID(2);
			}
		}
		// add listener to this button
		DrawElp drawElp = new DrawElp();
		elpBtn.addActionListener(drawElp);
		
		
		// create a line segment(Edge) button
		JButton edgeBtn = new JButton("Edge");
		
		class DrawEdge implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				graphE.setShapeID(3);
			}
		}
		// add listener to this button
		DrawEdge drawEdge = new DrawEdge();
		edgeBtn.addActionListener(drawEdge);
		
		
		// create a textfield
		final JTextField textField = new JTextField(10);
		
		
		// create a button that labels nodes
		JButton labelBtn = new JButton("Label");
		
		class AddLabel implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{

				int index = graphE.getSelected();

				// apply label to shape element
				if (graphE.getSelected() < 0 || graphE.getWasDragged() )		// || graphE.getShapeID() == 0
					JOptionPane.showMessageDialog(panel, "Select an object first!");
				else
				{
					graphE.shapeList.get( index ).setLabel( textField.getText() );	// textField.getText()
					graphE.shapeList.get( index ).setColor(Color.BLACK);
				}

				graphE.repaint();
				// deselect current graphE after labelling
				graphE.setSelected(-1);
			}
		}
		// add listener to this button 
		AddLabel addLabel = new AddLabel();
		labelBtn.addActionListener(addLabel);
		
		// add buttons to the panel
		panel.add(recBtn);
		panel.add(elpBtn);
		panel.add(edgeBtn);
		
		// add label & textfield to panel
		panel.add(labelBtn);
		panel.add(textField);
		
		frame.setVisible(true);
		
	}
	
}
