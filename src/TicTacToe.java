import java.util.Scanner;

public class TicTacToe {
    private char[][] board; // Since the char are basically row and col it more better to make the row and array to assign value.
    private char currentPlayer; //Player 1 is me. Player 2 is my little bother. Hope he doesn't reconnect

    public TicTacToe() {
        board = new char[3][3]; // Make (3 x 3) grid
        currentPlayer = 'X'; //Checks on the player
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) { // Creates grid
                board[i][j] = '-';
            }
        }
    }
    //Problem when testing: We need a outline of the grid. 11/27
    //Problem solved by making a row and col array
    // God damn it both hectors
    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    //Issue: Check if the Board is Full
    //Problem Solved
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '-') {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    private boolean checkWin() {
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

    private void switchPlayer() { // Switch Player function
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    //Comnplete all function of the game to make the game functional
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            printBoard();

            System.out.println("Player " + currentPlayer + "'s turn. Enter row (0-2) and column (0-2) separated by space:");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (makeMove(row, col)) {
                if (checkWin()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }
}
