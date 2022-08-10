package kh.petmily.controller;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.member.Member;
import kh.petmily.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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

    private MemberInfo toMemberInfoForm(Member member) {
        return new MemberInfo(member.getId(), member.getPw(), member.getName(), member.getBirth(), member.getGender(), member.getEmail(), member.getPhone(), member.getGrade());
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

        Member member = getAuthMember(request); // 로그인된 회원 정보 member에 담음
        int mNumber = member.getMNumber(); // member객체에서 mNumber정보 받아옴

        // 검증
        Map<String, Boolean> errors = new HashMap<>();
        request.setAttribute("errors", errors);

        if (!memberService.isPwEqualToConfirm(pw, confirmPw)) { // 비번!=비번확인
            errors.put("notMatch", Boolean.TRUE);
            return "/member/withdrawForm";
        } else if (!memberService.checkPwCorrect(mNumber, pw)) { //비밀번호가 틀림
            errors.put("notCorrect", Boolean.TRUE);
            return "/member/withdrawForm";
        }

        memberService.withdraw(mNumber);    // MemberService에서 mNumber 이용해 회원탈퇴
        request.getSession().invalidate();  // 남아있는 세션 삭제

        return "/member/withdrawSuccess";
    }


}
















