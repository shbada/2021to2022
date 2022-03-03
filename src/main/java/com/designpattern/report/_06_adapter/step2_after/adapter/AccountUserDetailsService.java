package com.designpattern.report._06_adapter.step2_after.adapter;

import com.designpattern.report._06_adapter.step2_after.adaptee.AccountService;
import com.designpattern.report._06_adapter.step2_after.target.security.UserDetails;
import com.designpattern.report._06_adapter.step2_after.target.security.UserDetailsService;

/**
 * Adapter
 */
public class AccountUserDetailsService implements UserDetailsService {

    private AccountService accountService;

    public AccountUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUser(String username) {
        return new AccountUserDetails(accountService.findAccountByUsername(username));
    }
}
