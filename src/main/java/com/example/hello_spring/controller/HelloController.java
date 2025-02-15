package com.example.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 컨트롤러에 해당 어노테이션
@Controller
public class HelloController {

    // html연결해줄 때 @GetMapping 어노테이션 필수
    @GetMapping("hello")
    public String hello(Model model) {
        // data라는 키값을 html에 ${data}로 값을 넘겨줌
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    // RequestParam()사용
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
}
