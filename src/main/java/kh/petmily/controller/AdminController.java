package kh.petmily.controller;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberModifyForm;
import kh.petmily.domain.member.form.MemberPageForm;
import kh.petmily.service.BoardService;
import kh.petmily.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final MemberService memberService;

    @GetMapping
    public String adminPage() {
        return "/admin/adminPage";
    }

    // 회원 리스트
    @GetMapping("/member")
    public String memberPage(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        MemberPageForm memberPageForm = memberService.getMemberPage(pageNo);
        model.addAttribute("memberPageForm", memberPageForm);

        return "/admin/member/adminMemberList";
    }

    // 회원 정보 추가(insert)
    @GetMapping("/member/insert")
    public String memberCreateForm() {
        return "admin/member/adminInsertForm";
    }

    @PostMapping("/member/insert")
    public String join(@ModelAttribute("joinRequest") JoinRequest joinRequest) {

        if (!joinRequest.isPwEqualToConfirm()) {
            return "/admin/member/adminInsertForm";
        }
        memberService.join(joinRequest);
        return "/admin/member/insertSuccess";
    }

    // 회원 정보 수정(modify)
    @GetMapping("/member/modify")
    public String memberDetailModify(@RequestParam("mNumber") int mNumber, Model model) {
        MemberModifyForm memberModifyForm = memberService.getMemberModify(mNumber);
        memberModifyForm.setMNumber(mNumber);

        model.addAttribute("member", memberModifyForm);

        return "admin/member/adminMemberModify";
    }

    @PostMapping("/member/modify")
    public String memberModify(@ModelAttribute MemberModifyForm memberModifyForm, Model model, RedirectAttributes redirectAttributes) {
        log.info("memberModifyForm = {}", memberModifyForm);

        memberService.modify(memberModifyForm);

        model.addAttribute("memberModify", memberModifyForm);

        return "redirect:/admin/member";
    }

    // 회원 정보 삭제(delete)
    @GetMapping("/member/delete")
    public String MemberDelete(@RequestParam("mNumber") int mNumber) {
        memberService.delete(mNumber);

        return "/admin/member/deleteMemberSuccess";
    }

    @GetMapping("/animal")
    public void animalPage() {

    }

    @GetMapping("/board")
    public void boardPage() {

    }

    @GetMapping("/adopt_temp")
    public void adoptTempPage() {

    }

    @GetMapping("/donation")
    public void donationPage() {

    }

    @GetMapping("/volunteer")
    public void volunteerPage() {

    }
}