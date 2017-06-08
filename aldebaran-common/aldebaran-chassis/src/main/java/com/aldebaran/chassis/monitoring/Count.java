package com.aldebaran.chassis.monitoring;

import java.lang.annotation.*;


@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Count {

    String name();

    double incBy() default 1;

    String help() default "Default \"count\" metric description.";
}