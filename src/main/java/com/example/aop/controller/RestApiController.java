package com.example.aop.controller;


import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {


    // AOP 예제를 진행하기 이전에 진행하는 기본적은 REST API TEST
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {


        return id+ " " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {



        return user;
    }


    // 위에서의 기본적인 REST API인 GET , POST를 확인했다.
    // AOP 예제 테스트를 본격적으로 시작하기 이전에
    // 실제 프로젝트에는 위와 같은 API들이 많이 존재할 확률이 높다.(10~20개)
    // 위에서의 두개의 메서드는 로그를 전부 찍었지만 실제 프로젝트에서의 많은 api들이 존재한다면
    // 로그를 일일이 찍기에는 어려움이 있을 것 이다. 각 메서드마다 로그를 찍는 부분을 한 곳으로 모을 수 있따.
    // -> aop - ParameterAop를 확인해보자.
}
