import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
public class UI_Game extends JFrame implements ActionListener {
	public static JFrame gameFrame;
	private String gameMode;
	private TicTacToe game;

	JLayeredPane board;
	private JPanel top;
	private JPanel bottom;
	private JPanel west;
	private JPanel east;
	JPanel board_panel;

	JLabel playerScore1;
	JLabel playerScore2;
	JButton tile;
	ImageIcon cross;
	ImageIcon circle;

	UI_Game(JFrame frame,String mode)
	{
		game = new TicTacToe();
		gameMode = mode;
		gameFrame = frame;
		gameFrame.setLayout(new BorderLayout(20,100));
		gameFrame.setBackground(Color.BLACK);

		

		startBoardUI();
		gameFrame.add(top, BorderLayout.NORTH);
		gameFrame.add(bottom,BorderLayout.SOUTH);
		gameFrame.add(board,BorderLayout.CENTER);
		gameFrame.add(east,BorderLayout.EAST);
		gameFrame.add(west,BorderLayout.WEST);


		gameFrame.setVisible(true);
		gameFrame.revalidate();
		gameFrame.repaint();
		scaleIcons(tile.getSize());
		
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
		for(Integer i = 0;i < 9;i++)
		{
			tile = new JButton();
			tile.setActionCommand(i.toString());
			tile.setBackground(Color.black);
			tile.addActionListener(this);
			tile.setBorder(new LineBorder(Color.white,2));
			board_panel.add(tile);
		}
		
		board.add(board_panel,board.DEFAULT_LAYER);
		startTopAndBottom();
		startEastAndWest();
	}
	private void startTopAndBottom()
	{
		top = new JPanel();
		top.setSize(500,50);
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 0));
		top.setBackground(Color.black);
		JLabel title = new JLabel("TicTacToe");
		title.setForeground(Color.white);
		title.setFont(new Font("Calibri",Font.BOLD,36));
		//top.add(Box.createRigidArea(new Dimension(0, 50)));
		top.add(title);

		bottom = new JPanel();
		bottom.setBackground(Color.black);
	}
	private void startEastAndWest()
	{
		west = new JPanel(new GridLayout(2,1));
		east = new JPanel(new GridLayout(2,1));
		west.setBackground(Color.black);
		east.setBackground(Color.black);

		JPanel player1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		player1Panel.setBackground(Color.black);
		JPanel player2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		player2Panel.setBackground(Color.black);

		JLabel player1Label = new JLabel("    Player 1");
		player1Label.setFont(new Font("Calibri",Font.PLAIN,20));
		player1Label.setForeground(Color.yellow);
		JLabel player2Label = new JLabel("    Player 2");
		player2Label.setFont(new Font("Calibri",Font.PLAIN,20));
		player2Label.setForeground(Color.yellow);
		//player1Panel.add(new JLabel(cross));

		playerScore1 = new JLabel("0");;
		playerScore1.setFont(new Font("Calibri",Font.PLAIN,24));
		playerScore1.setForeground(Color.white);
		playerScore2 = new JLabel("0");;
		playerScore2.setFont(new Font("Calibri",Font.PLAIN,24));
		playerScore2.setForeground(Color.white);

		player1Panel.add(player1Label);
		player2Panel.add(player2Label);
		west.add(player1Panel);
		west.add(playerScore1);

		east.add(player2Panel);
		east.add(playerScore2);
	}
	private void scaleIcons(Dimension tileSize)
	{
		try
			{
			Image crossImage = ImageIO.read(this.getClass().getResource("images//X.png")).getScaledInstance(tileSize.width, tileSize.height, Image.SCALE_DEFAULT);
			cross = new ImageIcon(crossImage);
			Image circleImage = ImageIO.read(this.getClass().getResource("images//circle-green.png")).getScaledInstance(tileSize.width, tileSize.height, Image.SCALE_DEFAULT);
			circle = new ImageIcon(circleImage);
			}
			catch(IOException ex)
			{
				System.out.println("Error");
			}
	}
	public void actionPerformed(ActionEvent event)
	{
		if(Integer.parseInt(event.getActionCommand()) < 9)
		{
			int buttonNumber = Integer.parseInt(event.getActionCommand());
			int row = buttonNumber / 3;
			int col = buttonNumber % 3;
			if(!game.makeMove(row, col))
			{	
				return;
			}
			JButton tileActivated = (JButton)event.getSource();
			ImageIcon turnIcon = (game.getPlayer() == 'X')? cross:circle;
			tileActivated.setIcon(turnIcon);
			if(gameMode == "Human")
			{
				if(game.checkWin())
				{
					gameEnded(game.getPlayer(),true);
				}
				else if(game.isBoardFull())
				{
				gameEnded(game.getPlayer(),false);
				}
				else{
					game.switchPlayer();
				}
			}
			
			
			board_panel.revalidate();
			board_panel.repaint();	
		}
	}
	private void gameEnded(char winnerPlayer,boolean victory)
	{
		if(victory)
		{

		}
	}
}
