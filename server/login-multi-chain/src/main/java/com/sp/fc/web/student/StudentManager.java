package com.sp.fc.web.student;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 통행증 Student 를 발급할 AuthenticationProvider
 */
@Component
public class StudentManager implements AuthenticationProvider, InitializingBean {

    // DB 대체
    private HashMap<String, Student> studentDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        StudentAuthenticationToken token = (StudentAuthenticationToken) authentication;

        if(studentDB.containsKey(token.getCredentials())){
            Student student = studentDB.get(token.getCredentials());
            // token 만들어서 전달 (StudentAuthenticationToken)
            return StudentAuthenticationToken.builder()
                    .principal(student)
                    .details(student.getUsername()) // 원래는 details 에대한 객체를 담아야하는데..
                    .authenticated(true)
                    .authorities(student.getRole())
                    .build();
        }

        return null;
    }

    /**
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // return authentication == UsernamePasswordAuthenticationToken.class;
        return authentication == StudentAuthenticationToken.class;
    }

    public List<Student> myStudentList(String teacherId) {
        return studentDB.values().stream()
                .filter(s -> s.getTeacherId().equals(teacherId))
                .collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Student("hong", "홍길동", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")), "choi"),
                new Student("kang", "강아지", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")), "choi"),
                new Student("rang", "호랑이", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")), "choi")
        ).forEach(s->
                studentDB.put(s.getId(), s)
        );
    }
}
