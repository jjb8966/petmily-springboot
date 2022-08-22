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
    public String list(@RequestParam(required = false) Integer pbNumber,
                       @RequestParam(required = false) String kindOfBoard,
                       @RequestParam(required = false) String searchType,
                       @RequestParam(required = false) String keyword,
                       HttpServletRequest request,
                       Model model) {
        HttpSession session = request.getSession();

        //====== 검색 추가 ======
        if (pbNumber == null) {
            initCondition(kindOfBoard, searchType, keyword, session);
            pbNumber = 1;
        }

        saveCondition(kindOfBoard, searchType, keyword, session);

        kindOfBoard = (String) session.getAttribute("kindOfBoard");
        searchType = (String) session.getAttribute("searchType");
        keyword = (String) session.getAttribute("keyword");

        BoardPage boardPage = adoptReviewService.getAdoptReviewPage(pbNumber, kindOfBoard, searchType, keyword);
        model.addAttribute("boardList", boardPage);
        model.addAttribute("kindOfBoard", kindOfBoard);

        log.info("kindOfBoard = {}", kindOfBoard);
        log.info("searchType = {}", searchType);
        log.info("keyword = {}", keyword);

        return "/adopt_review/listAdoptReview";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("bNumber") int bNumber, Model model) {
        AdoptReviewForm detailForm = adoptReviewService.getAdoptReview(bNumber);

        // ====== 조회수 추가 ======
        adoptReviewService.updateViewCount(bNumber);

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
    public String modify(@ModelAttribute AdoptReviewModifyForm modReq, HttpServletRequest request, Model model) {
        Member authUser = getAuthMember(request);

        int mNumber = authUser.getMNumber();
        modReq.setMNumber(mNumber);

        log.info("AdoptModifyForm = {}", modReq);

        adoptReviewService.modify(modReq);
        model.addAttribute("modReq", modReq);

        return "/adopt_review/modifySuccess";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam("bNumber") int bNumber) {
        adoptReviewService.delete(bNumber);

        return "/adopt_review/deleteSuccess";
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");
        return member;
    }

    private void saveCondition(String kindOfBoard, String searchType, String keyword, HttpSession session) {
        if (kindOfBoard != null) {
            if (!kindOfBoard.equals("")) {
                session.setAttribute("kindOfBoard", kindOfBoard);
            } else {
                session.setAttribute("kindOfBoard", "유기동물");
            }
        }

        if (searchType != null) {
            session.setAttribute("searchType", searchType);
        }

        if (keyword != null) {
            if (!keyword.equals("")) {
                session.setAttribute("keyword", keyword);
            } else {
                session.setAttribute("keyword", "allKeyword");
            }
        }
    }

    private void initCondition(String kindOfBoard, String searchType, String keyword, HttpSession session) {
        if (kindOfBoard != null && searchType == null && keyword == null) {
            session.removeAttribute("kindOfBoard");
            session.removeAttribute("searchType");
            session.removeAttribute("keyword");
        }
    }
}