package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Player;

public class MultiplayerGame extends JPanel implements ActionListener{

private static final long serialVersionUID = 1L;
private JFrame frame;

public MultiplayerGame(){
    frame = new JFrame();
    Player playerOne = new Player(0, 0, "RIGHT", true);
    Player playerTwo = new Player(900, 600, "LEFT", false);
    frame.add(playerOne);
    frame.add(playerTwo);
    frame.pack();
    frame.setResizable(false);

    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1024,768);
}

public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(59, 89, 182));
    g.fillRect(0, 0, 1024, 768);
}

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

}
}