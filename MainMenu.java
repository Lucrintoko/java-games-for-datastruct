import games.simonsays.SimonSays;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener
    {
        JButton btnGridBlast, btnSimonSays;
        JPanel headerpanel;

        public MainMenu ()
            {
                //JFrame default configurations
                this.setLayout(new BorderLayout());
                //Center the WIndow
                this.setLocationRelativeTo(null);
                //Ensures that the window will awlays be the same size
                this.setPreferredSize(new Dimension(600, 600));
                this.setMinimumSize(new Dimension(600, 600));
                this.setMaximumSize(new Dimension(600, 600));
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                /*---------------------------- Header Panel ----------------------------*/
                //Adding a Header to the JFrame
                headerpanel = new JPanel(new BorderLayout());
                headerpanel.setPreferredSize(new Dimension(0, 50));

                //Insantiating a Custom Font for Labels and Buttons
                Font customFont;
                try
                    {
                        InputStream is = getClass().getResourceAsStream("/games/simonsays/assets/fonts/Montserrat-Bold.ttf");
                        customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
                    }
                catch (Exception e)
                    {
                        customFont = new Font("Arial", Font.BOLD, 30);
                    }

                //Text to be place
                JLabel headerLabel = new JLabel("Choose a Game");
                headerLabel.setFont(customFont);
                //Adding Padding
                headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
                //Center the Text
                headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                //Adding to Header Panel
                headerpanel.add(headerLabel, BorderLayout.CENTER);

                //Adding to the North Side (Above) of the JFrame
                this.add(headerLabel, BorderLayout.NORTH);

                /*---------------------------- Game Buttons ----------------------------*/
                btnGridBlast = new JButton();
                btnSimonSays = new JButton();

                //To make the JFrame Visible
                this.setVisible(true);
            }
        
        //Main Driver Code
        public static void main(String[] args) 
            {
                SwingUtilities.invokeLater(() -> new SimonSays());
            }

        //Logical Handler for Buttons of each respective game
        @Override
        public void actionPerformed (ActionEvent e)
            {
                
            }
    }