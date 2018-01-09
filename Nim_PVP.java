/*
 * Julian Boada
 * julian.347@me.com
 */

/******************************PURPOSE*****************************************
This Version of NIM involves playing against another person. 
The purpose of this game is to remove stars from a pile until there are none 
left to remove. The person that removes the last piece will lose. 
******************************************************************************/

package Nim;
import java.util.Scanner;
import java.util.Random;

public class Nim_PVP {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        Random ri = new Random();
        int pileA = ri.nextInt(5);
        int pileB = ri.nextInt(5);
        int pileC = ri.nextInt(5);
        count c = new count(pileA, pileB, pileC);
        error e = new error();
        
        System.out.print("Player 1, enter your name: ");
        String nameOne = input.next();
        System.out.print("Player 2, enter your name: ");
        String nameTwo = input.next();
        
        c.counter();
        String currentName = nameOne;
        String prevName = nameTwo;
        String pileName = "A";
        int removeNum = 0;
        int dig = 0;
        
        MainLoop:
        while ((c.A != 0) || (c.B != 0) || (c.C != 0)){
            if (c.A + c.B + c.C == 1){
                dig = 1;
                break;
            }
            System.out.print(currentName + ", choose a pile: ");

            BackLoop:
            while (1>0){
                pileName = input.next();
                if (e.errorString(currentName, pileName, c.A, c.B, c.C) == 0){
                    continue;
                }
            
                System.out.print("How many to remove from pile " 
                            + pileName + ": ");
                while (1>0){
                    try {
                    removeNum = input.nextInt();
                    } catch (Exception p) {
                        continue BackLoop;
                    }
                    if (e.errorInt(pileName, removeNum, c.A, c.B, c.C) == 0){
                        continue;
                    }
                    break;
                }
            break;}
                
            if (currentName == nameOne){
                currentName = nameTwo;
                prevName = nameOne;
            } else {
                currentName = nameOne;
                prevName = nameTwo;
            }

            switch (pileName){
                case "A":
                    c.A = c.A - removeNum;
                    c.counter();
                    break;
                case "B":
                    c.B = c.B - removeNum;
                    c.counter();
                    break;
                case "C":
                    c.C = c.C - removeNum;
                    c.counter();
                    break;
            }
        }
        if (dig == 1){
            System.out.println(currentName + ", you must take the "
                    + "last remaining counter, so you lose. "
                    + prevName 
                    + " wins!");
        }
        else {System.out.println(prevName 
                    + " took the last multiple counters... Oops, so "
                    + currentName
                    + "  wins!");
        }        
        
    }
}


class count {        
    static int A;
    static int B;
    static int C;
    count(int pileA, int pileB, int pileC){
        A = pileA;
        B = pileB;
        C = pileC;
    }
    
    public static void counter(){
        int rows = 5;
        String aStar;
        String bStar;
        String cStar;
        while (rows != 0){
            if (A >= rows) {aStar = "          *";} else {aStar = "           ";}
            if (B >= rows) {bStar = "*";} else {bStar = " ";}
            if (C >= rows) {cStar = "*";} else {cStar = " ";}
            System.out.println(aStar + " " + bStar + " " + cStar);
            rows = rows - 1;
        }
        System.out.println("          A B C\n");     
    }
}

class error {
    public int errorString(String name, 
            String varStr, 
            int A, int B, int C){
        int conditionNum = 1;
        if (varStr.compareTo("C") < -10){
            System.out.print(varStr + " must be a name of a pile. Choose a pile: ");
            return conditionNum = 0;
        }
        if (varStr.compareTo("C") > 0 || varStr.compareTo("C") < -2){
            System.out.print(varStr + " does not exist. Choose a pile: ");
            return conditionNum = 0;
        }
        
        switch (varStr){
                case "A":
                    if (A == 0){
                        System.out.print("Nice try, " 
                                + name
                                + ". That pile is empty. Choose a pile: "); 
                    conditionNum = 0;
                    }
                    break;
                case "B":
                    if (B == 0){
                        System.out.print("Nice try, " 
                                + name
                                + ". That pile is empty. Choose a pile: "); 
                    conditionNum = 0;
                    }
                    break;
                case "C":
                    if (C == 0){
                        System.out.print("Nice try, " 
                                + name
                                + ". That pile is empty. Choose a pile: "); 
                    conditionNum = 0;
                    }
                    break;
        }
        return conditionNum;
    }
    
    public int errorInt(String varStr, int varInt, int A, int B, int C){
        int conditionNum = 1;
        switch (varStr){
                case "A":
                    if (A < varInt){
                        System.out.print("Pile " 
                        + varStr 
                        + " doesn't have that many. How many? "); 
                        conditionNum = 0;
                    }
                    break;
                case "B":
                    if (B < varInt){
                        System.out.print("Pile " 
                        + varStr 
                        + " doesn't have that many. How many? "); 
                        conditionNum = 0;
                    }
                    break;
                case "C":
                    if (C < varInt){
                        System.out.print("Pile " 
                        + varStr 
                        + " doesn't have that many. How many? ");
                        conditionNum = 0;
                    }
                    break;
        }
        if (varInt <= 0){
            System.out.print("You must choose at least 1. How many? ");
            conditionNum = 0;
        }
        return conditionNum;
    }
    
}
