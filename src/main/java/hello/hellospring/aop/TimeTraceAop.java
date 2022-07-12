package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP는 공통으로 사용하는 로직을 비지니스 로직에 적용을 쉽게 하는 기능이다.
// 예를 들어 시간 측정 로직은 전체 비지니스 로직에 적용해야 하는데 aop를 사용하면 각각의 비지니스 로직에 다 적용하지 않고 이 로직에만 적용하면 다 적용이 되어서 편하다.
@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try{
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs+ "ms");
        }
    }
}
