package com.oasis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * for BaseTimeEntity
 * @EnableJpaAuditing을 사용하기 위해서는 최소 하나의 엔티티가 필요합니다.
 * 하지만 @WebMvcTest를 하면 Entity를 찾을 수 없어 에러가 발생하게 됩니다.
 * 이때는 따로 @Configuration이 붙은 클래스에 분리시켜주면 해결할 수 있습니다.
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

}
