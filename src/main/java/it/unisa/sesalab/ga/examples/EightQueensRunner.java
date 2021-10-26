package it.unisa.sesalab.ga.examples;

import it.unisa.sesalab.ga.examples.fix.IntegerNPointCrossover;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GenerationalGeneticAlgorithm;
import org.uma.jmetal.example.AlgorithmRunner;
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
        CrossoverOperator<IntegerSolution> crossover = new IntegerNPointCrossover(crossoverProbability, 1);
        // With 0.01 probability a gene is mutated: a randomly-chosen gene is copied from the left side (0.5) or right side (0.5)
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

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(sga).execute();
        IntegerSolution result = sga.getResult();
        System.out.println(result);
    }
}
