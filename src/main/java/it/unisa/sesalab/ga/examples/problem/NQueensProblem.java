package it.unisa.sesalab.ga.examples.problem;

import org.uma.jmetal.problem.integerproblem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.integersolution.IntegerSolution;

import java.util.ArrayList;
import java.util.List;

public class NQueensProblem extends AbstractIntegerProblem {
    private final int chessSize;

    public NQueensProblem(String name, int chessSize) {
        if (chessSize < 2) {
            throw new IllegalArgumentException("Cannot set chess size to a value lower than 2.");
        }
        this.chessSize = chessSize;

        setName(name);
        setNumberOfVariables(chessSize);
        List<Integer> lowerBounds = new ArrayList<>();
        List<Integer> upperBounds = new ArrayList<>();
        for (int i = 0; i < chessSize; i++) {
            lowerBounds.add(1);
            upperBounds.add(chessSize);
        }
        setVariableBounds(lowerBounds, upperBounds);
        setNumberOfObjectives(1);
    }

    @Override
    public void evaluate(IntegerSolution integerSolution) {
        int conflicts = calculateConflicts(integerSolution.getVariables());
        integerSolution.getObjectives()[0] = conflicts;
    }

    private int calculateConflicts(List<Integer> encoding) {
        int rowConflicts = 0;
        int upperRightConflicts = 0;
        int lowerRightConflicts = 0;
        for (int col = 0; col < chessSize; col++) {
            int row = encoding.get(col);
            for (int rightCol = col + 1; rightCol < chessSize; rightCol++) {
                int rightRow = encoding.get(rightCol);
                if (rightRow == row) {
                    rowConflicts++;
                } else if (rightRow == row - (rightCol - col)) {
                    upperRightConflicts++;
                } else if (rightRow == row + (rightCol - col)) {
                    lowerRightConflicts++;
                }
            }
        }
        return upperRightConflicts + lowerRightConflicts + rowConflicts;
    }

    public int getChessSize() {
        return chessSize;
    }
}
