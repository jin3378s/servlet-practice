package com.jinsukiya.servletpractice.web.frontcontroller.v4.controller;

import com.jinsukiya.servletpractice.domain.member.Member;
import com.jinsukiya.servletpractice.domain.member.MemberRepository;
import com.jinsukiya.servletpractice.web.frontcontroller.ModelView;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.ControllerV3;
import com.jinsukiya.servletpractice.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members",members);
        return "members";
    }
}
