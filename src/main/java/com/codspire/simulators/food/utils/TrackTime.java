package com.codspire.simulators.food.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Reference: https://www.springboottutorial.com/spring-boot-and-aop-with-spring-boot-starter-aop
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackTime {
}

