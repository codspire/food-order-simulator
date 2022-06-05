package com.codspire.simulators.food.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

/**
 * Reference: https://www.springboottutorial.com/spring-boot-and-aop-with-spring-boot-starter-aop
 */
@Aspect
@Configuration
@Slf4j
public class MethodExecutionCalculationAspect {

    @Around("@annotation(com.codspire.simulators.food.utils.TrackTime)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("##################### inside MethodExecutionCalculationAspect()");
//        long startTime = System.currentTimeMillis();
        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        log.info("Time Taken by {} is {}ms", joinPoint, stopWatch.getTotalTimeMillis());
        return result;
    }
}