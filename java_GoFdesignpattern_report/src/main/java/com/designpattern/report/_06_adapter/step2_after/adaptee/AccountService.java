package com.designpattern.report._06_adapter.step2_after.adaptee;

import com.designpattern.report._06_adapter.step2_after.Account;

/**
 * Adaptee
 */
public class AccountService {

    public Account findAccountByUsername(String username) {
        Account account = new Account();
        account.setName(username);
        account.setPassword(username);
        account.setEmail(username);
        return account;
    }

    public void createNewAccount(Account account) {

    }

    public void updateAccount(Account account) {

    }

}
