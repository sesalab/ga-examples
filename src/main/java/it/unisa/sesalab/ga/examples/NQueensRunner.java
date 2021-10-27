package it.unisa.sesalab.ga.examples;

import it.unisa.sesalab.ga.examples.fix.IntegerNPointCrossover;
import it.unisa.sesalab.ga.examples.problem.NQueensProblem;
import it.unisa.sesalab.ga.examples.problem.NQueensStaircaseProblem;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GeneticAlgorithmBuilder;
import org.uma.jmetal.example.AlgorithmRunner;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.impl.IntegerPolynomialMutation;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.integersolution.IntegerSolution;
import org.uma.jmetal.util.JMetalLogger;

import java.util.Arrays;
import java.util.List;

public class NQueensRunner {

    public static void main(String[] args) {
        int chessSize = 8;

        double crossoverProbability = 0.8;
        double mutationProbability = 0.01;
        int maxEvaluations = 10000;
        int populationSize = 100;

        Problem<IntegerSolution> problem = new NQueensProblem("EightQueens", chessSize);
        BinaryTournamentSelection<IntegerSolution> selection = new BinaryTournamentSelection<>();
        CrossoverOperator<IntegerSolution> crossover = new IntegerNPointCrossover(crossoverProbability, 1);
        // With 0.01 probability a gene is mutated: a randomly-chosen gene is copied from the left side (0.5) or right side (0.5)
        IntegerPolynomialMutation mutation = new IntegerPolynomialMutation(mutationProbability, 0);

        Algorithm<IntegerSolution> sga = new GeneticAlgorithmBuilder<>(problem, crossover, mutation)
                .setPopulationSize(populationSize)
                .setMaxEvaluations(maxEvaluations)
                .setSelectionOperator(selection)
                .build();

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(sga).execute();
        IntegerSolution bestIndividual = sga.getResult();

        JMetalLogger.logger.info(String.format("Problem: %s", problem.getName()));
        JMetalLogger.logger.info(String.format("Solution: %s", bestIndividual.getVariables()));
        JMetalLogger.logger.info(String.format("Evaluation: %s", Arrays.toString(bestIndividual.getObjectives())));
        JMetalLogger.logger.info(String.format("Total execution time: %s ms", algorithmRunner.getComputingTime()));
    }
}
