package io.security.corespringsecurity.service.impl;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.repository.UserRepository;
import io.security.corespringsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(Account account){

        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setUserRoles(roles);
        userRepository.save(account);
    }

    @Transactional
    @Override
    public void modifyUser(AccountDto accountDto){

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);

        if (accountDto.getRoles() != null) {
            Set<Role> roles = new HashSet<>();

            accountDto.getRoles().forEach(role -> {
                Role r = roleRepository.findByRoleName(role);
                roles.add(r);
            });

            account.setUserRoles(roles);
        }

        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        userRepository.save(account);

    }

    @Transactional
    public AccountDto getUser(Long id) {

        Account account = userRepository.findById(id).orElse(new Account());
        ModelMapper modelMapper = new ModelMapper();
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);

        List<String> roles = account.getUserRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());

        accountDto.setRoles(roles);
        return accountDto;
    }

    @Transactional
    public List<Account> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /*
    [서버를 키면, 초기화과정 발생]
    MethodSecurityMetadataSourceAdvisor.java
    MethodSecurityMetadataSourcePointcut : matches()
    -> class, method 정보를 전달받는다.

    DelegatingMethodSecurityMetadataSource.java
    > 여기서 어노테이션이 있는 아래 order()을 찾았다. 캐시 map에 담는다.

    MethodSecurityInterceptor.java
    > order() 접근하기 전에 인가처리를 해야하는데, 이 인가처리를 할 수 있는 클래스가 (Advise) MethodSecurityInterceptor 객체다.
    > invoke()

    [아래 메서드 접근시]
    AbstractSecurityInterceptor.java
    > UserController 접속까진 가능
    UserController에서 호출하는 userService 가 프록시 객체다.

    AbstractMethodSecurityMetadataSource.java
    DelegatingMethodSecurityMetadataSource.java

    AbstractSecurityInterceptor.java
    성공시 invoke() -> proceed()
     */
    @Override
    @Secured("ROLE_MANAGER") /* 권한의 인가처리 필수 */
    public void order() {
        System.out.println("order");
    }
}