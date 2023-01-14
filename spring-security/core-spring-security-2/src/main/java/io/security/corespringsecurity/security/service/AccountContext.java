package io.security.corespringsecurity.security.service;

import io.security.corespringsecurity.domain.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AccountContext extends User { // User : springSecurity 가 제공해줌 (UserDetails 구현)
    private final Account account;

    public AccountContext(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getUsername(), account.getPassword(), authorities);
        this.account = account; // 추후 참조를 위해 멤버변수로 하나 생성하자.
    }
}
