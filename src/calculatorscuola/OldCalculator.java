/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorscuola;

import java.util.regex.Pattern;

/**
 *
 * @author lsdpirate
 */
public class OldCalculator {

    private Calc c;

    public OldCalculator() {
        this.c = new Calc();
    }

    public double Calculate(String [] s) {
        double r = Double.NaN;
        this.parseCalculus(s);
        r = this.c.getResult();
        return r;
    }
    public double Calculate (String o1, String s, String o2){
        
        double r = Double.NaN;
        
        double op1 = Double.parseDouble(o1);
        double op2 = Double.parseDouble(o2);
        
        this.c.setFirstOperand(op1);
        this.c.setSecondOperand(op2);
        
        if(s.equals("+")){
            this.c.sum();
        }else if(s.equals("-")){
            this.c.sub();
        }else if (s.equals("*")){
            this.c.mult();
        }else if(s.equals("/")){
            this.c.div();
        }
        r = this.c.getResult();
        return r;
    }

    
    /**
     * @deprecated 
     * @param s 
     */
    private void parseCalculus(String [] s) {

        String numberRegex = "(-?[1-9](([0-9]){1,9})?)";
        String operatorRegex = "";
        String expRegex = "(" + numberRegex + "?\\*|/|-|\\+" + numberRegex + "){1,10}";
        String [] operator = {"+", "-", "/", "*"};
        
        String test = "";
        for(String str: s){
            test += str;
        }
        System.out.println(test + " " + test.matches(expRegex));
        
        if(s[0].matches(numberRegex)){
            this.c.setFirstOperand(Double.parseDouble(s[0]));
        }else {
            this.c.setFirstOperand(0);
        }
        
        for(int i = 1; i < s.length; ++i){
            
            this.c.setSecondOperand(0);
            
            
            
            //Fix with something better
            if(operator[0].equals(s[i]) || operator[1].equals(s[i]) || operator[2].equals(s[i]) || operator[3].equals(s[i])){
                
                switch (s[i]) {
                    case "+":
                        this.c.setSecondOperand(Double.parseDouble(s[++i]));
                        this.c.sum();
                        break;
                    case "-":
                        this.c.setSecondOperand(Double.parseDouble(s[++i]));
                        this.c.sub();
                        break;
                    case "*":
                        this.c.setSecondOperand(Double.parseDouble(s[++i]));
                        this.c.mult();
                        break;
                    case "/":
                        this.c.setSecondOperand(Double.parseDouble(s[++i]));
                        this.c.div();
                        break;
                }
            }
        }
    }
}
