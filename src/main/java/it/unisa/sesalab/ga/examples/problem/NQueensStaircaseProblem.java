package it.unisa.sesalab.ga.examples.problem;

import org.uma.jmetal.solution.integersolution.IntegerSolution;

import java.util.List;

public class NQueensStaircaseProblem extends NQueensProblem {

    public NQueensStaircaseProblem(String name, int chessSize) {
        super(name, chessSize);
        setNumberOfObjectives(2);
    }

    @Override
    public void evaluate(IntegerSolution integerSolution) {
        super.evaluate(integerSolution);
        int steps = calculateSteps(integerSolution.getVariables());
        integerSolution.getObjectives()[1] = -1.0 * steps;
    }

    private int calculateSteps(List<Integer> encoding) {
        int steps = 0;
        for (int col = 0; col < getChessSize() - 1; col++) {
            int rowDistance = encoding.get(col) - encoding.get(col + 1);
            if (rowDistance == 2 || rowDistance == getChessSize() - 2) {
                steps++;
            }
        }
        return steps;
    }

}
