//package com.ejzblog.shopping.config.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ldap.core.support.BaseLdapPathContextSource;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.ldap.userdetails.PersonContextMapper;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
///**
// * <p>
// * Description：Security 配置
// * </p>
// *
// * @author Mango
// * @version v1.0.0
// * @date 2022-07-06 11:23
// * @see com.ejzblog.shopping.config.security
// */
//@SuppressWarnings("ALL")
//@Configuration
//public class SecurityConfiguration {
//
//    @Autowired
//    private AuthenticationConfiguration authenticationConfiguration;
//
////    /**
////     * 用户详情服务
////     *
////     * @return
////     */
////    @Bean
////    public UserDetailsService userDetailsService() {
////        return new UserDetailsServiceImpl();
////    }
//
//    @Bean
//    UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
//        users.createUser(User.withUsername("user").password("{noop}123").roles("admin").build());
//        users.createUser(User.withUsername("admin").password("{noop}123").roles("admin").build());
//        return users;
//    }
//
//    /**
//     * Security 密码策略
//     *
//     * @return {@link BCryptPasswordEncoder}
//     */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    // 权限不足错误信息处理，包含认证错误与鉴权错误处理
////    @Autowired
////    private JwtAuthError jwtAuthError;
////
////    // jwt 校验过滤器，从 http 头部 Authorization 字段读取 token 并校验
////    @Bean
////    public JwtAuthFilter authFilter() throws Exception {
////        return new JwtAuthFilter();
////    }
////
////    @Bean
////    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        return http
////                // 基于 token，不需要 csrf
////                .csrf().disable()
////                // 基于 token，不需要 session
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
////                // 设置 jwtAuthError 处理认证失败、鉴权失败
////                .exceptionHandling().authenticationEntryPoint(jwtAuthError).accessDeniedHandler(jwtAuthError).and()
////                // 下面开始设置权限
////                .authorizeRequests(authorize -> authorize
////                        // 请求放开
////                        .antMatchers("/**").permitAll()
////                        .antMatchers("/**").permitAll()
////                        // 其他地址的访问均需验证权限
////                        .anyRequest().authenticated()
////                )
////                // 添加 JWT 过滤器，JWT 过滤器在用户名密码认证过滤器之前
////                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
////                // 认证用户时用户信息加载配置，注入springAuthUserService
////                .userDetailsService(xxxAuthUserService)
////                .build();
////    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
////                .authorizeRequests(authorize -> authorize.antMatchers("/doc.html#/home").permitAll())
//                .httpBasic(withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    AuthenticationManager ldapAuthenticationManager(BaseLdapPathContextSource contextSource) {
//        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(contextSource);
//        factory.setUserDnPatterns("uid={0},ou=people");
//        factory.setUserDetailsContextMapper(new PersonContextMapper());
//        return factory.createAuthenticationManager();
//    }
//
//    /**
//     * 配置跨源访问(CORS)
//     *
//     * @return
//     */
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/doc.html#/home", "/images/**", "/js/**", "/webjars/**");
//    }
//
//}
