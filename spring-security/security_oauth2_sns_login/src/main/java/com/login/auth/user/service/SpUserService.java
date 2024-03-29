package com.login.auth.user.service;

import com.login.auth.user.domain.SpAuthority;
import com.login.auth.user.domain.SpOauth2User;
import com.login.auth.user.domain.SpUser;
import com.login.auth.user.repository.SpOauth2UserRepository;
import com.login.auth.user.repository.SpUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SpUserService implements UserDetailsService {

    private final SpUserRepository userRepository;
    private final SpOauth2UserRepository oauth2UserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Optional<SpUser> findUser(String email) {
        return userRepository.findUserByEmail(email);
    }

    public SpUser save(SpUser user) {
        return userRepository.save(user);
    }

    public void addAuthority(Long userId, String authority) {
        userRepository.findById(userId).ifPresent(user -> {
            SpAuthority newRole = new SpAuthority(user.getUserId(), authority);

            if (user.getAuthorities() == null) {
                HashSet<SpAuthority> authorities = new HashSet<>();
                authorities.add(newRole);

                user.setAuthorities(authorities);

                save(user);
            } else if (!user.getAuthorities().contains(newRole)) {
                HashSet<SpAuthority> authorities = new HashSet<>();
                authorities.addAll(user.getAuthorities());
                authorities.add(newRole);

                user.setAuthorities(authorities);

                save(user);
            }
        });
    }

    public void removeAuthority(Long userId, String authority) {
        userRepository.findById(userId).ifPresent(user -> {
            if (user.getAuthorities() == null) {
                return;
            }

            SpAuthority targetRole = new SpAuthority(user.getUserId(), authority);

            if (user.getAuthorities().contains(targetRole)) {
                user.setAuthorities(
                        user.getAuthorities().stream()
                                .filter(auth -> !auth.equals(targetRole))
                                .collect(Collectors.toSet())
                );

                save(user);
            }
        });
    }

    /**
     * oauth2User 회원 정보를 찾는다.
     * 존재 -> 결과 return
     * 존재X -> 등록 후 return
     * @param oauth2User
     * @return
     */
    public SpUser loadUser(final SpOauth2User oauth2User) {
        SpOauth2User user = oauth2UserRepository.findById(oauth2User.getOauth2UserId())
                .orElseGet(() -> {
                    // 최초 등록
                    // 각 sns 를 통해 들어온 유저는 모두 다른 사용자라고 생각한다 (같은 사용자인지의 판별은 여러 정책에 따라 결정될 부분)
                    SpUser spUser = new SpUser();
                    spUser.setEmail(oauth2User.getEmail());
                    spUser.setEnabled(true);
                    spUser.setUsername(oauth2User.getName());
                    spUser.setPassword("");

                    userRepository.save(spUser);

                    addAuthority(spUser.getUserId(), "ROLE_USER");

                    oauth2User.setUserId(spUser.getUserId());
                    oauth2User.setCreated(LocalDateTime.now());

                    return oauth2UserRepository.save(oauth2User);
                });

        return userRepository.findById(user.getUserId()).get();
    }
}
