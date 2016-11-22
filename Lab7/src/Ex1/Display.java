package ex1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Display extends JFrame
{
	JButton red, blue;
	private Display()
	{
		super("TEST");
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		drawGUI();
	}
	private void drawGUI()
	{
		Handler h = new Handler();
		red = new JButton("Red");
		red.addActionListener(h);
		add(red);
		blue = new JButton("Blue");
		blue.addActionListener(h);
		add(blue);
	}
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				Display d = new Display();
				d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				d.setSize(600, 400);
				d.setVisible(true);
			}
		});
	}
	private class Handler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == red) {
				getContentPane().setBackground(Color.red);
			}
			else if(e.getSource() == blue) {
				getContentPane().setBackground(Color.blue);
			}
		}
	}
}