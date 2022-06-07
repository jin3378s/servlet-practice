package com.jinsukiya.servletpractice.web.frontcontroller.v5.adapter;

import com.jinsukiya.servletpractice.web.frontcontroller.ModelView;
import com.jinsukiya.servletpractice.web.frontcontroller.MyView;
import com.jinsukiya.servletpractice.web.frontcontroller.v4.ControllerV4;
import com.jinsukiya.servletpractice.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4Adapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String,String> paramMap = new HashMap<>();
        createParamMap(request, paramMap);

        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName);

        mv.setModel(model);

        return mv;

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName+ ".jsp");
    }

    private void createParamMap(HttpServletRequest request, Map<String, String> paramMap) {
        request
                .getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
    }

}