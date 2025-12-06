import games.simonsays.GamePanel;
import javax.swing.SwingUtilities;

public class MainMenu
    {
        public static void main(String[] args) 
            {
                SwingUtilities.invokeLater(() -> new GamePanel());
            }
    }