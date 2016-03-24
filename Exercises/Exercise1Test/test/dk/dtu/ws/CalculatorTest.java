/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import javax.xml.ws.soap.SOAPFaultException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class CalculatorTest {

    public CalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void plus() {
        int one = 2;
        int two = 3;
        int result = plus(one, two);
        assertEquals(one + two, result);
    }

    @Test
    public void minus() {
        int one = 2;
        int two = 3;
        int result = minus(one, two);
        assertEquals(one - two, result);
    }

    @Test
    public void mult() {
        int one = 2;
        int two = 3;
        int result = mult(one, two);
        assertEquals(one * two, result);
    }

    @Test
    public void divNonZero() throws DivideByZerroException_Exception {
        int one = 2;
        int two = 3;
        int result = div(one, two);
        assertEquals(one / two, result);
    }

    @Test//(expected = SOAPFaultException.class)
    public void divZero() {
        int zero = 0;
        int one = 3;
        try {
           div(one, zero);
        } catch (DivideByZerroException_Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            System.out.println(e.getSuppressed());
            System.out.println(e.toString());
            assertEquals("Argument 'divisor' is 0", e.getMessage());
        }
    }

    private static int plus(int arg0, int arg1) {
        dk.dtu.ws.Calculator_Service service = new dk.dtu.ws.Calculator_Service();
        dk.dtu.ws.Calculator port = service.getCalculatorPort();
        return port.plus(arg0, arg1);
    }

    private static int minus(int arg0, int arg1) {
        dk.dtu.ws.Calculator_Service service = new dk.dtu.ws.Calculator_Service();
        dk.dtu.ws.Calculator port = service.getCalculatorPort();
        return port.minus(arg0, arg1);
    }

    private static int mult(int arg0, int arg1) {
        dk.dtu.ws.Calculator_Service service = new dk.dtu.ws.Calculator_Service();
        dk.dtu.ws.Calculator port = service.getCalculatorPort();
        return port.mult(arg0, arg1);
    }

    private static int div(int arg0, int arg1) throws DivideByZerroException_Exception {
        dk.dtu.ws.Calculator_Service service = new dk.dtu.ws.Calculator_Service();
        dk.dtu.ws.Calculator port = service.getCalculatorPort();
        return port.div(arg0, arg1);
    }
 
}
