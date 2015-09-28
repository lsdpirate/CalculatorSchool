/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorscuola;

import java.util.Scanner;

/**
 *
 * @author lsdpirate
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OldCalculator c = new OldCalculator();
        Scanner scanner = new Scanner(System.in);
        String i1;
        System.out.println("********************");
        System.out.println("*****Calculator*****");
        System.out.println("********************");
        System.out.print("Insert the expression: ");
        
        
        try {
            
            i1 = scanner.nextLine();
            System.out.println(c.Calculate(i1));

        } catch (ArithmeticException ex) {
            
            System.out.println(ex.getMessage());
        }
        

    }

}
