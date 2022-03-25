package com.jinsukiya.servletpractice.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private Long id;
    private String usrename;
    private Integer age;

    public Member(String username, Integer age){
        this.usrename = username;
        this.age = age;
    }

}
