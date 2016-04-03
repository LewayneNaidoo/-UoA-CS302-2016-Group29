package scenes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener{
	 
//	private JButton campaign = new JButton("Campaign");
//	private JButton multiplayer = new JButton("Multiplayer");
//	private JButton training = new JButton("Training");
	public MainMenu()
	{   
		add(new MultiplayerGame());
		
		setTitle("Sabotage!");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setVisible(true);
		setBackground(new Color(79, 79, 79));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(150,0);
		setSize(1024,768);
		setResizable(false);
	
//		JFrame frame = new JFrame("SABOTAGE!");
//		JPanel panel = new JPanel();
//
//		frame.setSize(1024,768);
//		frame.setLocation(150,0);
//		frame.setResizable(false);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		campaign.addActionListener(
//								new ActionListener(){
//									public void actionPerformed(ActionEvent e){
//										setVisible(true); 
//										new CampaignGame();
//									}
//								}); 
//
//		multiplayer = new JButton("Multiplayer");
//		multiplayer.addActionListener(this);
//		multiplayer.setActionCommand("Open");
//        add(multiplayer);
//        pack();
		
//		frame.add(campaign);
//		frame.add(multiplayer);
//		frame.add(training);
		
		//frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\logo.png")));
		//frame.setBackground(new Color(79, 79, 79));
		//frame.setIconImage("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\logo.png");
//		frame.setLayout(new FlowLayout());
//		frame.setSize(1024,768);
		
//					add(singlePlayer);
//				add(multiPlayer);
	}
	public static void main(String[] args) { 
		new MainMenu();
//		SwingUtilities.invokeLater(new Runnable(){
//
//            @Override
//            public void run()
//            {
//                new MainMenu().setVisible(true);
//            }
//
//        });
	} 
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		 String cmd = e.getActionCommand();
//
//	        if(cmd.equals("Open"))
//	        {
//	            dispose();
//	            new MultiplayerGame();
//	        }
	}
}