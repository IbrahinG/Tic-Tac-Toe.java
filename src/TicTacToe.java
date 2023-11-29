import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}

class TicTacToe {
    private char[][] board; // Since the char are basically row and col it more better to make the row and
                            // array to assign value.
    private char currentPlayer; // Player 1 is me. Player 2 is my little brother. Hope he doesn't reconnect
    private Random random; // Random number generator for choosing starting player
    private int player1Wins;
    private int player2Wins;

    public TicTacToe() {
        board = new char[3][3]; // Make (3 x 3) grid
        random = new Random();
        currentPlayer = (random.nextBoolean()) ? 'X' : 'O'; // Randomly choose starting player
        player1Wins = 0;
        player2Wins = 0;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) { // Creates grid
                board[i][j] = '-';
            }
        }
    }

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

    // Complete all functions of the game to make the game functional
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            printBoard();

            System.out.println(
                    "Player " + currentPlayer + "'s turn. Enter row (0-2) and column (0-2) separated by space:");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (makeMove(row, col)) {
                if (checkWin()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    updateWins();
                    if (isGameFinished()) {
                        printFinalResult();
                        if (askForRestart(scanner)) {
                            resetGame();
                        } else {
                            break;
                        }
                    } else {
                        resetBoard();
                    }
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    if (isGameFinished()) {
                        printFinalResult();
                        if (askForRestart(scanner)) {
                            resetGame();
                        } else {
                            break;
                        }
                    } else {
                        resetBoard();
                    }
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    private void updateWins() {
        if (currentPlayer == 'X') {
            player1Wins++;
        } else {
            player2Wins++;
        }
    }

    private boolean isGameFinished() {
        return player1Wins == 2 || player2Wins == 2;
    }

    private void printFinalResult() {
        System.out.println("Game Over! Final Result:");
        System.out.println("Player X Wins: " + player1Wins);
        System.out.println("Player O Wins: " + player2Wins);
        if (player1Wins > player2Wins) {
            System.out.println("Player X is the winner!");
        } else if (player2Wins > player1Wins) {
            System.out.println("Player O is the winner!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void resetBoard() {
        initializeBoard();
        currentPlayer = (random.nextBoolean()) ? 'X' : 'O'; // Randomly choose starting player
    }

    private void resetGame() {
        initializeBoard();
        player1Wins = 0;
        player2Wins = 0;
        currentPlayer = (random.nextBoolean()) ? 'X' : 'O'; // Randomly choose starting player
    }

    private boolean askForRestart(Scanner scanner) {
        System.out.println("Do you want to play again? (yes/no)");
        String response = scanner.next().toLowerCase();
        return response.equals("yes");
    }
}
