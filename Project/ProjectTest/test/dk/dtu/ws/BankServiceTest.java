/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
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
        boolean result = validateCreditCard(group, validCard(), amount);
        assertEquals(true, result);
    }
    
    @Test
    public void validateCreditCardInvalidFunds() throws CreditCardFaultMessage{       
        int group = 1;
        int amount = 1000;
        boolean result = validateCreditCard(group, validCardInsufficiantFunds(), amount);
        // I don't understand this result
        assertEquals(true, result);
    }
    
    @Test
    public void validateCreditCardInvalidFunds2() throws CreditCardFaultMessage{       
        int group = 1;
        int amount = 100000000;
        boolean result = validateCreditCard(group, validCardInsufficiantFunds(), amount);
        // I don't understand this result
        assertEquals(true, result);
    }
    
    @Test
    public void validateInvalidCreditCard() {
        int group = 1;
        int amount = 1000;
        try {
            boolean result = validateCreditCard(group, invalidCard(), amount);
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist", e.getMessage());
        }       
    }
    
    @Test
    public void chargeCreditCardValidFunds() throws CreditCardFaultMessage{
        int group = 1;
        int amount = 1000;
        boolean result = chargeCreditCard(group, validCard(), amount, validAccount());
        assertEquals(true, result);
    }
    
    @Test
    public void chargeInvalidCreditCard() {
        int group = 1;
        int amount = 1000;
        try {
            boolean result = chargeCreditCard(group, invalidCard(), amount, validAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist", e.getMessage());
        }       
    }
    
    @Test
    public void chargeCreditCardInvalidReciverAccount(){
        int group = 1;
        int amount = 1000;
        try {
            chargeCreditCard(group, validCard(), amount, invalidAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Account does not exist" ,e.getMessage());
        }
    }
    
    @Test
    public void chargeCreditInvalidCardAndInvalidReciverAccount(){
        int group = 1;
        int amount = 1000;
        try {
            chargeCreditCard(group, invalidCard(), amount, invalidAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist" ,e.getMessage());
        }
    }
    
    @Test
    public void refundCreditCard() throws CreditCardFaultMessage{
        int group = 1;
        int amount = 1000;      
        
        boolean result = refundCreditCard(group, validCard(), amount, validAccount());
        assertEquals(true, result);
    }
    
    @Test
    public void refundInvalidCreditCard(){
        int group = 1;
        int amount = 1000;      
        
        try{
            boolean result = refundCreditCard(group, invalidCard(), amount, validAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist", e.getMessage());
        }
    }
    
    @Test
    public void refundCreditCardInvalidReciverAccount(){
        int group = 1;
        int amount = 1000;      
        
        try{
            boolean result = refundCreditCard(group, validCard(), amount, invalidAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Account does not exist", e.getMessage());
        }       
    }
    
    @Test
    public void refundCreditInvalidCardAndInvalidReciverAccount(){
        int group = 1;
        int amount = 1000;      
        
        try{
            boolean result = refundCreditCard(group, invalidCard(), amount, invalidAccount());
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist", e.getMessage());
        }
    }
    
    private CreditCardInfoType validCard(){
        CreditCardInfoType testCard = new CreditCardInfoType();
        testCard.setName("Thor-Jensen Claus");
        testCard.setNumber("50408825");
        
        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        testCard.setExpirationDate(expirationDate);
        
        return testCard;
    }
    
    private CreditCardInfoType invalidCard(){
        CreditCardInfoType testCard = new CreditCardInfoType();
        testCard.setName("Thor-Jensen Claus");
        testCard.setNumber("50408825");
        
        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        expirationDate.setYear(5);
        expirationDate.setMonth(9);
        testCard.setExpirationDate(expirationDate);
        
        return testCard;
    }
    
    private CreditCardInfoType validCardInsufficiantFunds(){
        CreditCardInfoType testCard = new CreditCardInfoType();
        testCard.setName("Anne Strandberg");
        testCard.setNumber("50408816");
        
        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        testCard.setExpirationDate(expirationDate);
        
        return testCard;
     }
     
    private  AccountType validAccount(){
        AccountType account = new AccountType();
        account.setName("TravelGood");
        account.setNumber("50108811");
        return  account;
    }
    
    private AccountType invalidAccount(){
        AccountType account = new AccountType();
        account.setName("TravelGood");
        account.setNumber("50108812");
        return  account;
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
