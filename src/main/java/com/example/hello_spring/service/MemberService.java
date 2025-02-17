package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 직접 new 생성이 아니라 외부에서 넣어주도록 변경
    // 테스트케이스 참고
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        // Optional을 바로 반환하는걸 권장하지않음
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        Member member1 = result.get(); <= 이 방법이 있긴한데 굳이 많이 쓰진않음
        
        // Ctrl + Alt + M 단축키 생성해서 메소드 생성
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 만약 회원이 있다면 예외처리
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
