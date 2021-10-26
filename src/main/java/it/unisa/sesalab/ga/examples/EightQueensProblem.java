package it.unisa.sesalab.ga.examples;

import org.uma.jmetal.problem.integerproblem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.integersolution.IntegerSolution;

import java.util.ArrayList;
import java.util.List;

public class EightQueensProblem extends AbstractIntegerProblem {
    public EightQueensProblem() {
        setName("EightQueens");
        setNumberOfObjectives(1);
        setNumberOfVariables(8);
        List<Integer> lowerBounds = new ArrayList<>();
        List<Integer> upperBounds = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            lowerBounds.add(1);
            upperBounds.add(8);
        }
        setVariableBounds(lowerBounds, upperBounds);
    }

    @Override
    public void evaluate(IntegerSolution integerSolution) {
        // TODO: Implement Eval. Function
    }
}
