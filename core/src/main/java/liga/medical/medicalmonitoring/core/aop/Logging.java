package liga.medical.medicalmonitoring.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class Logging {
    @Pointcut(value = "execution(* liga.medical.medicalmonitoring.core.service.*.*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object dbLogging(ProceedingJoinPoint pjp) {
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        Object o = null;

        try {
            o = pjp.proceed();
        } catch (Throwable throwable) {
            log.error("Class: " + className + " method: " + methodName + " args: " + Arrays.toString(args));
        }

        log.info("Class: " + className + " method: " + methodName + " args: " + Arrays.toString(args));
        return o;
    }
}
