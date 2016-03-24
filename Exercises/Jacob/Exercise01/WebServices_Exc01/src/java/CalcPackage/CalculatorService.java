/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalcPackage;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import calculatorExceptionPkg.DivideByZeroException;

/**
 *
 * @author jacobmulvad
 */
@WebService(serviceName = "CalculatorService")
public class CalculatorService {

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "add")
    public String add(int a, int b) {
        int result = a+b;
        
        return Integer.toString(result);
    }
    
    @WebMethod(operationName = "subtract")
    public String subtract(int a, int b){
        int result = a-b;
        
        return Integer.toString(result);
    }
    
    @WebMethod(operationName = "multiply")
    public String multiply(int a, int b){
        int result = a*b;
        
        return Integer.toString(result);
    }
    
    @WebMethod(operationName = "divide")
    public String divide(int a, int b){
        int result = 0;
        if(b == 0) {
            throw new ArithmeticException("Divide by zero exception");
        } else {
            result = a/b;
        }
        
        return Integer.toString(result);
    }
    
    @WebMethod(operationName = "divide2")
    public String divide2(int a, int b) throws DivideByZeroException {
        int result = 0;
        if (b == 0) {
            Throwable t = new ArithmeticException("Divide by zero Exception!");
            throw new DivideByZeroException(t.getMessage(), t);
        }
        else {
            result = a/b;
        }
        return Integer.toString(result);
    }
}