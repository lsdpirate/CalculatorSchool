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
public class Calc {

    protected double o1, o2, r;

    public Calc() {
        o1 = o2 = r = 0;
    }

    public void setFirstOperand(double d) {
        this.o1 = d;
    }

    public void setSecondOperand(double d) {
        this.o2 = d;
    }

    public double getFirstOperand() {
        return this.o1;
    }

    public double getSecondOperand() {
        return this.o2;
    }

    public double getResult() {
        return this.r;
    }

    public double sum() {
        this.r = o1 + o2;
        o1 = r;
        
        return r;
    }

    public double sub() {
        this.r = o1 - o2;
        o1 = r;
        return r;
    }

    public double mult() {
        this.r = o1 * o2;
        o1 = r;
        return r;
    }

    public double div() {
        this.r = o1 / o2;
        o1 = r;
        return r;
    }

    public double sum(double a, double b) {
        this.setFirstOperand(a);
        this.setSecondOperand(b);
        return this.sum();

    }

    public double sub(double a, double b) {
        this.setFirstOperand(a);
        this.setSecondOperand(b);
        return this.sub();

    }

    public double mult(double a, double b) {
        this.setFirstOperand(a);
        this.setSecondOperand(b);
        return this.mult();
    }

    public double div(double a, double b) {
        this.setFirstOperand(a);
        this.setSecondOperand(b);
        return this.div();

    }
}
