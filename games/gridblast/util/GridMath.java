package games.gridblast.util;

public final class GridMath {

    private GridMath() {}

    /**
     * Converts pixel coordinates to grid coordinates.
     */
    public static int toGridIndex(int pixel, int start, int cellSize, int gap) {
        return (pixel - start) / (cellSize + gap);
    }

    /**
     * Converts grid coordinates to pixel coordinates.
     */
    public static int toPixel(int index, int start, int cellSize, int gap) {
        return start + index * (cellSize + gap);
    }

    /**
     * Checks if a pixel coordinate lies inside a grid cell.
     */
    public static boolean inBounds(int pixel, int start, int size, int cellSize, int gap) {
        int end = start + size * (cellSize + gap);
        return pixel >= start && pixel <= end;
    }
}
