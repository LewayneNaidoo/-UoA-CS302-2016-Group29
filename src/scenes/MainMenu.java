package scenes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener{
	public MainMenu() {
		setTitle("Sabotage!");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setVisible(true);
		setBackground(new Color(59, 89, 182));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(150,0);
		setSize(1024,768);

		JButton singlePlayer = new JButton("Campaign"); 
		singlePlayer.setOpaque(true);
		singlePlayer.setBackground(new Color(59, 89, 182));
		singlePlayer.setFocusPainted(false);
		singlePlayer.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						setVisible(false); 
						new CampaignGame();
					}
				}); 

		JButton multiPlayer = new JButton("Multiplayer");
		multiPlayer.setOpaque(true);
		multiPlayer.setBackground(new Color(59, 89, 182));
		multiPlayer.setFocusPainted(false);
		multiPlayer.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						setVisible(false);
						new MultiplayerGame();
					}
				});  

		add(singlePlayer);
		add(multiPlayer);

	}
	public static void main(String[] args) { 
		new MainMenu(); 
	} 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}