package scenes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener{

	private JButton campaign;
	private JButton multiplayer;
	private JButton training;
	public MainMenu()
	{   
//		add(new MultiplayerGame());

		setScreenLayout();

		//		JFrame frame = new JFrame("SABOTAGE!");
		//		JPanel panel = new JPanel();
		//
		//		frame.setSize(1024,768);
		//		frame.setLocation(150,0);
		//		frame.setResizable(false);
		//		frame.setVisible(true);
		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		campaign = new JButton("Campaign");
		campaign.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(true);
				removeButtons();
				
				add(new CampaignGame());
				setScreenLayout();
			}
		}); 

		multiplayer = new JButton("Multiplayer");
		multiplayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(true); 
				removeButtons();

				add(new MultiplayerGame());
				setScreenLayout();
			}
		}); 
		
		training = new JButton("Training");
		training.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(true); 
				removeButtons();

				add(new Training());
				setScreenLayout();
			}
		}); 
		
		add(multiplayer);
		add(campaign);
		add(training);
		pack();

		//		frame.add(campaign);
		//		frame.add(multiplayer);
		//		frame.add(training);
		//		
		//		frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\logo.png")));
		//		frame.setBackground(new Color(79, 79, 79));
		//		frame.setIconImage("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\logo.png");
		//		frame.setLayout(new FlowLayout());
		//		frame.setSize(1024,768);
		//		
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
	public void actionPerformed(ActionEvent e) {
		//		 String cmd = e.getActionCommand();
		//
		//	        if(cmd.equals("Open"))
		//	        {
		//	            dispose();
		//	            new MultiplayerGame();
		//	        }
	}
	
	public void setScreenLayout(){
		setTitle("Sabotage!");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setVisible(true);
//		setBackground(new Color(79, 79, 79));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(150,0);
		setSize(1024,768);
		setResizable(false);

	}
	
	public void removeButtons(){
		remove(campaign);
		remove(multiplayer);
		remove(training);
	}
}