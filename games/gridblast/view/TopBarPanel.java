package games.gridblast.view;

import javax.swing.*;
import java.awt.*;

public class TopBarPanel extends JPanel {

    private final JButton menuButton;

    public TopBarPanel() {
        setBackground(UIConstants.BACKGROUND_COLOR);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        menuButton = new JButton("MENU");
        menuButton.setPreferredSize(new Dimension(120, 40));
        menuButton.setBackground(new Color(60, 60, 60));
        menuButton.setForeground(Color.WHITE);
        menuButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        menuButton.setFocusPainted(false);
        menuButton.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));

        add(menuButton);
    }

    public JButton getMenuButton() {
        return menuButton;
    }
}
