/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorscuola;

/**
 *
 * @author lsdpirate
 */
public class Calculator {
    private ScientificCalc c;
    
    public Calculator (){
        this.c = new ScientificCalc();
    }
    
    public double calculate(double op1, double op2, String operation){
        double d = 0;
        
        this.doCalc(op1, op2, operation);
        d = this.c.getResult();
        return d;
    }
    public double calculate(String op1, String op2, String operation){
        double d = 0;
        
        double o1 = Double.parseDouble(op1);
        double o2 = Double.parseDouble(op2);
        this.doCalc(o1, o2, operation);
        d = this.c.getResult();
        return d;
    }
    
    private void doCalc(double op1, double op2, String operation){
        this.c.setFirstOperand(op1);
        switch (operation) {
                    case "+":
                        this.c.setSecondOperand(op2);
                        this.c.sum();
                        break;
                    case "-":
                        this.c.setSecondOperand(op2);
                        this.c.sub();
                        break;
                    case "*":
                        this.c.setSecondOperand(op2);
                        this.c.mult();
                        break;
                    case "/":
                        this.c.setSecondOperand(op2);
                        this.c.div();
                        break;
                    case "^":
                        this.c.setSecondOperand(op2);
                        this.c.pow();
                        break;
                    case "sin":
                        this.c.sin();
                        break;
                    case "cos":
                        this.c.cos();
                        break;
                }
    }
}
