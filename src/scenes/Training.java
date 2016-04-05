package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Player;

public class Training extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	public Training(){
		frame = new JFrame();
//		Player playerOne = new Player(10, 10, 0, true);
	//	frame.add(playerOne);
		
		frame.pack();
		frame.setResizable(false);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(150,0);
		frame.setSize(1024,768);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 1024, 768);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}