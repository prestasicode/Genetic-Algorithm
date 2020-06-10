package GeneticAlgorithm;

/*
 * @author M Luthfi Shahab
 */

public class Solution {
    double x[] = new double[Library.getNumberOfVariables()];
    double fitness = 0;
    
    // Construct a random solution
    public Solution(){
        for (int i = 0; i < x.length; i++) {
            x[i] = Math.random()*200-100;
        }
    }
    
    public Solution(double a[]){
        for(int i = 0; i < x.length; i++) {
            x[i] = a[i];
        }
    }
    
    public void setX(int i, double a) {
        x[i] = a;
        fitness = 0;
    }
    
    public double[] getX() {
        return x;
    }
    
    public double getX(int i) {
        return x[i];
    }
    
    public double getFitness() {
        if (fitness == 0) {
            fitness = f();
        }
        return fitness;
    }
    
    public double f() {
        double result = 0;
        for(int i = 0; i < x.length; i++) {
            result = result + Math.pow(x[i], 2);
        }
        return result;
    }
    
    public void printX() {
        for(int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }
}