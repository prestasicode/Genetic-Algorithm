package GeneticAlgorithm;

/*
 * @author M Luthfi Shahab
 */

public class Main {

    public static void main(String[] args) {
        // n = numberOfVariables
        // function with n variables
        int numberOfVariables = 100;
        Library.setNumberOfVariables(numberOfVariables);
        
        int numberOfSolutions = 100;
        int numberOfIterations = 1000;
        
        // Initialize population
        Population pop = new Population(numberOfSolutions, true);
        System.out.println("Initial fitness : " + pop.getFittest().getFitness());

        // Evolve population
        for (int i = 0; i < numberOfIterations; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final result
        System.out.println();
        System.out.println("Final fitness : " + pop.getFittest().getFitness());
        System.out.print("Solution : ");
        pop.getFittest().printX();
    }
    
}