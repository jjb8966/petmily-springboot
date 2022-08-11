package kh.petmily.controller;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.member.Member;
import kh.petmily.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberDao memberDao;

    // 회원 가입
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinForm() {
        return "/login/joinForm";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@ModelAttribute("joinRequest") JoinRequest joinRequest) {
        log.info("넘어온 joinRequest : {}", joinRequest);

        if (!joinRequest.isPwEqualToConfirm()) {
            return "login/joinForm";
        }

        memberService.join(joinRequest);

        return "/login/loginForm";
    }

    // 로그인
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "/login/loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("id") String id,
            @RequestParam("pw") String pw,
            HttpServletRequest request) {

        Member authUser = memberService.login(id, pw);

        if (authUser == null) {
            return "/login/loginForm";
        }

        request.getSession().setAttribute("authUser", authUser);

        return "/main/index";
    }

    // 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();

        return "/main/index";
    }

    @RequestMapping("/")
    public String home() {
        return "/main/index";
    }

    // mypage
    @GetMapping("/member/mypage")
    public String mypage(HttpServletRequest request, Model model) {
        Member member = getAuthMember(request);

        String id = member.getId();

//        MemberInfo memberInfo = memberService.findById(id);
        MemberInfo memberInfo = toMemberInfoForm(member);

//        request.setAttribute("memberInfo", memberInfo);
        model.addAttribute("memberInfo", memberInfo);

        return "/member/mypage";
    }

    //change member Info
    @GetMapping(value = "/member/change_info")
    private String processForm(HttpSession session, Model model) {
        Member user = (Member) session.getAttribute("authUser");
        log.info("user = {} ", user);
        String id = user.getId();
        log.info("id = {} ", id);

        Member memberInfo = memberService.findById(id);
        model.addAttribute("memberInfo", memberInfo);
        return "/member/changeMemberInfo";
    }

    @PostMapping(value = "/member/change_info")
    private String processSubmit(HttpSession session, Model model, MemberInfo memberInfo, HttpServletResponse res) throws Exception {
        log.info("[Request MemberInfo] = {} ", memberInfo);
        Member user = (Member) session.getAttribute("authUser");
        String id = user.getId();
        Member info = memberService.findById(id);

        log.info("[findMemberInfo] = {} ", info);
        Map<String, Boolean> errors = new HashMap<>();
        model.addAttribute("errors", errors);

        try {
            memberService.changeMemberInfo(user.getMNumber(), memberInfo);
            Member member = memberService.findById(id);
            session.setAttribute("authUser", member);
        } catch (NoSuchElementException e) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return "/member/mypage";
    }

    private MemberInfo toMemberInfoForm(Member member) {
        return new MemberInfo(member.getId(), member.getPw(), member.getName(), member.getBirth(), member.getGender(), member.getEmail(), member.getPhone(), member.getGrade());
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");
        return member;
    }
}
