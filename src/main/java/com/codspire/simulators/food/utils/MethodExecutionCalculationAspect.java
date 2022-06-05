package com.codspire.simulators.food.utils;

import com.google.common.math.StatsAccumulator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Reference: https://www.springboottutorial.com/spring-boot-and-aop-with-spring-boot-starter-aop
 */
@Aspect
@Configuration
@Slf4j
@EnableScheduling
public class MethodExecutionCalculationAspect {

    Map<String, StatsAccumulator> statsMap = new ConcurrentHashMap<>();

    @Around("@annotation(com.codspire.simulators.food.utils.TrackTime)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        String key = joinPoint.getSignature().toShortString();
        if (!statsMap.containsKey(key)) {
            statsMap.put(key, new StatsAccumulator());
        }
        statsMap.get(key).add(stopWatch.getTotalTimeMillis());

        log.info("Time Taken by {} is {}ms", joinPoint, stopWatch.getTotalTimeMillis());

        return result;
    }

    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedDelayTask() {
        log.info("============================== Performance Stats =============================================================");
        statsMap.entrySet().stream().forEach(e -> log.info("{} :: {}", e.getKey(), e.getValue().snapshot()));
        log.info("==============================================================================================================");
    }
}