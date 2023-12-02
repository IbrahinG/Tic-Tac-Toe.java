import java.awt.*;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        UI_Menu menu = new UI_Menu(new JFrame());
		while (true) {
			if(menu.getStateFromMenu().equals("AI"))
			{
				UI_Game game_UI = new UI_Game(menu.getFrame(),menu.getStateFromMenu());
				break;
			}
		}



    }
}
