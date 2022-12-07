package io.security.corespringsecurity.security.init;

import io.security.corespringsecurity.service.RoleHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;

/**
 * 스프링 부트가 기동될때 수행
 */
@Component
@RequiredArgsConstructor
public class SecurityInitializer implements ApplicationRunner {
    private final RoleHierarchyService roleHierarchyService;
    private final RoleHierarchyImpl roleHierarchy;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String allHierarchy = roleHierarchyService.findAllHierarchy();
        roleHierarchy.setHierarchy(allHierarchy);
    }
}
