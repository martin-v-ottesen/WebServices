package dk.dtu.ws;

import dk.dtu.imm.fastmoney.types.AccountType;
/**
 *
 * @author Martin
 */
public class BankAccount {
        static final AccountType validAccount() {
        AccountType account = new AccountType();
        account.setName("NiceView");
        account.setNumber("50308815");
        return account;
    }
    
}
