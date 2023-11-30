import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
public class UI_Menu extends JFrame implements ActionListener {
	static JFrame menuFrame;
	JPanel panel;
	JButton lowerButton;
	JButton upperButton;
	JLabel TicTacToeLabel = new JLabel("Tic Tac Toe");
	UI_Menu(JFrame frame)
	{
		menuFrame = frame;
		menuFrame.setSize(500,500);
		menuFrame.setVisible(true);
		menuFrame.setLayout(new FlowLayout());
		menuFrame.getContentPane().setBackground(Color.BLACK);
		startPanel();
		menuFrame.add(panel);
	}
	
	public void startButtons()
	{
		lowerButton = new JButton("Quit");
		lowerButton.addActionListener(this);
		lowerButton.setMaximumSize(new Dimension(100,50));
		lowerButton.setFont(new Font("Calibri",Font.BOLD, 12));
		lowerButton.setBorder(new LineBorder(Color.YELLOW, 2));

		upperButton = new JButton("Play");
		upperButton.addActionListener(this);
		upperButton.setMaximumSize(new Dimension(100,50));
		upperButton.setBorder(new LineBorder(Color.YELLOW,2));

		TicTacToeLabel.setSize(100, 50);
		TicTacToeLabel.setFont(new Font("Calibri",Font.ITALIC,36));
		TicTacToeLabel.setForeground(Color.WHITE);
		//TicTacToeLabel.setFont(new Font();

		
	}
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getActionCommand().equals("Quit"))
		{
			System.exit(0);
		}
		if(ev.getActionCommand().equals("Play"))
		{
			enterOpponentMenu();
		}
		if(ev.getActionCommand().equals("AI"))
		{
			System.out.println("AI");
		}

	}
	private void startPanel()
	{
		panel = new JPanel();
		panel.setLayout(new GridLayout(7, 0));
		panel.setSize(500,500);
		panel.setAlignmentY(BOTTOM_ALIGNMENT);
		panel.setAlignmentX(BOTTOM_ALIGNMENT);
		panel.setBackground(Color.BLACK);
		startButtons();

		panel.add(TicTacToeLabel);
		panel.add(Box.createRigidArea(new Dimension(0,50)));
		panel.add(upperButton);
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		panel.add(lowerButton);
	}
	private void enterOpponentMenu()
	{
		TicTacToeLabel.setText("Choose game mode");
		upperButton.setText("Human");
		lowerButton.setText("AI");
		JButton quit_button = new JButton("Quit");
		quit_button.addActionListener(this);
		quit_button.setBorder(new LineBorder(Color.YELLOW,2));
		quit_button.setFont(new Font("Calibri",Font.PLAIN,12));
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		panel.add(quit_button);
	}
}	

