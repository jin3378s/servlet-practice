package com.jinsukiya.servletpractice.web.frontcontroller.v3;

import com.jinsukiya.servletpractice.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
