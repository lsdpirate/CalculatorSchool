/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorscuola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
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

    public double Calculate(String s) {
        double r = Double.NaN;
        this.parseCalculus(s);
        r = this.c.getResult();
        return r;
    }

    public double Calculate(String o1, String s, String o2) {

        double r = Double.NaN;

        double op1 = Double.parseDouble(o1);
        double op2 = Double.parseDouble(o2);

        this.c.setFirstOperand(op1);
        this.c.setSecondOperand(op2);

        if (s.equals("+")) {
            this.c.sum();
        } else if (s.equals("-")) {
            this.c.sub();
        } else if (s.equals("*")) {
            this.c.mult();
        } else if (s.equals("/")) {
            this.c.div();
        }
        r = this.c.getResult();
        return r;
    }

    /**
     * @deprecated @param s
     */
    private void parseCalculus(String exp) {

        String regex = "((\\d)((\\+)|(-)|(\\*)|(/)))+(\\d)";

           //First of all we will have to normalize the string to a non spaceless
        //expression.
        exp = exp.replaceAll(" ", "");

        //Then we assure that is matches the expression format.
        if (!exp.matches(regex)) {
            return;
        }
           //After that we split all the expression elements into an array
        //for better management.

       
        String [] splitExp = this.expStringToArray(exp);

           //Now we have to look for all the divisions
        //and multiplications to compute them first.
        //Since the expression will match the regex we can safely assume that
        //every time we find a '/' or a '*' the adjacents values are numbers
        //to divide/multiply.
        String firstStep = "";
        double temp = 0;
        for (int i = 0; i < splitExp.length; ++i) {

            if (splitExp[i].equals("/")) {
                temp = this.c.div(Double.parseDouble(splitExp[i - 1]), Double.parseDouble(splitExp[i + 1]));
                firstStep += temp;

            } else if (splitExp[i].equals("*")) {
                temp = this.c.mult(Double.parseDouble(splitExp[i - 1]), Double.parseDouble(splitExp[i + 1]));
                firstStep += Double.toString(temp);
                
            } else if(i < (splitExp.length - 1) && (!(splitExp[i +1].equals("*") || splitExp[i + 1].equals("*")))){
                firstStep += splitExp[i];
            }

        }
        splitExp = this.expStringToArray(firstStep);
        
        //Now we have an expression of digits followed by operators only.
        //It is correct to say that each even element number is an operator
        for (int i = 0; i < splitExp.length; ++i) {
            if(i ==0){
                temp = Double.parseDouble(splitExp[0]);
            }else if(i % 2 != 0){
                
                if(splitExp[i].equals("+")){
                    temp = this.c.sum(temp, Double.parseDouble(splitExp[i + 1]));
                }else if(splitExp[i].equals("-")){
                    temp = this.c.sub(temp, Double.parseDouble(splitExp[i + 1]));
            }
            }
                
        }

//        String numberRegex = "(-?[1-9](([0-9]){1,9})?)";
//        String operatorRegex = "";
//        String expRegex = "(" + numberRegex + "?\\*|/|-|\\+" + numberRegex + "){1,10}";
//        String [] operator = {"+", "-", "/", "*"};
//        
//        String test = "";
//        for(String str: s){
//            test += str;
//        }
//        System.out.println(test + " " + test.matches(expRegex));
//        
//        if(s[0].matches(numberRegex)){
//            this.c.setFirstOperand(Double.parseDouble(s[0]));
//        }else {
//            this.c.setFirstOperand(0);
//        }
//        
//        for(int i = 1; i < s.length; ++i){
//            
//            this.c.setSecondOperand(0);
//            
//            
//            
//            //Fix with something better
//            if(operator[0].equals(s[i]) || operator[1].equals(s[i]) || operator[2].equals(s[i]) || operator[3].equals(s[i])){
//                
//                switch (s[i]) {
//                    case "+":
//                        this.c.setSecondOperand(Double.parseDouble(s[++i]));
//                        this.c.sum();
//                        break;
//                    case "-":
//                        this.c.setSecondOperand(Double.parseDouble(s[++i]));
//                        this.c.sub();
//                        break;
//                    case "*":
//                        this.c.setSecondOperand(Double.parseDouble(s[++i]));
//                        this.c.mult();
//                        break;
//                    case "/":
//                        this.c.setSecondOperand(Double.parseDouble(s[++i]));
//                        this.c.div();
//                        break;
//                }
//            }
//        }
    }

    private String[] expStringToArray(String exp) {

        String[] splitExp = new String[exp.length()];
        Arrays.fill(splitExp, "");
        int j = 0;
        for (int i = 0; i < exp.length(); ++i) {
            if (exp.charAt(i) == '*' || exp.charAt(i) == '+'
                    || exp.charAt(i) == '/' || exp.charAt(i) == '-') {

                splitExp[++j] += exp.charAt(i);
                ++j;
            } else {
                splitExp[j] += exp.charAt(i);
            }

        }

        ArrayList<String> utilityArray = new ArrayList<>(Arrays.asList(splitExp));
        Predicate <String> p = (s)-> s.equals("");
        utilityArray.removeIf(p);
        splitExp = new String [0];
        splitExp = utilityArray.toArray(splitExp);
        return splitExp;
    }
}
