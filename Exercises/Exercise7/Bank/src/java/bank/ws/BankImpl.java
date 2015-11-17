/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bank.ws;

import javax.jws.WebService;

/**
 *
 * @author hub
 */
@WebService(serviceName = "bankService", portName = "bankPortTypeBindingPort", endpointInterface = "ws.bank.BankPortType", targetNamespace = "http://bank.ws", wsdlLocation = "WEB-INF/wsdl/BankImpl/bank.wsdl")
public class BankImpl {

    public java.lang.String debitCreditCard(orderdata.CustomerType customer, orderdata.CreditcardType creditcard, double amount) {
        System.out.println(String.format("debitCeditCard(customer id = %s, creditcard number = %s, amount = %f)",customer.getName(),creditcard.getNumber(),amount));
        return "ok";
    }

    public java.lang.String refundCreditCard(orderdata.CustomerType customer, java.lang.String creditcard, double amount) {
        System.out.println(String.format("refundCeditCard(customer id = %s, creditcard number = %s, amount = %f)",customer.getName(),creditcard,amount));
        return "payment refunded";
    }

}
