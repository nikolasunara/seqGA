/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxsat_ga;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Nikola
 */
public class SequentialGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        long startTime = System.currentTimeMillis();;
        CNF formula = new CNF(1,2,-3); // učitati formulu
        Population population = Population.generatePopulation(12, 3);
        population.evaluate(formula);
        
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        
        while(population.maxGrade < formula.numberOfClauses && timeElapsed < 20000) {
            
            population = population.selection();
            population = population.recombination();
            population.evaluate(formula);
            
            endTime = System.currentTimeMillis();
            timeElapsed = endTime - startTime;
        }
        /*
        System.out.println(Arrays.toString(population.grades));
        for(int k = 0; k < 12; ++k)
            System.out.println(Arrays.toString(population.population[k].values));
        
        population = population.selection(9, 2);
        
        System.out.println(Arrays.toString(population.grades));
        for(int z = 0; z < 9; ++z)
            System.out.println(Arrays.toString(population.population[z].values));
        
        population = population.recombination(12, 3);
        population.evaluate(formula);
        
        System.out.println(Arrays.toString(population.grades));
        for(int k = 0; k < 12; ++k)
            System.out.println(Arrays.toString(population.population[k].values));
        */
    }
    
}
