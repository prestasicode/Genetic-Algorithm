
package GeneticAlgorithm;

/*
 * @author M Luthfi Shahab
 */

public class Population {
    Solution[] solution;
    
    // Construct a population
    public Population(int numberOfSolutions, boolean initialise) {
        solution = new Solution[numberOfSolutions];
        // If we need to initialise a population do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < numberOfSolutions; i++) {
                Solution newSolution = new Solution();
                saveSolution(i, newSolution);
            }
        }
    }
    
    // Save a solution
    public void saveSolution(int index, Solution newSolution) {
        solution[index] = newSolution;
    }
    
    // Gets a solution from population
    public Solution getSolution(int index) {
        return solution[index];
    }

    // Gets the best solution in the population
    public Solution getFittest() {
        Solution fittest = solution[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < size(); i++) {
            if (getSolution(i).getFitness() < fittest.getFitness()) {
                fittest = getSolution(i);
            }
        }
        return fittest;
    }
    
    public int size() {
        return solution.length;
    }
}