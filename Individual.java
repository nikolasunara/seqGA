/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxsat_ga;

import java.util.Random;
/**
 *
 * @author Korisnik
 */
public class Individual {
    
    boolean[] values;
    int size;
    static Random random = new Random();
    
    Individual() {
        values = new boolean[0];
        size = 0;
    }
    Individual(int size) {
        values = new boolean[size];
        this.size = size;
    }
    
    public static Individual generateRandomIndividual(int size) {
        Individual newIndividual = new Individual(size);
        for(int i = 0; i < size; ++i) {
            newIndividual.values[i] = random.nextBoolean();
        }
        return newIndividual;
    }
    public int evaluateIndividual(CNF formula) {
        Clause nClause;
        int result = 0;
        
        for(int i = 0; i < formula.numberOfClauses; ++i) {
            nClause = formula.getClauseByIndex(i);
            for(int j = 0; j < nClause.clauseLength; ++j) {
                if((values[ Math.abs(nClause.variables[j]) -1] && nClause.variables[j] > 0) ||
                        (!values[ Math.abs(nClause.variables[j]) -1] && nClause.variables[j] < 0)) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }
    public static Individual recombine(Individual firstIndividual, Individual secondIndividual) {
        Individual newIndividual = new Individual(firstIndividual.size);
        for(int i = 0; i < newIndividual.size; ++i) {
            if(random.nextBoolean() == true) {
                newIndividual.values[i] = firstIndividual.values[i];
            } else {
                newIndividual.values[i] = secondIndividual.values[i];
            }
        }
        return newIndividual;
    }
}
