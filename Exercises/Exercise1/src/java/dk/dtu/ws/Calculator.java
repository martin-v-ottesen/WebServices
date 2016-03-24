package dk.dtu.ws;

import dk.dtu.ws.ex.DivideByZerroException;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "Calculator")
public class Calculator {

    @WebMethod(operationName = "plus")
    public int plus(int a, int b) {
        return a + b;
    }

    @WebMethod(operationName = "minus")
    public int minus(int a, int b) {
        return a - b;
    }

    @WebMethod(operationName = "mult")
    public int mult(int a, int b) {
        return a * b;
    }

    @WebMethod(operationName = "div")
    public int div(int a, int divisor) throws DivideByZerroException {
        if (divisor == 0) {
            throw new DivideByZerroException("Argument 'divisor' is 0", new IllegalAccessException("Divide by Zerro"));
        }
        return a / divisor;
    }
}
