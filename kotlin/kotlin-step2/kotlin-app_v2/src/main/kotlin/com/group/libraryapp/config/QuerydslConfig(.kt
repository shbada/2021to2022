package com.group.libraryapp.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager

@Configuration
class QuerydslConfig(
    private val em: EntityManager,
) {
    /**
     * JPAQueryFactory 를 스프링 Bean으로 등록
     */
    @Bean
    fun querydsl(): JPAQueryFactory {
        return JPAQueryFactory(em)
    }
}