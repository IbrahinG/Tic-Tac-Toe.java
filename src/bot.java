<<<<<<< HEAD

=======
import java.util.Random;

public class TicTacToeBot {

    private char botSymbol; // 'X' or 'O'
    private char opponentSymbol; // Opponent's symbol, opposite of botSymbol

    public TicTacToeBot(char botSymbol) {
        this.botSymbol = botSymbol;
        this.opponentSymbol = (botSymbol == 'X') ? 'O' : 'X';
    }

    // Method to make a move for the bot
    public void makeMove(char[][] board) {
        int[] move = getBestMove(board);
        int row = move[0];
        int col = move[1];

        // Make the move
        board[row][col] = botSymbol;

        System.out.println("Bot chooses row " + row + " and column " + col);
    }

    // Method to find the best move using a simple random strategy
    private int[] getBestMove(char[][] board) {
        // Check for empty spots and choose a random one
        Random random = new Random();
        int row, col;

        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');

        return new int[]{row, col};
    }

    // You can add more sophisticated AI logic here for a more challenging bot
    // For example, checking for possible winning moves, blocking opponent's moves, etc.

    // Helper method to check if a player has won
    private boolean isWinner(char[][] board, char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    // Helper method to check if the board is full
    private boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Helper method to check if the game is over
    public boolean isGameOver(char[][] board) {
        return isWinner(board, botSymbol) || isWinner(board, opponentSymbol) || isBoardFull(board);
    }
}
>>>>>>> e615fc5a6ed656342cdbc89cee3ecdb0c295886b
