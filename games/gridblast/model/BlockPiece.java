package games.gridblast.model;

import java.awt.Color;

public class BlockPiece {

    private final boolean[][] shape;
    private final int width;
    private final int height;
    private final Color color;

    public BlockPiece(boolean[][] shape, Color color) {
        this.shape = shape;
        this.height = shape.length;
        this.width = shape[0].length;
        this.color = color;
    }

    public boolean[][] getShape() {
        return shape;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}
