import javax.swing.SwingUtilities;
import simonsays.GamePanel;

public class MainMenu
    {
        public static void main(String[] args) 
            {
                SwingUtilities.invokeLater(() -> new GamePanel());
            }
    }