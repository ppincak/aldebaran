package com.aldebaran.chassis.monitoring;

import java.lang.annotation.*;


@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Progress {

    String name();

    double inc() default 1;

    String help() default "Default \"progress\" metric description.";
}