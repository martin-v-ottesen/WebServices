package dk.dtu.ws;

@javax.jws.WebService
public class Calculator {    
    public int plus(int a, int b){
        return a + b;        
    }
    
    public int minus(int a, int b){
        return a - b;        
    }
    
    public int mult(int a, int b){
        return a * b;        
    }
    
    public int div(int a, int b){
        return a / b;        
    }    
}
