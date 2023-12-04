import java.util.Scanner;

public class TicTacToe {
    private char[][] board; // Since the char are basically row and col it more better to make the row and array to assign value.
    private char currentPlayer; //Player 1 is me. Player 2 is my little bother. Hope he doesn't reconnect
	//private TicTacToeBot bot;

    public TicTacToe() {
        board = new char[3][3]; // Make (3 x 3) grid
        currentPlayer = 'X'; //Checks on the player
		initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) { // Creates grid
                board[i][j] = '-';
            }
        }
    }
    //Problem when testing: We need a outline of the grid. 11/27
    //Problem solved by making a row and col array
    // God damn it both hectors
    //Issue: Check if the Board is Full
    //Problem Solved
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '-') {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    public boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // Row win
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true; // Column win
            }
        }

        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; // Diagonal win
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true; // Diagonal win
        }

        return false;
    }

    public void switchPlayer() { // Switch Player function
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
	public char getPlayer(){return currentPlayer;}
	public void setPlayer(char player)
	{
		currentPlayer = player;
		switchPlayer();
	}
}
