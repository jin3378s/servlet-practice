package com.jinsukiya.servletpractice.web.frontcontroller.v3.controller;

import com.jinsukiya.servletpractice.domain.member.Member;
import com.jinsukiya.servletpractice.domain.member.MemberRepository;
import com.jinsukiya.servletpractice.web.frontcontroller.ModelView;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members",members);

        return modelView;


    }
}
