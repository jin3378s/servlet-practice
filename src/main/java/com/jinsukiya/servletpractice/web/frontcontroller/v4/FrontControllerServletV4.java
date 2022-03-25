package com.jinsukiya.servletpractice.web.frontcontroller.v4;

import com.jinsukiya.servletpractice.web.frontcontroller.ModelView;
import com.jinsukiya.servletpractice.web.frontcontroller.MyView;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.ControllerV3;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.jinsukiya.servletpractice.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.jinsukiya.servletpractice.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.jinsukiya.servletpractice.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerV4Map.get(requestURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // paramMap
        Map<String,String> paramMap = new HashMap<>();

        createParamMap(request, paramMap);
Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);

        view.render(model, request,response);
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
