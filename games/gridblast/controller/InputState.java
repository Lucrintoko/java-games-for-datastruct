package games.gridblast.controller;

import games.gridblast.model.BlockPiece;

public class InputState {

    private BlockPiece selectedPiece;

    public void setSelectedPiece(BlockPiece piece) {
        this.selectedPiece = piece;
    }

    public BlockPiece getSelectedPiece() {
        return selectedPiece;
    }

    public boolean hasSelection() {
        return selectedPiece != null;
    }

    public void clearSelection() {
        selectedPiece = null;
    }
}
