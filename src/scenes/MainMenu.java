package scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener{

	JPanel jp = new JPanel();
	JLabel a, b, c, d, e = new JLabel();
	JButton multiplayer = new JButton();
	JButton campaign = new JButton();
	JButton training = new JButton();
	
	public MainMenu(){ 
		
		setScreenLayout();
		
		jp.setLayout(new GridLayout(10, 1));
		
		a = new JLabel("");
		a.setIcon(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\SabotageBackgroundPart1.png"));
		jp.add(a);
		b = new JLabel("");
		b.setIcon(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\SabotageBackgroundPart2.png"));
		jp.add(b);
		c = new JLabel("");
		c.setIcon(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\SabotageBackgroundPart3.png"));
		jp.add(c);
	
		
		campaign = new JButton("Campaign Mode");
		campaign.setBackground(new Color(10, 106, 2));
		campaign.setForeground(Color.WHITE);
		campaign.setIcon(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerTankE.png"));
//		campaign.setPreferredSize(new Dimension(1024, 100));
		campaign.setFocusPainted(false);
		campaign.setFont(new Font("Tahoma", Font.BOLD, 30));
		campaign.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(true);
				removeButtons();
				add(new CampaignGame());
				setScreenLayout();
				revalidate();
				
			}
		}); 
		jp.add(campaign);

		multiplayer = new JButton("Multiplayer Mode");
		multiplayer.setBackground(new Color(10, 106, 2));
		multiplayer.setForeground(Color.WHITE);
		multiplayer.setIcon(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerTankE.png"));
//		campaign.setPreferredSize(new Dimension(1024, 100));
		multiplayer.setFocusPainted(false);
		multiplayer.setFont(new Font("Tahoma", Font.BOLD, 30));
		multiplayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				removeButtons();
				add(new MultiplayerGame());
				setScreenLayout();
				revalidate();
				
			}
		}); 
		jp.add(multiplayer);
		
		training = new JButton("Training Mode");
//		training.setPreferredSize(new Dimension(40, 40));
		training.setIcon(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerTankE.png"));
		training.setForeground(Color.WHITE);
		training.setBackground(new Color(10, 106, 2));
		training.setFocusPainted(false);
		training.setFont(new Font("Tahoma", Font.BOLD, 30));
		training.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(true);
				removeButtons();
				add(new Training());
				setScreenLayout();
				
			}
		}); 
		jp.add(training);
		
		d = new JLabel("");
		d.setIcon(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\SabotageFooterPart1.png"));
		jp.add(d);
		
		e = new JLabel("");
		e.setIcon(new ImageIcon("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\SabotageFooterPart2.png"));
		jp.add(e);
		
		add(jp);
		validate();
//		setScreenLayout();
//		campaign = new JButton("Campaign Mode");
//		campaign.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				setVisible(true);
//				removeButtons();
//
//				add(new CampaignGame());
//				setScreenLayout();
//			}
//		}); 

//		multiplayer = new JButton("Multiplayer Game");
//		multiplayer.setLocation(100,100);
//		multiplayer.setBackground(new Color(10, 106, 2));
//		multiplayer.setForeground(Color.WHITE);
//		multiplayer.setFocusPainted(false);
//		multiplayer.setFont(new Font("Tahoma", Font.BOLD, 20));
//		multiplayer.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				setVisible(true); 
//				removeButtons();
//
//				add(new MultiplayerGame());
//				setScreenLayout();
//			}
//		}); 

//		training = new JButton("Combat Training");
//		training.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				setVisible(true); 
//				removeButtons();
//
//				add(new Training());
//				setScreenLayout();
//			}
//		}); 

//		add(multiplayer);
//		add(campaign);
//		add(training);
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

//	public void paint (Graphics g){
//		super.paintComponents(g);
//		g.setColor(new Color( 0, 0, 0));
//		g.fillRect(0, 0, 1024, 768);
//	}
	
	public static void main(String[] args) { 
		new MainMenu();

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
		jp.removeAll();
	}
}