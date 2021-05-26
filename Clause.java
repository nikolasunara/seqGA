/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxsat_ga;

/**
 *
 * @author Korisnik
 */
public class Clause {
    int variables[];
    int clauseLength;
    
    Clause(int numberOfLiterals) {
        variables = new int[numberOfLiterals];
        clauseLength = numberOfLiterals;
    }
    Clause(int numberOfLiterals, int[] valuesOfLiterals) {
        variables = new int[numberOfLiterals];
        variables = valuesOfLiterals;
        clauseLength = numberOfLiterals;
    }
}
