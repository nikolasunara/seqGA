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
public class CNF {
    int[][] variables;
    public int numberOfClauses;
    int clauseLength;
    
    CNF(int numberOfClauses, int clauseLength) {
        variables = new int[numberOfClauses][clauseLength];
        this.numberOfClauses = numberOfClauses;
        this.clauseLength = clauseLength;
    } 
    //test konstruktor
    CNF(int a, int b, int c) {
        variables = new int[2][3];
        this.numberOfClauses = 2;
        this.clauseLength = 3;
        variables[0][0] = a;
        variables[0][1] = b;
        variables[0][2] = c;
        variables[1][0] = -a;
        variables[1][1] = -b;
        variables[1][2] = -c;
    }
    
    public Clause getClauseByIndex(int index) {
        Clause newClause = new Clause(this.clauseLength, this.variables[index]);
        return newClause;
    }
}
