package kh.petmily.controller;

import kh.petmily.domain.adopt_review.form.AdoptReviewForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewWriteForm;
import kh.petmily.domain.adopt_review.form.BoardPage;
import kh.petmily.domain.member.Member;
import kh.petmily.service.AdoptReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/adopt_review")
@RequiredArgsConstructor
@Slf4j
public class AdoptReviewController {
    private final AdoptReviewService adoptReviewService;

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        String pbNumberVal = request.getParameter("pbNumber");
        String kindOfBoard = request.getParameter("kindOfBoard");

        int pbNumber = 1;

        if (pbNumberVal != null) {
            pbNumber = Integer.parseInt(pbNumberVal);
        }

        BoardPage boardPage = adoptReviewService.getAdoptReviewPage(pbNumber, kindOfBoard);
        model.addAttribute("boardList", boardPage);
        model.addAttribute("kindOfBoard", kindOfBoard);

        log.info("kindOfBoard = {}", kindOfBoard);
        return "/adopt_review/listAdoptReview";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("bNumber") int bNumber, Model model) {
        AdoptReviewForm detailForm = adoptReviewService.getAdoptReview(bNumber);

        model.addAttribute("detailForm", detailForm);

        return "/adopt_review/detailFormAdoptReview";
    }

    @GetMapping("/auth/write")
    public String writeForm() {
        return "/adopt_review/writeAdoptReviewForm";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute AdoptReviewWriteForm adoptReviewWriteForm, HttpServletRequest request) {
        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        adoptReviewWriteForm.setMNumber(mNumber);

        log.info("WriteBoardForm = {}", adoptReviewWriteForm);

        adoptReviewService.write(adoptReviewWriteForm);

        return "/adopt_review/writeAdoptReviewSuccess";
    }

    @GetMapping("/auth/modify")
    private String modifyForm(@RequestParam("bNumber") int bNumber, HttpServletRequest request, Model model) {
        AdoptReviewModifyForm modReq = adoptReviewService.getAdoptReviewModify(bNumber);
        Member authUser = getAuthMember(request);

        int mNumber = authUser.getMNumber();
        modReq.setMNumber(mNumber);

        log.info("modReq={}", modReq);

        model.addAttribute("modReq", modReq);

        return "/adopt_review/modifyAdoptReviewForm";
    }

    @PostMapping("/auth/modify")
    public String modify(@ModelAttribute AdoptReviewModifyForm modReq, HttpServletRequest request, Model model){
        Member authUser = getAuthMember(request);

        int mNumber = authUser.getMNumber();
        modReq.setMNumber(mNumber);

        log.info("AdoptModifyForm = {}", modReq);

        adoptReviewService.modify(modReq);
        model.addAttribute("modReq", modReq);

        return "/adopt_review/modifySuccess";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam("bNumber") int bNumber){
        adoptReviewService.delete(bNumber);

        return "/adopt_review/deleteSuccess";
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");
        return member;
    }
}