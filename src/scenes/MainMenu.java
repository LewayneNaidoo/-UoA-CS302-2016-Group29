package scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener{

	private JButton campaign;
	private JButton multiplayer;
	private JButton training;
	public MainMenu()
	{   
//		public static boolean RIGHT_TO_LEFT = false;
//		
//		 public static void addComponentsToPane(Container contentPane) {
////		    	Use BorderLayout. Default empty constructor with no horizontal and vertical
////		    	gaps
//			 contentPane.setLayout(new BorderLayout(5,5));
//		        if (!(contentPane.getLayout() instanceof BorderLayout)) {
//		            contentPane.add(new JLabel("Container doesn't use BorderLayout!"));
//		            return;
//		        }
//
//		        if (RIGHT_TO_LEFT) {
//		            contentPane.setComponentOrientation(
//		                java.awt.ComponentOrientation.RIGHT_TO_LEFT);
//		        }
		
		setScreenLayout();

		//				JFrame frame = new JFrame("SABOTAGE!");
		//				JPanel panel = new JPanel();
		//		
		//				frame.setSize(1024,768);
		//				frame.setLocation(150,0);
		//				frame.setResizable(false);
		//				frame.setVisible(true);
		//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		campaign = new JButton("Campaign Mode");
		campaign.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(true);
				removeButtons();

				add(new CampaignGame());
				setScreenLayout();
			}
		}); 

		multiplayer = new JButton("Multiplayer Game");
		multiplayer.setLocation(100,100);
		multiplayer.setBackground(new Color(10, 106, 2));
		multiplayer.setForeground(Color.WHITE);
		multiplayer.setFocusPainted(false);
		multiplayer.setFont(new Font("Tahoma", Font.BOLD, 20));
		multiplayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(true); 
				removeButtons();

				add(new MultiplayerGame());
				setScreenLayout();
			}
		}); 

		training = new JButton("Combat Training");
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
		//		pack();

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

	public void paint (Graphics g){
		super.paintComponents(g);
		g.setColor(new Color( 0, 0, 0));
		g.fillRect(0, 0, 1024, 768);
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