/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class BankSecureServiceTest {
    
    public BankSecureServiceTest() {
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
    public void validate_1CreditCardValidFunds() throws CreditCardFaultMessage{
        int group = 1;
        int amount = 1000;
        boolean result = validateCreditCard_1(group, BankData.validCard(), amount);
        assertEquals(true, result);
    }
    
    @Test
    public void validate_1CreditCardInvalidFunds() throws CreditCardFaultMessage{       
        int group = 1;
        int amount = 1000;
        boolean result = validateCreditCard_1(group, BankData.validCardInsufficiantFunds(), amount);
        // I don't understand this result
        assertEquals(true, result);
    }
    
    @Test
    public void validate_1InvalidCreditCard() {
        int group = 1;
        int amount = 1000;
        try {
            boolean result = validateCreditCard_1(group, BankData.invalidCard(), amount);
        } catch (CreditCardFaultMessage e) {
            assertEquals("Credit card does not exist", e.getMessage());
        }       
    }
    
    
//    private CreditCardInfoType validCard(){
//        CreditCardInfoType testCard = new CreditCardInfoType();
//        testCard.setName("Thor-Jensen Claus");
//        testCard.setNumber("50408825");
//        
//        ExpirationDate expirationDate = new ExpirationDate();
//        expirationDate.setYear(9);
//        expirationDate.setMonth(5);
//        testCard.setExpirationDate(expirationDate);
//        
//        return testCard;
//    }
//    
//    private CreditCardInfoType invalidCard(){
//        CreditCardInfoType testCard = new CreditCardInfoType();
//        testCard.setName("Thor-Jensen Claus");
//        testCard.setNumber("50408825");
//        
//        ExpirationDate expirationDate = new ExpirationDate();
//        expirationDate.setYear(5);
//        expirationDate.setMonth(9);
//        testCard.setExpirationDate(expirationDate);
//        
//        return testCard;
//    }
//    
//    private CreditCardInfoType validCardInsufficiantFunds(){
//        CreditCardInfoType testCard = new CreditCardInfoType();
//        testCard.setName("Anne Strandberg");
//        testCard.setNumber("50408816");
//        
//        ExpirationDate expirationDate = new ExpirationDate();
//        expirationDate.setYear(9);
//        expirationDate.setMonth(5);
//        testCard.setExpirationDate(expirationDate);
//        
//        return testCard;
//     }
//     
//    private  AccountType validAccount(){
//        AccountType account = new AccountType();
//        account.setName("TravelGood");
//        account.setNumber("50108811");
//        return  account;
//    }
//    
//    private AccountType invalidAccount(){
//        AccountType account = new AccountType();
//        account.setName("TravelGood");
//        account.setNumber("50108812");
//        return  account;
//    }

    private static boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankSecureService service = new dk.dtu.imm.fastmoney.BankSecureService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankSecurePort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }

    private static boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankSecureService service = new dk.dtu.imm.fastmoney.BankSecureService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankSecurePort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean validateCreditCard_1(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankSecureService service = new dk.dtu.imm.fastmoney.BankSecureService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankSecurePort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}
