package com.jinsukiya.servletpractice.web.frontcontroller.v1;

import com.jinsukiya.servletpractice.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.jinsukiya.servletpractice.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.jinsukiya.servletpractice.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    private Map<String, ControlleV1> controlleV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        controlleV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controlleV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controlleV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();

        ControlleV1 controller = controlleV1Map.get(requestURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response);
    }
}
