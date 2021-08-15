package com.SportyShoes.logging;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@Aspect
public class LoggerAspect {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "execution(* com.SportyShoes.controller.*.*(..))")
    public void ControllerPointcuts() {
        //empty method just to name the location in the Pointcut
    }

    @After("ControllerPointcuts()")
    public void ControllerlogAfter() {

        LOG.debug("------------------------------------------------------------");
        LOG.debug("Debugging from @Afte pointcut");
        LOG.debug("------------------------------------------------------------");

    }

    @Before("ControllerPointcuts()")
    public void ControllerlogBefore(JoinPoint jp) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

        int StartPracket = jp.getSignature().toLongString().indexOf("(");
        int EndPracket = jp.getSignature().toLongString().indexOf(")") + 1;

        String Args = jp.getSignature().toLongString().substring(StartPracket, EndPracket);

        LOG.debug("------------------------------------------------------------");
        LOG.debug("the calss full path: " + jp.getSignature().getDeclaringTypeName());
        LOG.debug("the method Name: " + jp.getSignature().getName());
        LOG.debug("the method Args: " + Args);
        LOG.debug("Debugging from @Before pointcut");
        LOG.debug("------------------------------------------------------------");

    }

}
