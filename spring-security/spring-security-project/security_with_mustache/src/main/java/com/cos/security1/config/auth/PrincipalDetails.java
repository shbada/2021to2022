package com.cos.security1.config.auth;

import com.cos.security1.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 시큐리티가 /loginProc 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
 * 이때, 로그인 진행이 완료가 되면 시큐리티 session 을 만들어준다.
 * 시큐리티가 세션을 가지고있다. 시큐리티가 자신만의 세션 공간을 가진다 (Security ContextHolder)
 * 세션에 들어갈 수 있는 정보는 오브젝트 로 정해져있다. (Authentication 객체)
 * 이 Authentication 객체 안에는 User 정보가 있어야한다.
 * 이 User 오브젝트 타입 => UserDetails 타입
 *
 * 시큐리티가 가지고있는 세션 영역 (Security session) -> Authentication (=UserDetails type)
 * implements UserDetails
 *
 * PrincipalDetails 가 UserDetails 타입이 된다. 이제 이를 Authentication 로 넣을 수 있게됬다.
 *
 */
// Authentication 객체에 저장할 수 있는 유일한 타입
@Data
public class PrincipalDetails implements UserDetails {

    /* 우리의 유저 정보 (컴포지션) */
    private User user;

    /* 생성자 */
    public PrincipalDetails(User user) {
        super();
        this.user = user;
    }

    /**
     * 유저의 권한을 리턴하는 곳
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();

        /* string 타입을 리턴할 수 없어, Collection 에 담아서 리턴한다. */
        collect.add(() -> {return user.getRole();});
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 휴먼 계정에 대한 정책이 있다면 구현 가능
     * 체크해서 return false 해주면 로그인 불가 계정이 된다.
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
