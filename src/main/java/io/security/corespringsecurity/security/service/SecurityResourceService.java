package io.security.corespringsecurity.security.service;

import io.security.corespringsecurity.domain.entity.Resources;
import io.security.corespringsecurity.repository.AccessIpRepository;
import io.security.corespringsecurity.repository.ResourcesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AppConfig 에서 빈으로 등록
 */
@RequiredArgsConstructor
@Service
public class SecurityResourceService {
    private final ResourcesRepository resourcesRepository;
    private final AccessIpRepository accessIpRepository;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> allResources = resourcesRepository.findAllResources();

        /*
            DB에서 모든 resource 정보를 가지고온다.
            1:N 형태로 담겨져있다. (allResources)

            resource_name : /admin/** 등
            role_name : ROLE_ADMIN, ROLE_MANAGER 등
         */
        allResources.forEach(re -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            re.getRoleSet().forEach(role -> {
                // DB에서 가지고온 Role 정보를 담는다.
                configAttributeList.add(new SecurityConfig(role.getRoleName()));
            });

            result.put(new AntPathRequestMatcher(re.getResourceName()), configAttributeList);
        });

        return result;
    }

    public List<String> getAccessIpList() {
        List<String> accessIpList = accessIpRepository.findAll()
                .stream()
                .map(accessIp -> accessIp.getIpAddress())
                .collect(Collectors.toList());
        return accessIpList;
    }
}
