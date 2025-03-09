package com.example.hello_spring;

import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;
import com.example.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바코드로 직접 스프링빈에 등록
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        // 리포지토리를 연결해줌
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // 리포지토리를 위의 서비스에 연결
        return new MemoryMemberRepository();
    }
}
