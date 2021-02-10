package com.valid.practice.member;

import com.valid.practice.exception.DuplicatedException;
import com.valid.practice.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public boolean duplicateCheck(String identifier) {
        if (memberRepository.existsByIdentifier(identifier)) {
            throw new DuplicatedException("이미 사용중인 아이디입니다 :(");
        }
        return true;
    }


    @Transactional
    public Long saveMember(MemberDto memberDto) {

        Member member = memberDto.toEntity();

        return memberRepository.save(member).getId();

    }
}
