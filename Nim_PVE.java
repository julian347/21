/*
 * Julian Boada
 * julian.347@me.com
 */

/******************************PURPOSE*****************************************
This Version of NIM involves playing against the computer. 
The purpose of this game is to remove stars from a pile until there are none 
left to remove. The person that removes the last piece will lose. 
******************************************************************************/

package Nim;
import java.util.Scanner;
import java.util.Random;

public class Nim_PVE {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        Random ri = new Random();
        computer compute = new computer();
        int pileA = ri.nextInt(5);
        int pileB = ri.nextInt(5);
        int pileC = ri.nextInt(5);
        int roll2 = ri.nextInt(2);
        countpve c = new countpve(pileA, pileB, pileC);
        errorpve e = new errorpve();
        String nameTwo = "Jarvis";
        
        
        System.out.print("Player 1, enter your name: ");
        String nameOne = input.next();
        System.out.print("Your opponent this game will be " + nameTwo);
        if (roll2 == 1) {
            System.out.println("\n\nYou will go first. Good Luck.");
        } else {
            System.out.println("\n\nJarvis will go first. Good Luck.");
            String tempName = nameOne;
            nameOne = nameTwo;
            nameTwo = tempName;
        }
        
        
        c.counter();
        String currentName = nameOne;
        String prevName = nameTwo;
        String pileName = "A";
        String limitA = "NO";
        String limitB = "NO";
        String limitC = "NO";
         int removeNum = 0;
        int dig = 0;
        String computerPlaying = "NO";
        MainLoop:
        while ((c.A != 0) || (c.B != 0) || (c.C != 0)){
            if (c.A + c.B + c.C == 1){
                dig = 1;
                break;
            }
//********************************COMPUTER TURN*********************************
            if (currentName == "Jarvis") {
                if (c.A == 0){
                    limitA = "A";
                }
                if (c.B == 0){
                    limitB = "B";
                }
                if (c.C == 0){
                    limitC = "C";
                }
                compute.random(c.A, c.B, c.C, limitA, limitB, limitC);
                pileName = compute.rollString;
                removeNum = compute.rollNum;
                computerPlaying = "YES";
            }
//******************************************************************************
            if (computerPlaying != "YES"){
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
                break;
                }
            }    
            String tempName = currentName;
            currentName = prevName;
            prevName = tempName;
            computerPlaying = "NO";
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
        if (currentName == "Jarvis"){
            System.out.println("Jarvis must take the last remaining counter, "
                    + "so you win!");
        }else if (dig == 1){
                System.out.println(currentName + ", you must take the "
                        + "last remaining counter, so you lose.");
        }
        else {System.out.println(" oops, " + prevName 
                    + " you took the last counters so you lose.");
        }        
        
    }
}

class computer implements Runnable {
    Random rng = new Random();
    int rollInt;

    static String rollString;
    static int rollNum;
    public void random(int intA, int intB, int intC, 
            String strA, String strB, String strC){
        while (1>0){
            int rollStr = rng.nextInt(2);
            int rollInt = rng.nextInt();
            switch (rollStr){
                case 0:
                    rollString = "A";
                    if (rollString.equals(strA)){
                        continue;
                    } else if (strB == "B" && strC == "C"){
                        rollInt = intA - 1;
                    } else {rollInt = rng.nextInt(intA);}
                    break;
                case 1:
                    rollString = "B";
                    if (rollString.equals(strB)){
                        continue;
                    } else if (strA == "A" && strC == "C"){
                        rollInt = intB - 1;
                    } else {rollInt = rng.nextInt(intB);}
                    break;
                case 2:
                    rollString = "C";
                    if (rollString.equals(strC)){
                        continue;
                    } else if (strB == "B" && strA == "A"){
                        rollInt = intC - 1;
                    } else {rollInt = rng.nextInt(intC);}
                    break;
            }
            break;
        }
        
        while(1>0){
            switch (rollInt){
                case 0:
                    rollNum = 1;
                    break;
                case 1:
                    rollNum = 2;
                    break;
                case 2:
                    rollNum = 3;
                    break;
                case 3:
                    rollNum = 4;
                    break;
                case 4:
                    rollNum = 5;
                    break;
            }
            break;
        }
        run();
    }
    
    public void run(){
        System.out.print("Jarvis is choosing a pile: ");
        try {
        Thread.sleep(1500);
        } catch (InterruptedException e){}
        System.out.println(rollString);
        System.out.print("Jarvis is choosing a number to remove from pile: ");
        try {
        Thread.sleep(1500);
        } catch (InterruptedException e){}
        System.out.println(rollNum);
    }
}

class countpve {        
    static int A;
    static int B;
    static int C;
    countpve(int pileA, int pileB, int pileC){
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

class errorpve {
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
