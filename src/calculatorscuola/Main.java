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
        String i1, i2, i3;
        
     /*   System.out.print("Enter the first operand: ");
        i1 = scanner.nextLine();
        System.out.print("Enter the operation sign: ");
        i2 = scanner.nextLine();
        System.out.print("Enter the second operand: ");
        i3 = scanner.nextLine();
        double r = c.calculate(i1, i3, i2);
        
        System.out.println("Result: " + r);*/
        
        i1 = scanner.nextLine();
        System.out.println(c.Calculate(i1));
        //String reg = "((\\d)((\\+)|(-)|(\\*)|(/)))+(\\d)";
        
        
    }
    
}
