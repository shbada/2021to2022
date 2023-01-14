package com.designpattern.report._06_adapter.step2_after.adapter;

import com.designpattern.report._06_adapter.step2_after.Account;
import com.designpattern.report._06_adapter.step2_after.target.security.UserDetails;

/**
 * Adapter
 */
public class AccountUserDetails implements UserDetails {

    private Account account;

    public AccountUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public String getUsername() {
        return account.getName();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }
}
