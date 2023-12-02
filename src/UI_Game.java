import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
public class UI_Game extends JFrame implements ActionListener {
	public static JFrame game_frame;
	private String gameMode;
	private JLabel title = new JLabel("TicTacToe");
	JLabel playerScore1;
	JLabel playerScore2;
	private JPanel top;
	private JPanel bottom;
	JLayeredPane board;
	JPanel board_panel;

	UI_Game(JFrame frame,String mode)
	{
		gameMode = mode;
		game_frame = frame;
		frame.setLayout(new BorderLayout(100,100));
		frame.setBackground(Color.BLACK);

		playerScore1 = new JLabel("0");;
		playerScore1.setFont(new Font("Calibri",Font.PLAIN,24));
		playerScore1.setForeground(Color.white);
		playerScore2 = new JLabel("0");;
		playerScore2.setFont(new Font("Calibri",Font.PLAIN,24));
		playerScore2.setForeground(Color.white);

		startBoardUI();
		frame.add(top, BorderLayout.NORTH);
		frame.add(bottom,BorderLayout.SOUTH);
		frame.add(board,BorderLayout.CENTER);
		frame.add(playerScore1,BorderLayout.WEST);
		frame.add(playerScore2,BorderLayout.EAST);

		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
		
	}
	void startBoardUI()
	{
		board = new JLayeredPane();
		board.setBackground(Color.yellow);
		board.setBorder(new LineBorder(Color.white, 2));
		board.setLayout(new GridLayout(0,1));

		board_panel = new JPanel();
		board_panel.setLayout(new GridLayout(3, 3));
		board_panel.setBackground(Color.black);
		board_panel.setBorder(new LineBorder(Color.BLACK,2));
		for(int i = 0;i < 9;i++)
		{
			JButton button = new JButton();
			button.setActionCommand("tile");
			button.setBackground(Color.black);
			button.addActionListener(this);
			button.setSize(50,50);
			button.setBorder(new LineBorder(Color.white,2));
			board_panel.add(button);

		}
		board.add(board_panel,board.DEFAULT_LAYER);
		
		top = new JPanel();
		top.setSize(500,50);
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 0));
		top.setBackground(Color.black);

		title.setForeground(Color.white);
		title.setFont(new Font("Calibri",Font.BOLD,36));
		//top.add(Box.createRigidArea(new Dimension(0, 50)));
		top.add(title);

		bottom = new JPanel();
		bottom.setBackground(Color.black);

	}
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("tile"))
		{
			ImageIcon icon = new ImageIcon(this.getClass().getResource("circle-green.png"));
			JButton tileActivated = (JButton)event.getSource();
			tileActivated.setIcon(icon);
			board_panel.revalidate();
			board_panel.repaint();
		}
	}
}
