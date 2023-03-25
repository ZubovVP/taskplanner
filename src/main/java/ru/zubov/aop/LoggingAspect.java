package ru.zubov.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class LoggingAspect {

    //Аспект будет выполняться для всех методов из пакета контроллеров
    @Around("execution(* ru.zubov.controllers..*(..)))")
    public Object profileControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Получить информацию о том, какой клаасс и метод выполняется
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        log.info("----------Executing " + className + "." + methodName + "    -------------------");

        // выполняем сам метод и передаём
        return proceedingJoinPoint.proceed();
    }
}
