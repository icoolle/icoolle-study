package com.icoolle.aspect;

import com.icoolle.tool.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static com.icoolle.tool.PageHelper.LOCAL_PAGE;


/**
 * 分页增强
 */
@Slf4j
@Aspect
@Component
public class PageHelperAspect {

    @Pointcut("execution(* com.icoolle.provider..*Mapper.*(com.baomidou.mybatisplus.extension.plugins.pagination.Page,..))")
    private void commonPackage() {
    }

    @Pointcut("execution(* com.icoolle.common.core.mapper.IcoolleMapper.*(com.baomidou.mybatisplus.extension.plugins.pagination.Page,..))")
    private void IcoolleMapper() {
    }

    @Around(value = "commonPackage() || IcoolleMapper()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        PageHelper.startPage(args[1]);
        args[0] = PageHelper.getLocalPage();
        LOCAL_PAGE.get().remove();
        try {
            return proceedingJoinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info(throwable.getMessage());
        }
        return null;
    }
}
