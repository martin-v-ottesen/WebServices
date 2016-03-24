/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorExceptionPkg;

/**
 *
 * @author jacobmulvad
 */
public class DivideByZeroException extends Exception {

    public DivideByZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
