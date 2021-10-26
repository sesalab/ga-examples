package it.unisa.sesalab.ga.examples;

import it.unisa.sesalab.ga.examples.fix.NPointCrossover;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GenerationalGeneticAlgorithm;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.impl.IntegerPolynomialMutation;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.integersolution.IntegerSolution;

public class EightQueensRunner {

    public static void main(String[] args) {
        double crossoverProbability = 0.8;
        double mutationProbability = 0.01;
        int maxEvaluations = 1000;
        int populationSize = 100;

        Problem<IntegerSolution> problem = new EightQueensProblem();
        BinaryTournamentSelection<IntegerSolution> selection = new BinaryTournamentSelection<>();
        CrossoverOperator<IntegerSolution> crossover = new NPointCrossover<>(crossoverProbability, 1);
        // Con probabilità 0.01 si perturba un gene: 0.5 si sceglie casualmente un gene da sinistra, altrimenti da destra
        IntegerPolynomialMutation mutation = new IntegerPolynomialMutation(mutationProbability, 0);

        GenerationalGeneticAlgorithm<IntegerSolution> sga = new GenerationalGeneticAlgorithm<>(
                problem,
                maxEvaluations,
                populationSize,
                crossover,
                mutation,
                selection,
                null
        );


    }
}
