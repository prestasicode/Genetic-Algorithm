package GeneticAlgorithm;

/*
 * @author M Luthfi Shahab
 */

public class GA {

    /* GA parameters */
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveSolution(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            // Select parents
            Solution parent1 = pop.getSolution(i);
            Solution parent2 = tournamentSelection(pop);
            // Crossover parents
            Solution child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveSolution(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            // If you can guarantee that the obtained result will be better, you can remove the if statement
            //if(Math.random() < mutationRate) {
                mutate(newPopulation.getSolution(i));
            //}
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Solution crossover(Solution parent1, Solution parent2) {
        // Create new child solution
        Solution child1 = new Solution(parent1.getX());
        
        for(int i = 0; i < Library.getNumberOfVariables(); i++) {
            Solution child2 = new Solution(child1.getX());
            child1.setX(i, (parent1.getX(i)+parent2.getX(i))/2);
            
            // jika lebih buruk, maka dikembalikan
            if(child1.getFitness() > child2.getFitness()) {
                child1.setX(i, child2.getX(i));
            }
        }
        
        return child1;
    }

    // Mutate a solution
    private static void mutate(Solution solution) {
        for(int i = 0; i < Library.getNumberOfVariables(); i++) {
            Solution solution2 = new Solution(solution.getX());
            solution.setX(i, solution.getX(i)+Math.random()-0.5);
            
            // jika lebih buruk, maka dikembalikan
            if(solution.getFitness() > solution2.getFitness()) {
                solution.setX(i, solution2.getX(i));
            }
        }
    }

    // Selects candidate solution for crossover
    private static Solution tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate solution and add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveSolution(i, pop.getSolution(randomId));
        }
        // Get the fittest
        Solution fittest = tournament.getFittest();
        return fittest;
    }
}
