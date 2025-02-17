package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 이렇게 해야 각 테스트 실행 전(@BeforeEach)에
    // 디펜더지 인젝션
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        // 밑에 test로 객체 2개를 생성했기에 test를 하면 에러, 발생하기에 선언해놨던 cleatStore()활용
        member.setName("test1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("test");
        Member member2 = new Member();
        member2.setName("test");
        //when
        // try catch말고
        memberService.join(member1);
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        asserThrows에 Ctrl + Alt + v 사용
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");

//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            // MemberService에서 던져주는 에러 문자열이랑 똑같은지 확인
//            // 이거때문에 try catch를 넣기엔 애매함
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}