import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // TicTacToe game = new TicTacToe();
        UI_Menu menu = new UI_Menu(new JFrame());
        System.out.println("Hello");
        int i = 0;
        while (true) {
            if (menu.state == "AI") {
                System.exit(0);
            }
        }
        // game.playGame();
    }
}
