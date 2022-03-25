package com.jinsukiya.servletpractice.web.frontcontroller.v3.controller;

import com.jinsukiya.servletpractice.web.frontcontroller.ModelView;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
