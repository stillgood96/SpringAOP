package com.example.aop.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // Aop로 동작하기 위해서 필요한 어노테이션
@Component // 당연히 스프링에서 관리되어지기 위한 Component 어노테이션
public class ParameterAop {

    // pointcut에 대한 수많은 경로 표현식(execution에 쓰이는 부분) 은 많으니 따로 인터넷으로 찾아보자.
    // 일단 이번 예제에서는 대표적으로 많이 쓰이는 부분을 사용
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") // controller 하위에 있는 모든 메서드에 적용시키는 표현식.
    private void cut() {

    }

    // cut() 메서드가 실행되는 이전 시점에 before()를 실행시키겠다.
    // JoinPoint라는 객체에 before에 매개변수로 받게되면
    // 들어가는 지점에 들어가는 정보가 전부 JoinPoint 객체에 담겨진다.
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        Object [] args = joinPoint.getArgs(); // 메서드에 들어가는 매개변수들을 받아오는 getArgs();

        for(Object obj : args) {
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj );
        }
    }



    // afterReturning에도 마찬가지로 정보를 확인하기 위해서
    // JoinPoint 객체가 필요하고 추가적으로 afterReturning에는
    // Object를 받을 수 있다.
    @AfterReturning(value= "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinpoint , Object returnObj ) {
        System.out.println("return obj");
        System.out.println(returnObj);
    }


    // 정리하자면 com.example.aop.controller 패키지 하위에 있는 모든 메서드들중 한 메서드가 실행이 되면
    // 실행이 되기전에 before 에 지정된 메서드가 실행이되고
    // 정상적으로 실행이 되고 리턴이된다면 해당 오브젝트를 AfterReturning 어노테이션이 있는 메서드에서 확인을 할 수 있다.

}
