/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorscuola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 *
 * @author lsdpirate
 */
public class OldCalculator {

    private ScientificCalc c;

    public OldCalculator() {
        this.c = new ScientificCalc();
    }

    public double Calculate(String s) throws ArithmeticException{
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
     * @deprecated @param exp
     */
    private void parseCalculus(String exp) throws ArithmeticException {

        String numberRegex = "((-)?(\\d*)(\\.?)(\\d*))";
        String operatorRegex = "((\\+)|(-)|((\\*))|((/)))";
        
        String sqrtRegex = "(sqrt\\(" + numberRegex + "\\))";
        String operandRegex = "(" + numberRegex + "|" + sqrtRegex + ")";
        String regex = "(" + operandRegex + operatorRegex + operandRegex + ")*";
        //First of all we will have to normalize the string to a non spaceless
        //expression otherwise regex check could fail. This will give the user
        //freedom to write the exp as he wishes.
        exp = exp.replaceAll(" ", "");

        //Then we assure that exp matches the expression format.
        System.out.println(exp.matches(regex));
        System.out.println(exp.matches(sqrtRegex));

        if (!exp.matches(regex)) {
            return;
        }

        //After that we split all the expression elements into an array
        //for better management.
        String[] splitExp = this.expStringToArray(exp);
        
        //Now we calculate preliminar variables like sqrt() or sin()
        for (int i = 0; i < splitExp.length; i++) {
            
            if(splitExp[i].matches(sqrtRegex)){
                
                int f = splitExp[i].indexOf('(');
                int s = splitExp[i].indexOf(')');
                String n = splitExp[i].substring(f + 1, s);
                double sqrt = Double.parseDouble(n);
                sqrt = this.c.sqrt(sqrt);
                splitExp[i] = Double.toString(sqrt);
            }
        }
        //Now we have to look for all the divisions
        //and multiplications to compute them first.
        //Since the expression will match the regex we can safely assume that
        //every time we find a '/' or a '*' the adjacents values are numbers
        //to divide/multiply.
        //Since we cannot know in advance the length of the expression after the
        //multiplications/divisions (without a first iteration of the expression)
        //we initialize the array at the string length. This is not optimal
        //but saves compute time.
        String[] firstStep = new String[exp.length()];

        double temp = 0;
        int j = 0;

        for (int i = 0; i < splitExp.length; ++i) {

            if (splitExp[i].equals("/")) {
                temp = this.c.div(Double.parseDouble(firstStep[j - 1]), Double.parseDouble(splitExp[i + 1]));

                ++i;
                firstStep[j - 1] = Double.toString(temp);

            } else if (splitExp[i].equals("*")) {

                temp = this.c.mult(Double.parseDouble(firstStep[j - 1]), Double.parseDouble(splitExp[i + 1]));
                firstStep[j - 1] = Double.toString(temp);
                ++i;

            } else {
                firstStep[j] = splitExp[i];
                ++j;
            }

        }

        //Necessary to remove unused array slots. Since the initialization size
        //was unknown this operation is necessary to avoid screwing up the next
        //iteration.
        splitExp = OldCalculator.cleanArray(firstStep);

        //Now we have an expression of digits followed by sums/subs only.
        //It is correct to say that each odd element index is an operator.
        for (int i = 0; i < splitExp.length; ++i) {
            if (i == 0) {
                temp = Double.parseDouble(splitExp[0]);
            } else {
                temp = this.c.sum(temp, Double.parseDouble(splitExp[i]));
            }

        }

    }

    private String[] expStringToArray(String exp) {

        String[] splitExp = new String[exp.length()];
        Arrays.fill(splitExp, "");
        boolean parsingNumber = true;
        int j = 0;
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
//            if (exp.charAt(i) == '*' || exp.charAt(i) == '/') {
//                parsingNumber = false;
//                splitExp[++j] += exp.charAt(i);
//
//            } else if (exp.charAt(i) == '+' || exp.charAt(i) == '-') {
//                parsingNumber = false;
//                splitExp[++j] += exp.charAt(i);
//            } else {
//                if (!parsingNumber) {
//                    ++j;
//                    parsingNumber = true;
//                }
//                splitExp[j] += exp.charAt(i);
//
//            }
            if (c == '+' || c == '-') {
                splitExp[++j] += c;
            } else if (c == '*' || c == '/') {
                splitExp[++j] += c;
                ++j;
            } else {
                splitExp[j] += c;
            }

        }

        ArrayList<String> utilityArray = new ArrayList<>(Arrays.asList(splitExp));
        Predicate<String> p = (s) -> s.equals("");
        utilityArray.removeIf(p);
        splitExp = new String[0];
        splitExp = utilityArray.toArray(splitExp);
        return splitExp;
    }

    private static String[] cleanArray(String[] ar) {
        ArrayList<String> utilityArray = new ArrayList<>();

        for (int i = 0; i < ar.length; ++i) {
            if (ar[i] != null) {
                utilityArray.add(ar[i]);
            }

        }
        String[] result = new String[0];
        result = utilityArray.toArray(result);
        return result;
    }
}
