/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice.service;

import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;

/**
 *
 * @author dxong
 */
public class Bank {
    private final static int group = 2; //Our group number
    
    private final static String accountNumber = "50208812";
    private final static String accountName = "LameDuck";
    
    public boolean chargeCreditCard(String creditCardName, String creditCardNumber, int creditCardYear, int creditCardMonth, int amount) throws CreditCardFaultMessage {
        AccountType account = new AccountType();
        CreditCardInfoType creditCardInfo = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        
        
        account.setName(Bank.accountName);
        account.setNumber(Bank.accountNumber);
        
        creditCardInfo.setName(creditCardName);
        creditCardInfo.setNumber(creditCardNumber);
        
        expirationDate.setYear(creditCardYear);
        expirationDate.setMonth(creditCardMonth);
        creditCardInfo.setExpirationDate(expirationDate);
        
        return fastmoney_chargeCreditCard(group, creditCardInfo, amount, account);
    }
    
    public boolean refundCreditCard(CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        AccountType account = new AccountType();
        
        account.setName(Bank.accountName);
        account.setNumber(Bank.accountNumber);
        
        return fastmoney_refundCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean fastmoney_chargeCreditCard(int group, 
            dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, 
            int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean fastmoney_refundCreditCard(int group, 
            dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, 
            int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }
    
}
