package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import generators.WallPainter;
import objects.Player;

public class MultiplayerGame extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Timer timer = null;
	Player playerOne;
	Player playerTwo;
	WallPainter wall;
	
	public MultiplayerGame(){
		//    frame = new JFrame();
		//    Player playerOne = new Player(0, 0, 90, true);
		//    Player playerTwo = new Player(900, 600, 270, false);
		//    frame.add(playerOne);
		//    frame.add(playerTwo);
		//    frame.pack();
		//    frame.setResizable(false);
		//
		//    frame.setVisible(true);
		//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//    frame.setSize(1024,768);
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);

		playerOne = new Player(400, 400, 0, true);
		playerTwo = new Player(50, 50, 180, false);
		wall = new WallPainter();
		
		timer = new Timer(33, this);
		timer.start(); 
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(59, 89, 182));
		g.fillRect(0, 0, 1024, 768);
		doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {
		playerOne.paint(g);
		playerTwo.paint(g);
		wall.paint(g);
//		for (int i=0;i<=1024;i+=48){
//		wall = new Walls(i,0);
//		wall.paint(g);
//			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		playerOne.move();
		playerTwo.move();
		repaint();

	}
	
	// http://zetcode.com/tutorials/javagamestutorial/movingsprites/
	 private class TAdapter extends KeyAdapter {

	        @Override
	        public void keyReleased(KeyEvent e) {
	            playerOne.keyReleased(e);
	            playerTwo.keyReleased(e);
	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	            playerOne.keyPressed(e);
	            playerTwo.keyPressed(e);
	        }
	    }
}