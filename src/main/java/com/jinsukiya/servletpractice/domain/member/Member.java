package com.jinsukiya.servletpractice.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private Long id;
    private String username;
    private Integer age;

    public Member(String username, Integer age){
        this.username = username;
        this.age = age;
    }

}
