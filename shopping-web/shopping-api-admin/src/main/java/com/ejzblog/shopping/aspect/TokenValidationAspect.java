package com.ejzblog.shopping.aspect;

import com.alibaba.fastjson.JSON;
import com.ejzblog.shopping.annotation.TokenValidation;
import com.ejzblog.shopping.exceptionhandler.DataException;
import com.ejzblog.shopping.handler.AccountContext;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.ejzblog.shopping.service.JwtService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

import static com.ejzblog.shopping.enums.ResponseStatusEnum.TOKEN_NULL;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.TOKEN_PARSING_ERROR;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-03-29 14:44
 * @see com.ejzblog.pet.aspect
 */
@SuppressWarnings("ALL")
@Aspect
@Component
public class TokenValidationAspect implements Ordered {

    private static final Logger log = LoggerFactory.getLogger(TokenValidationAspect.class);

    /**
     * jwt 服务
     */
    @Autowired
    private JwtService jwtService;

    @Pointcut("@annotation(com.ejzblog.shopping.annotation.TokenValidation)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) {
        try {
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            log.info("aspect method: {} - {}", methodSignature.getDeclaringTypeName(), methodSignature.getName());
            TokenValidation processor = getAnnotation(methodSignature);
            if (processor != null && StringUtils.isNotBlank(processor.value()) && processor.validationType()) {
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
                String token = request.getHeader(processor.value());
                log.info("token: {}", token);
                if (Objects.isNull(token)) {
                    throw new DataException(TOKEN_NULL.getMessage(), TOKEN_NULL.getMessage());
                }
                AdminAccountDTO dto = jwtService.parseToken(token);
                log.info("user: {}", JSON.toJSON(dto.toString()));
                if (Objects.isNull(dto)) {
                    throw new DataException(TOKEN_PARSING_ERROR.getMessage(), TOKEN_PARSING_ERROR.getMessage());
                }
                AccountContext.setUser(dto);
            }
            return pjp.proceed();
        } catch (Throwable throwable) {
            log.error("aroundMethod exception", throwable);
            throw new DataException(throwable.getMessage(), throwable.getMessage());
        } finally {
            AccountContext.clear();
        }
    }

    private static TokenValidation getAnnotation(MethodSignature methodSignature) {
        Class<?> clazz = methodSignature.getDeclaringType();
        Method method = methodSignature.getMethod();
        TokenValidation annotation = (TokenValidation) method.getAnnotation(TokenValidation.class);
        if (annotation == null) {
            annotation = (TokenValidation) clazz.getAnnotation(TokenValidation.class);
        }
        return annotation;
    }

    @Override
    public int getOrder() {
        return -200;
    }

}
