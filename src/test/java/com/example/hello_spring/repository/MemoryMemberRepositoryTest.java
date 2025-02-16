package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    // 객체생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메소드가 끝나고 동작하게 하는 콜백
    public void afterEach() {
        repository.clearStore();
    }

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

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 단어 드래그 필요없이 Shift + F6하면 전체 수정
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }


}
