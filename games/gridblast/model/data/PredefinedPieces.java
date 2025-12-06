package games.gridblast.model.data;

import games.gridblast.model.BlockPiece;
import games.gridblast.view.UIConstants;

import java.awt.Color;

public class PredefinedPieces {

    // Helper to pick a color from your palette
    private static Color color(int index) {
        return UIConstants.BLOCK_COLORS[index % UIConstants.BLOCK_COLORS.length];
    }

    public static final BlockPiece ONE = new BlockPiece(
            new boolean[][] {
                    { true }
            },
            color(0)
    );

    public static final BlockPiece TWO_HORIZONTAL = new BlockPiece(
            new boolean[][] {
                    { true, true }
            },
            color(1)
    );

    public static final BlockPiece TWO_VERTICAL = new BlockPiece(
            new boolean[][] {
                    { true },
                    { true }
            },
            color(1)
    );

    public static final BlockPiece THREE_HORIZONTAL = new BlockPiece(
            new boolean[][] {
                    { true, true, true }
            },
            color(2)
    );

    public static final BlockPiece THREE_VERTICAL = new BlockPiece(
            new boolean[][] {
                    { true },
                    { true },
                    { true }
            },
            color(2)
    );

    public static final BlockPiece SQUARE_2X2 = new BlockPiece(
            new boolean[][] {
                    { true, true },
                    { true, true }
            },
            color(3)
    );

    public static final BlockPiece L_SHAPE = new BlockPiece(
            new boolean[][] {
                    { true, false },
                    { true, false },
                    { true, true }
            },
            color(4)
    );

    public static final BlockPiece L_SHAPE_FLIPPED = new BlockPiece(
            new boolean[][] {
                    { false, true },
                    { false, true },
                    { true,  true }
            },
            color(4)
    );

    public static final BlockPiece T_SHAPE = new BlockPiece(
            new boolean[][] {
                    { true, true, true },
                    { false, true, false }
            },
            color(5)
    );

    public static final BlockPiece BLOCK_3X3 = new BlockPiece(
            new boolean[][] {
                    { true, true, true },
                    { true, true, true },
                    { true, true, true }
            },
            color(6)
    );
}
