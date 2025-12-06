package simonsays.simonbutton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import simonsays.GamePanel;

public class SimonButton extends JPanel implements MouseListener
    {
        //Atrributes
        private Color baseColor;
        private Color flashColor;
        private int code;
        private boolean isLit;
        private GamePanel gamePanel;

        public SimonButton (GamePanel gamePanel, Color base, Color flash, int code)
            {
                this.gamePanel = gamePanel;
                //Setting fields
                this.baseColor = base;
                this.flashColor = flash;
                this.code = code;
                //Default state of button
                this.isLit = false;
                //Setting Button Color
                this.setBackground(baseColor);
                //Adding a Border for clear definition
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                //Ensures that this has a Listener to allow for interactivity
                this.addMouseListener(this);
            }

        //Method for flashing of buttons
        public void flash ()
            {
                //Set the Color to the designated Flash Color
                setBackground(flashColor);
                //Set it to true
                isLit = true;
                //Refresh visual elements
                repaint();

                //Turn off after 400ms
                Timer offTimer = new Timer(400, e ->
                    {
                        //Set it to the Base Color
                        setBackground(baseColor);
                        //Turn it back to false after the delay
                        isLit = false;
                        //Refreshes visual elements
                        repaint();
                        //Stops the timer
                        ((Timer)e.getSource()).stop();
                    });
                //Disable repetition
                offTimer.setRepeats(false);                    
                offTimer.start();
            }

        @Override
        public void mouseClicked (MouseEvent e)
            {}

        @Override
        public void mousePressed (MouseEvent e)
            {
                if (gamePanel.isTakingInput())
                    {
                        //Change to Flash Color when pressed
                        setBackground(flashColor);
                        repaint();
                        gamePanel.handleInput(code);
                    }
            }

        @Override
        public void mouseReleased (MouseEvent e)
            {
                // Return to base color on release
                if (!isLit) { // Don't override a programmed flash if overlapping
                    setBackground(baseColor);
                    repaint();
                }
            }

        @Override
        public void mouseEntered (MouseEvent e)
            {}

        @Override
        public void mouseExited (MouseEvent e)
            {}
    }