/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxsat_ga;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Korisnik
 */
public class Population {
    Individual[] population;
    int size;
    int[] grades;
    int maxGrade; // -1 not assigned
    int maxGradeIndex; // -1 not assigned
    
    Population(int size) {
        population = new Individual[size];
        grades = new int[size];
        this.size = size;
        maxGrade = -1;
        maxGradeIndex = -1;
    }
    Population(int size, Individual[] individuals, int[] grades, int maxGradeIndex, int maxGrade) {
        this.size = size;
        this.population = individuals;
        this.grades = grades;
        this.maxGradeIndex = maxGradeIndex;
        this.maxGrade = maxGrade;
    }
    Population(Individual[] individuals, int size) {
        this.population = individuals;
        this.maxGradeIndex = -1;
        this.maxGrade = -1;
        this.size = size;
        grades = new int[size];
    }
    
    public static Population generatePopulation(int populationSize, int individualSize) {
        Population newPopulation = new Population(populationSize);
        for(int i = 0; i< populationSize; ++i) {
            newPopulation.population[i] = Individual.generateRandomIndividual(individualSize);
        }
        return newPopulation;
    }
    public void evaluate(CNF formula) {
        for(int i = 0; i < this.size; ++i) {
            grades[i] = this.population[i].evaluateIndividual(formula);
            if(grades[i] > maxGrade) {
                maxGrade = grades[i];
                maxGradeIndex = i;
            }
        }
    }
    public Population selection(int newPopulationSize, int numberOfClauses) {
        Individual[] individuals = new Individual[newPopulationSize];
        int[] newGrades = new int[newPopulationSize];
        final int newMaxGradeIndex = 0;
        
        int currentNumberOfIndividuals = 0;
        
        while(currentNumberOfIndividuals < newPopulationSize) {
            for(int i = 0; i < this.size; ++i) {
                if(this.grades[i] == numberOfClauses) {
                    individuals[currentNumberOfIndividuals] = this.population[i];
                    newGrades[currentNumberOfIndividuals] = this.grades[i];
                    currentNumberOfIndividuals++;
                    if(currentNumberOfIndividuals == newPopulationSize)
                        break;
                }
            }
            numberOfClauses--;
        }
        return new Population(newPopulationSize, individuals, newGrades, newMaxGradeIndex, this.maxGrade);
    }
    public Population recombination(int newPopulationSize, int numberOfNewIndividuals) {
        Individual[] individuals = new Individual[newPopulationSize];
        
        for(int i = 0; i < this.size; ++i) {
            individuals[i] = this.population[i];
        }
        for(int j = newPopulationSize - numberOfNewIndividuals; j < newPopulationSize; j++) {
            
            Individual firstIndividual = individuals[ThreadLocalRandom.current()
                    .nextInt(0, newPopulationSize - numberOfNewIndividuals +1)];
            Individual secondIndividual = individuals[ThreadLocalRandom.current()
                    .nextInt(0, newPopulationSize - numberOfNewIndividuals +1)];
            Individual newIndividual = Individual.recombine(firstIndividual, secondIndividual);
            individuals[j] = newIndividual;
        }
        return new Population(individuals, newPopulationSize);
    }
}
