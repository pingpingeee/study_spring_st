package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {
    // 인터페이스 - 클래스
    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        // Member 객체 생성
        Member member = new Member();
        // 객체 이름 지정
        member.setName("spring");

        // 저장소에 해당 멤버 저장
        repository.save(member);

        // id찾기
        // Optional일 경우 .get()활용(테스트여서 get()사용, 실무에서는 다름)
        Member result = repository.findById(member.getId()).get();

        // 아래처럼 출력으로 테스트를 할 수 있지만
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals 사용가능
        // Assertions.assertEquals(member, result);

        // jupiter 말고 assertj사용
        Assertions.assertThat(member).isEqualTo(result);
    }
}
