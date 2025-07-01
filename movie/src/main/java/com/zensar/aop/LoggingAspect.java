package com.zensar.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 🔹 Reusable pointcut expression
    // @Pointcut("execution(* com.zensar.service.*.*(..))") // This is only for com.zensar.service not for its sub packages
    // @Pointcut("execution(* com.example.service..*(..)) || execution(* com.example.repository..*(..))") // you can define multiple packages

	@Pointcut("execution(* com.zensar.service..*(..))") // This is for com.zensar.service and for its sub packages as well
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("[Before] Method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        System.out.println("[AfterReturning] Method: " + joinPoint.getSignature().getName() + " returned: " + result);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
        System.out.println("[AfterThrowing] Method: " + joinPoint.getSignature().getName() + " threw: " + ex);
    }

    @After("serviceMethods()")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("[After] Method: " + joinPoint.getSignature().getName());
    }

    @Around("serviceMethods()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[Around-Before] Method: " + joinPoint.getSignature().getName());
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("[Around-Exception] Exception in method: " + joinPoint.getSignature().getName());
            throw e;
        }
        System.out.println("[Around-After] Method: " + joinPoint.getSignature().getName());
        return result;
    }
}
