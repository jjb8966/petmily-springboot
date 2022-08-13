package kh.petmily.controller;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberChangeForm;
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
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberDao memberDao;

    // 회원 가입
    @GetMapping("/join")
    public String joinForm() {
        return "/login/joinForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("joinRequest") JoinRequest joinRequest) {
        log.info("넘어온 joinRequest : {}", joinRequest);

        if (!joinRequest.isPwEqualToConfirm()) {
            return "login/joinForm";
        }

        memberService.join(joinRequest);

        return "/login/loginForm";
    }

    // 로그인
    @GetMapping("/login")
    public String loginForm() {
        return "/login/loginForm";
    }

    @PostMapping("/login")
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

        model.addAttribute("member", member);

        return "/member/mypage";
    }

    //change member Info
    @GetMapping(value = "/member/change_info")
    private String changeInfo(HttpServletRequest request, Model model) {
        Member member = getAuthMember(request);

        model.addAttribute("memberInfo", member);

        return "/member/changeMemberInfo";
    }

    @PostMapping(value = "/member/change_info")
    private String changeInfoPost(HttpServletRequest request, Model model, MemberChangeForm memberChangeForm) {
        Member member = getAuthMember(request);

        Member mem = memberService.modify(member, memberChangeForm);

        model.addAttribute("member", mem);
        model.addAttribute("authUser", mem);

        return "/member/mypage";
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }

    // 회원탈퇴
    @GetMapping("/member/withdraw")
    public String withdrawForm() {
        return "/member/withdrawForm";
    }

    @PostMapping("/member/withdraw")
    public String withdraw(HttpServletRequest request, @RequestParam String pw, @RequestParam String confirmPw) {

        log.info("pw = {}", pw);
        log.info("confirmPw = {}", confirmPw);

        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        Map<String, Boolean> errors = new HashMap<>();
        request.setAttribute("errors", errors);

        if (!memberService.isPwEqualToConfirm(pw, confirmPw)) {
            errors.put("notMatch", Boolean.TRUE);
            return "/member/withdrawForm";
        } else if (!memberService.checkPwCorrect(mNumber, pw)) {
            errors.put("notCorrect", Boolean.TRUE);
            return "/member/withdrawForm";
        }

        memberService.withdraw(mNumber);
        request.getSession().invalidate();

        return "/member/withdrawSuccess";
    }
}