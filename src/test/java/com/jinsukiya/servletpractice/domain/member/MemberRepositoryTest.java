package com.jinsukiya.servletpractice.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member("hello",20);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member foundMember = memberRepository.findById(member.getId());
        assertThat(foundMember).isEqualTo(savedMember);
    }


    @Test
    void findAll() {
        // given
        Member member1 = new Member("hello1",20);
        Member member2 = new Member("hello2",20);
        Member member3 = new Member("hello3",20);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        // when

        List<Member> foundMembers = memberRepository.findAll();
        // then

        assertThat(foundMembers.size()).isEqualTo(3);
        assertThat(foundMembers).contains(member1, member2, member3);

    }
}