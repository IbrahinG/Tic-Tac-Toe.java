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
	private boolean gameRunning = true;
	private char player1 = 'X';
	public String playerScoreNumX = "0";
	public String playerScoreNumY = "0";

	JLayeredPane board;
	private JPanel top;
	private JPanel bottom;
	private JPanel west;
	private JPanel east;
	JPanel board_panel;

	JLabel playerScore1;
	JLabel playerScore2;
	JLabel playerIcon1;
	JLabel playerIcon2;
	JButton tile;
	ImageIcon cross;
	ImageIcon circle;

	UI_Game(JFrame frame, String mode) {
		game = new TicTacToe();
		gameMode = mode;
		gameFrame = frame;
		gameFrame.setLayout(new BorderLayout(20, 100));
		gameFrame.setBackground(Color.BLACK);

		startBoardUI();
		gameFrame.add(top, BorderLayout.NORTH);
		gameFrame.add(bottom, BorderLayout.SOUTH);
		gameFrame.add(board, BorderLayout.CENTER);
		gameFrame.add(east, BorderLayout.EAST);
		gameFrame.add(west, BorderLayout.WEST);

		gameFrame.setVisible(true);
		gameFrame.revalidate();
		gameFrame.repaint();
		scaleIcons(tile.getSize());

	}

	void startBoardUI() {
		board = new JLayeredPane();
		board.setBackground(Color.yellow);
		board.setBorder(new LineBorder(Color.white, 2));
		board.setLayout(new GridLayout(0, 1));

		board_panel = new JPanel();
		board_panel.setLayout(new GridLayout(3, 3));
		board_panel.setBackground(Color.black);
		board_panel.setBorder(new LineBorder(Color.BLACK, 2));
		for (Integer i = 0; i < 9; i++) {
			tile = new JButton();
			tile.setActionCommand(i.toString());
			tile.setBackground(Color.black);
			tile.addActionListener(this);
			tile.setBorder(new LineBorder(Color.white, 2));
			board_panel.add(tile);
		}

		board.add(board_panel, board.DEFAULT_LAYER);
		startTopAndBottom();
		startEastAndWest();
	}

	private void startTopAndBottom() {
		top = new JPanel();
		top.setSize(500, 50);
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 0));
		top.setBackground(Color.black);
		JLabel title = new JLabel("TicTacToe");
		title.setForeground(Color.white);
		title.setFont(new Font("Calibri", Font.BOLD, 36));
		// top.add(Box.createRigidArea(new Dimension(0, 50)));
		top.add(title);

		bottom = new JPanel(new FlowLayout());
		bottom.setBackground(Color.black);
	}

	private void startEastAndWest() {
		west = new JPanel(new GridLayout(5, 1));
		east = new JPanel(new GridLayout(5, 1));
		west.setBackground(Color.black);
		east.setBackground(Color.black);

		JPanel player1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		player1Panel.setBackground(Color.black);
		JPanel player2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		player2Panel.setBackground(Color.black);

		JLabel player1Label = new JLabel("    Player 1");
		player1Label.setFont(new Font("Calibri", Font.PLAIN, 20));
		player1Label.setForeground(Color.yellow);
		playerIcon1 = new JLabel();
		playerIcon1.setSize(25, 25);
		playerIcon2 = new JLabel();
		JLabel player2Label = new JLabel("    Player 2");
		player2Label.setFont(new Font("Calibri", Font.PLAIN, 20));
		player2Label.setForeground(Color.yellow);
		scaleIcons(playerIcon1.getSize());
		playerIcon1.setIcon(cross);
		playerIcon2.setIcon(circle);

		playerScore1 = new JLabel(playerScoreNumX);
		;
		playerScore1.setFont(new Font("Calibri", Font.PLAIN, 24));
		playerScore1.setForeground(Color.white);
		playerScore2 = new JLabel(playerScoreNumY);
		;
		playerScore2.setFont(new Font("Calibri", Font.PLAIN, 24));
		playerScore2.setForeground(Color.white);

		player1Panel.add(player1Label);
		player1Panel.add(playerIcon1);
		player2Panel.add(player2Label);
		player2Panel.add(playerIcon2);

		west.add(player1Panel);
		west.add(playerScore1);
		west.add(Box.createRigidArea(new Dimension(20, 100)));

		east.add(player2Panel);
		east.add(playerScore2);
		east.add(Box.createRigidArea(new Dimension(20, 100)));
	}

	private void scaleIcons(Dimension tileSize) {
		try {
			Image crossImage = ImageIO.read(this.getClass().getResource("images//X.png"))
					.getScaledInstance(tileSize.width, tileSize.height, Image.SCALE_DEFAULT);
			cross = new ImageIcon(crossImage);
			Image circleImage = ImageIO.read(this.getClass().getResource("images//circle-green.png"))
					.getScaledInstance(tileSize.width, tileSize.height, Image.SCALE_DEFAULT);
			circle = new ImageIcon(circleImage);
		} catch (IOException ex) {
			System.out.println("Error");
		}
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("No")) {
			System.exit(0);
		}
		if (event.getActionCommand().equals("Yes")) {
			game.initializeBoard();
			if (player1 == 'X')
				player1 = 'O';
			else
				player1 = 'X';
			for (Component c : bottom.getComponents()) {
				bottom.remove(c);
			}
			Integer i = 0;
			for (Component c : board_panel.getComponents()) {
				board_panel.remove(c);
				tile = new JButton();
				tile.setActionCommand(i.toString());
				tile.setBackground(Color.black);
				tile.addActionListener(this);
				tile.setBorder(new LineBorder(Color.white, 2));
				board_panel.add(tile);
				i++;
			}
			gameFrame.revalidate();
			gameFrame.repaint();
			scaleIcons(new Dimension(25, 25));
			if (player1 == 'X') {
				playerIcon1.setIcon(cross);
				playerIcon2.setIcon(circle);
			} else {
				playerIcon2.setIcon(cross);
				playerIcon1.setIcon(circle);
			}
			scaleIcons(tile.getSize());
			gameRunning = true;
			return;
		}
		System.out.println(event.getActionCommand());
		if (Integer.parseInt(event.getActionCommand()) < 9) {
			if (!gameRunning)
				return;
			int buttonNumber = Integer.parseInt(event.getActionCommand());
			int row = buttonNumber / 3;
			int col = buttonNumber % 3;
			if (!game.makeMove(row, col)) {
				return;
			}
			JButton tileActivated = (JButton) event.getSource();
			ImageIcon turnIcon = (game.getPlayer() == 'X') ? cross : circle;
			tileActivated.setIcon(turnIcon);
			if (gameMode == "Human") {
				if (game.checkWin()) {
					gameEnded(game.getPlayer(), true);
				} else if (game.isBoardFull()) {
					gameEnded(game.getPlayer(), false);
				} else {
					game.switchPlayer();
				}
			}

			board_panel.revalidate();
			board_panel.repaint();
		}
	}

	private void gameEnded(char winnerPlayer, boolean victory) {
		gameRunning = false;
		JLabel winnerLabel;
		addPlayersPoints(victory);
		if (victory) {
			if (game.getPlayer() == player1)
				winnerLabel = new JLabel("Player 1 won. Do you want to play again:");
			else
				winnerLabel = new JLabel("Player 2 won. Do you want to play again:");
		} else {
			winnerLabel = new JLabel("It is a draw. Do you want to play again:");
		}
		winnerLabel.setFont(new Font("Calibri", Font.ITALIC, 20));
		winnerLabel.setForeground(Color.red);
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		yes.setActionCommand("Yes");
		yes.addActionListener(this);
		yes.setBackground(Color.BLACK);
		yes.setForeground(Color.yellow);
		no.setBackground(Color.black);
		no.setForeground(Color.yellow);
		no.setActionCommand("No");
		no.addActionListener(this);

		bottom.add(winnerLabel);
		bottom.add(yes);
		bottom.add(no);
		gameFrame.revalidate();
		gameFrame.repaint();
	}

	public void addPlayersPoints(boolean victory) {
		if (victory) {
			switch (playerScoreNumX) {
				case "0":
					playerScoreNumX = "1";
					break;
				case "1":
					playerScoreNumX = "2";
					break;
				case "2":
					playerScoreNumX = "3";
					break;
				case "3":
					playerScoreNumX = "4";
					break;
				case "4":
					playerScoreNumX = "5";
					break;
				case "5":
					playerScoreNumX = "6";
					break;
				case "6":
					playerScoreNumX = "7";
					break;
				case "7":
					playerScoreNumX = "8";
					break;
				case "8":
					playerScoreNumX = "9";
					break;
				case "9":
					playerScoreNumX = "10";
					break;
				default:
					playerScoreNumX = "MAXED";
					break;
			}
		} else {
			switch (playerScoreNumY) {
				case "0":
					playerScoreNumY = "1";
					break;
				case "1":
					playerScoreNumY = "2";
					break;
				case "2":
					playerScoreNumY = "3";
					break;
				case "3":
					playerScoreNumY = "4";
					break;
				case "4":
					playerScoreNumY = "5";
					break;
				case "5":
					playerScoreNumY = "6";
					break;
				case "6":
					playerScoreNumY = "7";
					break;
				case "7":
					playerScoreNumY = "8";
					break;
				case "8":
					playerScoreNumY = "9";
					break;
				case "9":
					playerScoreNumY = "10";
					break;
				default:
					playerScoreNumY = "MAXED";
					break;
			}
		}
	}
}