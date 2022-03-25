package com.jinsukiya.servletpractice.web.frontcontroller.v5;

import com.jinsukiya.servletpractice.web.frontcontroller.ModelView;
import com.jinsukiya.servletpractice.web.frontcontroller.MyView;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.ControllerV3;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.jinsukiya.servletpractice.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.jinsukiya.servletpractice.web.frontcontroller.v5.adapter.ControllerV3Adapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private boolean initHandlerAdapters() {
        return handlerAdapters.add(new ControllerV3Adapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler =  getHandler(request);

        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request,response,handler);

        MyView view = viewResolver(mv);
        view.render(mv.getModel(), request,response);
    }

    private MyView viewResolver(ModelView mv) {
        return new MyView("/WEB-INF/views/" + mv.getViewName() + ".jsp");
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }

        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = "+ handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        return handlerMappingMap.get(requestURI);
    }
}
