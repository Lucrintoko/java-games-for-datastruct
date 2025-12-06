package games.gridblast.model;

import games.gridblast.view.UIConstants;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnifiedPieceFactory {

    private static final Random random = new Random();

    // Predefined shapes (you can add more)
    private static final boolean[][][] SHAPES = {
            // dot
            {
                    { true }
            },
            // 2x2 square
            {
                    { true, true },
                    { true, true }
            },
            // 3 horizontal
            {
                    { true, true, true }
            },
            // 3 vertical
            {
                    { true },
                    { true },
                    { true }
            },
            // L shape
            {
                    { true, false },
                    { true, false },
                    { true, true }
            },
            // T shape
            {
                    { true, true, true },
                    { false, true, false }
            }
    };

    public static List<BlockPiece> generatePieceSet() {
        List<BlockPiece> list = new ArrayList<>();
        list.add(randomPiece());
        list.add(randomPiece());
        list.add(randomPiece());
        return list;
    }

    public static BlockPiece randomPiece() {
        boolean[][] def = SHAPES[random.nextInt(SHAPES.length)];
        boolean[][] shapeCopy = copyShape(def);
        Color color = randomColor();
        return new BlockPiece(shapeCopy, color);
    }

    private static boolean[][] copyShape(boolean[][] src) {
        boolean[][] dest = new boolean[src.length][];
        for (int i = 0; i < src.length; i++) {
            dest[i] = new boolean[src[i].length];
            System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
        }
        return dest;
    }

    private static Color randomColor() {
        Color[] colors = UIConstants.BLOCK_COLORS;
        return colors[random.nextInt(colors.length)];
    }
}
