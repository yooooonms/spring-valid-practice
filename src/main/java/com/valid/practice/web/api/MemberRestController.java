package com.valid.practice.web.api;

import com.valid.practice.exception.IdBlankException;
import com.valid.practice.member.MemberService;
import com.valid.practice.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/duplicateCheck")
    public ResponseEntity<?> idDuplicateCheck(@RequestParam("id") String identifier) {

        if (identifier.isBlank()) {
            throw new IdBlankException("아이디를 입력해주세요 :(");
        }

        log.debug("Member Identifier: " + identifier);

        return new ResponseEntity<>(memberService.duplicateCheck(identifier), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Long memberJoin(@Valid @RequestBody MemberDto memberDto) {
        log.debug("MemberDTO: " + memberDto);
        return memberService.saveMember(memberDto);
    }


}
