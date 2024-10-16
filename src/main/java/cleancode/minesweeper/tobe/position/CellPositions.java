package cleancode.minesweeper.tobe.position;

import cleancode.minesweeper.tobe.cell.Cell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CellPositions {

    private final List<CellPosition> positions;

    private CellPositions(List<CellPosition> positions) {
        this.positions = positions;
    }

    public static CellPositions of(List<CellPosition> positions) {
        return new CellPositions(positions);
    }

    public static CellPositions from(Cell[][] board) {
        List<CellPosition> cellPositions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                CellPosition cellPosition = CellPosition.of(i, j);
                cellPositions.add(cellPosition);
            }
        }
        return of(cellPositions);
    }

    public List<CellPosition> extractRandomPositions(int count) {
        List<CellPosition> cellPositions = new ArrayList<>(this.positions);
        Collections.shuffle(cellPositions);
        return cellPositions.subList(0, count);
    }

    public List<CellPosition> subtract(List<CellPosition> positionListToSubtract) {
        List<CellPosition> cellPositions = new ArrayList<>(this.positions);
        CellPositions positionsToSubtract = CellPositions.of(positionListToSubtract);

        return cellPositions.stream()
                .filter(positionsToSubtract::doesNotContain)
                .toList();
    }

    public List<CellPosition> getPositions() {
        return new ArrayList<>(positions);
    }

    private boolean doesNotContain(CellPosition position) {
        return !positions.contains(position);
    }

}
