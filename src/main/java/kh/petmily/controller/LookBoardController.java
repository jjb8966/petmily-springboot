package kh.petmily.controller;

import kh.petmily.domain.look_board.form.LookBoardDetailForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardWriteForm;
import kh.petmily.domain.member.Member;
import kh.petmily.service.LookBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/lookBoard")
@RequiredArgsConstructor
@Slf4j
public class LookBoardController {
    private final LookBoardService lookBoardService;

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        String pageNoVal = request.getParameter("pageNo");
        int pageNo = 1;

        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }

        LookBoardPageForm Looks = lookBoardService.getLookPage(pageNo);
        model.addAttribute("Looks", Looks);

        return "/look_board/listLookBoard";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("laNumber") int laNumber, Model model) {
        LookBoardDetailForm detailForm = lookBoardService.getDetailForm(laNumber);
        log.info("LookDetailForm = {}", detailForm);

        model.addAttribute("lookIn", detailForm);

        return "/look_board/detailLookBoard";
    }

    //=======작성=======
    @GetMapping("/auth/write")
    public String writeForm() {

        return "/look_board/writeLookBoardForm";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute LookBoardWriteForm lookBoardWriteForm, HttpServletRequest request) {
        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        lookBoardWriteForm.setMNumber(mNumber);

        log.info("LookWriteForm = {}", lookBoardWriteForm);

        lookBoardService.write(lookBoardWriteForm);

        return "/look_board/submitSuccess";
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }
}