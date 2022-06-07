package com.jinsukiya.servletpractice.web.springmvc.v2;

import com.jinsukiya.servletpractice.domain.member.Member;
import com.jinsukiya.servletpractice.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @RequestMapping("/new-form")
    public ModelAndView createForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/")
    public ModelAndView getMembers() throws ServletException, IOException {

        List<Member> members = this.memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",members);

        return mv;

    }
    @RequestMapping("/save")
    public ModelAndView createMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username,age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member",member);

        return mv;

    }
}
