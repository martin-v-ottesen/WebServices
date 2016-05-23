/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.integrationtest.travelgood.rest;

import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;

/**
 *
 * @author Martin
 */
public class BankData {

    public static final CreditCardInfoType validCard() {
        CreditCardInfoType testCard = new CreditCardInfoType();
        testCard.setName("Thor-Jensen Claus");
        testCard.setNumber("50408825");

        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        testCard.setExpirationDate(expirationDate);

        return testCard;
    }

    public static final CreditCardInfoType invalidCard() {
        CreditCardInfoType testCard = new CreditCardInfoType();
        testCard.setName("Thor-Jensen Claus");
        testCard.setNumber("50408825");

        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        expirationDate.setYear(5);
        expirationDate.setMonth(9);
        testCard.setExpirationDate(expirationDate);

        return testCard;
    }

    static final CreditCardInfoType validCardInsufficiantFunds() {
        CreditCardInfoType testCard = new CreditCardInfoType();
        testCard.setName("Anne Strandberg");
        testCard.setNumber("50408816");

        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        testCard.setExpirationDate(expirationDate);

        return testCard;
    }

    static final AccountType validAccount() {
        AccountType account = new AccountType();
        account.setName("TravelGood");
        account.setNumber("50108811");
        return account;
    }

    static final AccountType invalidAccount() {
        AccountType account = new AccountType();
        account.setName("TravelGood");
        account.setNumber("50108812");
        return account;
    }

}
