/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatortest;

import calcpackage.DivideByZeroException_Exception;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.xml.ws.soap.SOAPFaultException;

/**
 *
 * @author jacobmulvad
 */
public class WebServiceCalculatorTest {
    
    
     @Test
     public void add_input3and5_returns8() {
         String result = add(3,5);
         assertEquals("8", result);
     }
     
     @Test
     public void add_inputMinus3andPlus5_returns2() {
         String result = add(-3,5);
         assertEquals("2", result);
     }
     
     @Test
     public void subtract_input5and3_returns2() {
         String result = subtract(5,3);
         assertEquals("2", result);
     }
     
     @Test
     public void multiply_input3and5_returns15() {
         String result = multiply(3,5);
         assertEquals("15", result);
     }
     
     @Test
     public void divide_divide20By5_returns4() {
         String result = divide(20,5);
         assertEquals("4", result);
     }
     
     @Test(expected = SOAPFaultException.class)
     public void divide_divideByZero_Fails() {
         String result = divide(20, 0);
         assertEquals("Divide by zero exception", result);
     }
     
     @Test
     public void divide2_divideByZero_Fails() {
         try{
             String result = divide2(20, 0);
         }
         catch(DivideByZeroException_Exception e) {
             assertEquals("Divide by zero Exception!", e.getMessage());
         }
     }
    
    @Test
    public void hello() {
        String result = hello("Jacob");
        assertEquals("Hello Jacob !", result);
    }

    private static String add(int arg0, int arg1) {
        calcpackage.CalculatorService_Service service = new calcpackage.CalculatorService_Service();
        calcpackage.CalculatorService port = service.getCalculatorServicePort();
        return port.add(arg0, arg1);
    }

    private static String divide(int arg0, int arg1) {
        calcpackage.CalculatorService_Service service = new calcpackage.CalculatorService_Service();
        calcpackage.CalculatorService port = service.getCalculatorServicePort();
        return port.divide(arg0, arg1);
    }

    private static String divide2(int arg0, int arg1) throws DivideByZeroException_Exception {
        calcpackage.CalculatorService_Service service = new calcpackage.CalculatorService_Service();
        calcpackage.CalculatorService port = service.getCalculatorServicePort();
        return port.divide2(arg0, arg1);
    }

    private static String hello(java.lang.String name) {
        calcpackage.CalculatorService_Service service = new calcpackage.CalculatorService_Service();
        calcpackage.CalculatorService port = service.getCalculatorServicePort();
        return port.hello(name);
    }

    private static String multiply(int arg0, int arg1) {
        calcpackage.CalculatorService_Service service = new calcpackage.CalculatorService_Service();
        calcpackage.CalculatorService port = service.getCalculatorServicePort();
        return port.multiply(arg0, arg1);
    }

    private static String subtract(int arg0, int arg1) {
        calcpackage.CalculatorService_Service service = new calcpackage.CalculatorService_Service();
        calcpackage.CalculatorService port = service.getCalculatorServicePort();
        return port.subtract(arg0, arg1);
    }
}
