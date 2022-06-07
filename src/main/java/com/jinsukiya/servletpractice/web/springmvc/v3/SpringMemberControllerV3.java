package com.jinsukiya.servletpractice.web.springmvc.v3;

import com.jinsukiya.servletpractice.domain.member.Member;
import com.jinsukiya.servletpractice.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @RequestMapping("/new-form")
    public String createForm() {
            return "new-form";
    }

    @RequestMapping
    public String getMembers(Model model) throws ServletException, IOException {

        List<Member> members = this.memberRepository.findAll();

        model.addAttribute("members",members);

        return "members";

    }
    @RequestMapping("/save")
    public String createMember(@RequestParam("username") String username, @RequestParam("age")int age, Model model) throws ServletException, IOException {

        Member member = new Member(username,age);
        memberRepository.save(member);

        model.addAttribute("member", member);

        return "save-result";

    }
}
