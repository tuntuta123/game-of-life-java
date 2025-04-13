package conway.challenge;

import conway.gameBasicAlgo.logics.Grid;
import conway.gameBasicAlgo.shapes.*;
import conway.gameBasicAlgo.graphics.GridPanel;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.*;

public class PuzzleChallenge {
    private Grid targetGrid;       
    private GridPanel targetPanel; 
    private int moveCount = 0;     
    private int moveLimit;         
    private int generationLimit;   
    private int size;
    private Color liveCellColor;
    private Color deadCellColor;
    private boolean emojisEnabled;

    private static final Map<String, Shapes> shapeMap = new HashMap<>();

    static {
        shapeMap.put("Block", new Block());
        shapeMap.put("Blinker", new Blinker());
        shapeMap.put("Glider", new Glider());
    }

    public PuzzleChallenge(int size, Color liveCellColor, Color deadCellColor, boolean emojisEnabled, int moveLimit, int generationLimit) {
        this.size = size;
        this.liveCellColor = liveCellColor;
        this.deadCellColor = deadCellColor;
        this.emojisEnabled = emojisEnabled;
        this.moveLimit = moveLimit;
        this.generationLimit = generationLimit;
        targetGrid = new Grid(size);
        clearTargetGrid();
        targetPanel = new GridPanel(targetGrid, liveCellColor, deadCellColor, emojisEnabled);
    }

    private void clearTargetGrid() {
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                targetGrid.setNode(x, y, false);
            }
        }
    }

    public void setTargetPattern(String figureName) {
        clearTargetGrid();
        int mid = size / 2 - 1;
        Shapes targetShape = shapeMap.getOrDefault(figureName, new Glider()); 
        targetShape.applyShape(targetGrid, mid, mid);
        if(targetPanel != null) {
            targetPanel.repaint();
        }
    }

    public JPanel getTargetPanel() {
        return targetPanel;
    }

    public boolean canToggleCell() {
         return moveCount < moveLimit;
    }

    public void incrementMoveCount() {
         moveCount++;
    }

    public int getMoveLimit() {
         return moveLimit;
    }

    public int getGenerationLimit() {
         return generationLimit;
    }

    public boolean checkPuzzleSolution(Grid currentGrid) {
         for (int x = 0; x < size; x++){
              for (int y = 0; y < size; y++){
                  if (currentGrid.getNode(x, y).isAlive() != targetGrid.getNode(x, y).isAlive()){
                      return false;
                  }
              }
         }
         return true;
    }

    public int compareTo(PuzzleChallenge other) {
        return Integer.compare(this.size, other.size);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PuzzleChallenge that = (PuzzleChallenge) obj;
        return targetGrid.equals(that.targetGrid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetGrid);
    }
}

