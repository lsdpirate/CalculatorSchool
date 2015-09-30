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
public class ScientificCalc extends Calc {

    public ScientificCalc() {

    }

    public double sqrt(){
        this.r = Math.sqrt(this.o1);
        return r;
    }
    public double sqrt(double o){
        super.setFirstOperand(o);
        return this.sqrt();
    }
    public double pow() {
        double r = 1;
        
        for(int i = 0; i < this.o2; ++i){
            r = r * this.o1;
            
        }
        super.r = r;
        return r;
    }
    public double pow(double o1, double o2){
        
        super.setFirstOperand(o1);
        super.setSecondOperand(o2);
        this.pow();
        return r;
    }
    
    public double sin(){
        
        r = Math.sin(Math.toRadians(this.o1));
        return this.r;
    }
    public double sin(double o1, double o2){
        this.o1 = o1;
        this.sin();
        return this.r;
    }
    
    public double cos(){
        r =  Math.cos(Math.toRadians(this.o1));
        
        return this.r;
    }
    public double cos(double o1, double o2){
        this.o1 = o1;
        this.cos();
        return this.r;
    }
    public double cos(double o1){
        return this.cos(o1, 0);
        
    }
    public double sin(double o1){
        return this.sin(o1, 0);
    }
    
}
