import games.gridblast.GridBlast;
import games.simonsays.SimonSays;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream; // Import GridBlast
import javax.swing.*; // Import SimonSays

public class MainMenu extends JFrame implements ActionListener
    {
        JButton[] gameButtons;
        JPanel headerPanel;
        //Creating an array for the button names and their file names
        String[] btnNames = 
            {
                "GridBlast", "SimonSays"
            };
        String[] btnAssets =
            {
                "GridBlast_Icon.png", "SimonSays_Icon.png"
            };
        

        public MainMenu ()
            {
                //JFrame default configurations
                this.setLayout(new BorderLayout());
                //Center the WIndow
                this.setLocationRelativeTo(null);
                //Ensures that the window will awlays be the same size
                this.setPreferredSize(new Dimension(400, 400));
                this.setMinimumSize(new Dimension(400, 400));
                this.setMaximumSize(new Dimension(400, 400));
                this.getContentPane().setBackground(Color.BLACK); 
                //Set content pane background
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                /*---------------------------- Header Panel ----------------------------*/
                //Adding a Header to the JFrame
                headerPanel = new JPanel(new BorderLayout());
                headerPanel.setBackground(Color.BLACK);
                headerPanel.setPreferredSize(new Dimension(0, 50));

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
                headerLabel.setForeground(Color.WHITE);
                //Adding Padding
                headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                //Center the Text
                headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

                //Adding to Header Panel
                headerPanel.add(headerLabel, BorderLayout.CENTER);

                //Adding to the North Side (Above) of the JFrame
                this.add(headerPanel, BorderLayout.NORTH);

                /*---------------------------- Game Buttons ----------------------------*/
                //Creating a JPanel to add the buttons
                JPanel gameButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
                gameButtonPanel.setBackground(Color.BLACK); // Set game button panel background
                //Insantiating the JButton Array
                gameButtons = new JButton[btnNames.length];

                //Creating the buttons
                for (int i = 0; i < btnNames.length; i++)
                    {
                        //Creating the buttons
                        gameButtons[i] = createButton(btnNames[i], btnAssets[i]);

                        //Set their Action Command Names and what they will appear as to the syste,
                        gameButtons[i].setActionCommand(btnNames[i]);
                        gameButtons[i].setToolTipText(btnNames[i]);
                        gameButtons[i].addActionListener(this); // Add action listener
                        gameButtonPanel.add(gameButtons[i]); // Add button to the new panel
                    }
                
                //JPanel to hold gameButtonPanel to easily Center them in empty space
                JPanel centerPanel = new JPanel(new GridBagLayout());
                centerPanel.setBackground(Color.BLACK); // Set center panel background
                centerPanel.add(gameButtonPanel);
                //Add to the JFrames Center
                this.add(centerPanel, BorderLayout.CENTER);

                //Centers the Window
                this.setLocationRelativeTo(null);
                //To make the JFrame Visible
                this.setVisible(true);
            }
        
        //Main Driver Code
        public static void main(String[] args) 
            {
                SwingUtilities.invokeLater(() -> new MainMenu());
            }

        //Logical Handler for Buttons of each respective game
        @Override
        public void actionPerformed (ActionEvent e)
            {
                //Initialize a String variable that stores Button Pressed Name
                String command = e.getActionCommand();

                //Lesbert's Game
                if ("GridBlast".equals(command)) 
                    {
                        this.dispose(); //Close MainMenu
                        JFrame gridBlastFrame = new JFrame("Grid Blast");
                        gridBlastFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        gridBlastFrame.setLayout(new BorderLayout());
                        gridBlastFrame.setSize(900, 700);
                        gridBlastFrame.setLocationRelativeTo(null);
                        
                        //Add Window Listener to reopen Main Menu on close
                        gridBlastFrame.addWindowListener(new WindowAdapter() 
                            {
                                @Override
                                public void windowClosed(WindowEvent e) 
                                    {
                                        new MainMenu();
                                    }
                            });

                        GridBlast.startGame(gridBlastFrame);
                        gridBlastFrame.setVisible(true);
                    } 
                //My Game
                else if ("SimonSays".equals(command)) 
                    {
                        this.dispose(); //Close MainMenu
                        SimonSays simonSaysFrame = new SimonSays(); //Insantiate the JFrame of SimonSays
                        simonSaysFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        
                        //Add Window Listener to reopen Main Menu on close
                        simonSaysFrame.addWindowListener(new WindowAdapter() 
                            {
                                @Override
                                public void windowClosed(WindowEvent e) 
                                    {
                                        new MainMenu();
                                    }
                            });
                    }
            }

        //Method for creating buttons
        public JButton createButton (String name, String fileName)
            {
                //Insantiate a JButton Variable to Return to the Constructor
                JButton button = new JButton();
                button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
                button.setBackground(Color.BLACK); // Set button background

                //Take Image Icon Asset
                ImageIcon originalIcon = new ImageIcon(getClass().getResource("/games/buttonassets/" + fileName));
                Image image = originalIcon.getImage(); // transform it 
                Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                ImageIcon icon = new ImageIcon(newimg);  // transform it back
                
                JLabel imageLabel = new JLabel(icon);
                //Centers the Image
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                
                //Insantiating a Custom Font
                Font customFont;
                try 
                    {
                        InputStream is = getClass().getResourceAsStream("/games/simonsays/assets/fonts/Montserrat-Bold.ttf");
                        customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);
                    }
                catch (Exception e)
                    {
                        customFont = new Font("Arial", Font.BOLD, 20);
                    }

                //Text Label of the Name of the Game
                JLabel textLabel = new JLabel(name);
                textLabel.setFont(customFont);
                textLabel.setForeground(Color.WHITE); // Set text color to white
                textLabel.setPreferredSize(new Dimension(0, 25));
                //Centers the Text Label
                textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Add components to the button
                button.add(Box.createVerticalStrut(10));
                button.add(imageLabel);
                button.add(Box.createVerticalStrut(8));
                button.add(textLabel);
                button.add(Box.createVerticalStrut(10));

                // Remove default button styling
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setFocusPainted(false);

                return button;
            }
    }