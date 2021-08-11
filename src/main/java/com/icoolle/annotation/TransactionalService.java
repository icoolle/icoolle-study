package com.icoolle.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * service 联合注解
 * @author
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Transactional(rollbackFor = RuntimeException.class)
@Service
public @interface TransactionalService {

    @AliasFor(annotation = Service.class)
    String value() default "";

    @AliasFor(annotation = Transactional.class)
    Class<? extends Throwable>[] rollbackFor() default Exception.class;

    @AliasFor(annotation = Transactional.class)
    Class<? extends Throwable>[] noRollbackFor() default {};
}
