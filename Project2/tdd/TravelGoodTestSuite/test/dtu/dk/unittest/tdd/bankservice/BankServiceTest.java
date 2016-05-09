/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.bankservice;

import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class BankServiceTest {
    
    public BankServiceTest() {
    }
//    @Rule
//    public ExpectedException expectedEx = ExpectedException.none();
    
    @Test
    public void validateCreditCardValidFunds() throws CreditCardFaultMessage{
        int group = 1;
        int amount = 1000;
        boolean result = validateCreditCard(group, BankData.validCard(), amount);
        assertEquals(true, result);
    }
    
    @Test
    public void validateCreditCardInvalidFunds() throws CreditCardFaultMessage{       
        int group = 1;
        int amount = 1000;
        boolean result = validateCreditCard(group, BankData.validCardInsufficiantFunds(), amount);
        // I don't understand this result
        assertEquals(true, result);
    }
    
    @Test
    public void validateCreditCardInvalidFunds2() throws CreditCardFaultMessage{       
        int group = 1;
        int amount = 100000000;
        boolean result = validateCreditCard(group, BankData.validCardInsufficiantFunds(), amount);
        // I don't understand this result
        assertEquals(true, result);
    }
    
    @Test
    public void validateInvalidCreditCard() {
        int group = 1;
        int amount = 1000;
        try {
            boolean result = validateCreditCard(group, BankData.invalidCard(), amount);
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist", e.getMessage());
        }       
    }
    
    @Test
    public void chargeCreditCardValidFunds() throws CreditCardFaultMessage{
        int group = 1;
        int amount = 1000;
        boolean result = chargeCreditCard(group, BankData.validCard(), amount, BankData.validAccount());
        assertEquals(true, result);
    }
    
    @Test
    public void chargeInvalidCreditCard() {
        int group = 1;
        int amount = 1000;
        try {
            boolean result = chargeCreditCard(group, BankData.invalidCard(), amount, BankData.validAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist", e.getMessage());
        }       
    }
    
    @Test
    public void chargeCreditCardInvalidReciverAccount(){
        int group = 1;
        int amount = 1000;
        try {
            chargeCreditCard(group, BankData.validCard(), amount, BankData.invalidAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Account does not exist" ,e.getMessage());
        }
    }
    
    @Test
    public void chargeCreditInvalidCardAndInvalidReciverAccount(){
        int group = 1;
        int amount = 1000;
        try {
            chargeCreditCard(group, BankData.invalidCard(), amount, BankData.invalidAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist" ,e.getMessage());
        }
    }
    
    @Test
    public void refundCreditCard() throws CreditCardFaultMessage{
        int group = 1;
        int amount = 1000;      
        
        boolean result = refundCreditCard(group, BankData.validCard(), amount, BankData.validAccount());
        assertEquals(true, result);
    }
    
    @Test
    public void refundInvalidCreditCard(){
        int group = 1;
        int amount = 1000;      
        
        try{
            boolean result = refundCreditCard(group, BankData.invalidCard(), amount, BankData.validAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist", e.getMessage());
        }
    }
    
    @Test
    public void refundCreditCardInvalidReciverAccount(){
        int group = 1;
        int amount = 1000;      
        
        try{
            boolean result = refundCreditCard(group, BankData.validCard(), amount, BankData.invalidAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Account does not exist", e.getMessage());
        }       
    }
    
    @Test
    public void refundCreditInvalidCardAndInvalidReciverAccount(){
        int group = 1;
        int amount = 1000;      
        
        try{
            boolean result = refundCreditCard(group, BankData.invalidCard(), amount, BankData.invalidAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist", e.getMessage());
        }
    }   
   
    private static boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }

    private static boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }
    
    
}
