package games.gridblast.view;

import java.awt.Color;
import java.awt.Font;

public class UIConstants {

    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color GRID_COLOR = new Color(40, 40, 40);

    public static final Color PREVIEW_COLOR = new Color(100, 200, 255, 120);
    public static final Color PREVIEW_INVALID_COLOR = new Color(255, 80, 80, 140);

    public static final int BOARD_PADDING = 40;

    public static final Font SCORE_FONT = new Font("SansSerif", Font.BOLD, 28);

    public static final Color[] BLOCK_COLORS = {
        new Color(255, 80, 80),
        new Color(255, 160, 0),
        new Color(255, 255, 0),
        new Color(0, 200, 0),
        new Color(0, 180, 255),
        new Color(160, 80, 255),
        new Color(255, 100, 200)
    };
}
