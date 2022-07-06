package com.ejzblog.shopping.config;

import com.ejzblog.shopping.aspect.TokenValidationAspectHandler;
import com.ejzblog.shopping.handler.AccountContext;
import com.ejzblog.shopping.service.JwtService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-03-29 14:44
 * @see com.ejzblog.shopping.config
 */
@Configuration
@ConditionalOnClass({AccountContext.class})
public class AccountAutoConfiguration {

    @Bean
    public JwtService tokenServiceBean() {
        return new JwtService();
    }

    @Bean
    public TokenValidationAspectHandler tokenValidationAspectHandler() {
        return new TokenValidationAspectHandler();
    }

}

