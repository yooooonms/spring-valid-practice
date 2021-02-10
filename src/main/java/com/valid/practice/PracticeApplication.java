package com.valid.practice;

import com.valid.practice.member.Member;
import com.valid.practice.member.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
        return args -> {
            Member newMember = Member.builder()
                    .identifier("freems0617")
                    .password("0000")
                    .email("test@test.com")
                    .build();

            memberRepository.save(newMember);
        };
    }

}
