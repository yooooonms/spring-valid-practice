package com.valid.practice.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Builder
    public Member(String identifier, String password, String email) {
        this.identifier = identifier;
        this.password = password;
        this.email = email;
    }
}
