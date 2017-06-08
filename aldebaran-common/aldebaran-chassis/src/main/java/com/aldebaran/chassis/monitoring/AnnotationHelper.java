package com.aldebaran.chassis.monitoring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;


public class AnnotationHelper {

    public static <T extends Annotation> T getAnnotation(JoinPoint joinPoint, Class<T> annotation) {
        return getAnnotation(joinPoint.getSignature(), annotation);
    }

    public static <T extends Annotation> T getAnnotation(Signature signature, Class<T> annotation) {
        if(signature instanceof MethodSignature) {
            MethodSignature mSignature = (MethodSignature) signature;
            return mSignature.getMethod().getAnnotation(annotation);
        }
        throw new RuntimeException();
    }
}