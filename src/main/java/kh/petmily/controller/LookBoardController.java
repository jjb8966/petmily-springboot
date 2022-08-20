package kh.petmily.controller;

import kh.petmily.domain.look_board.form.LookBoardDetailForm;
import kh.petmily.domain.look_board.form.LookBoardModifyForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardWriteForm;
import kh.petmily.domain.member.Member;
import kh.petmily.service.LookBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/lookBoard")
@RequiredArgsConstructor
@Slf4j
public class LookBoardController {

    private final LookBoardService lookBoardService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer pageNo,
                       @RequestParam(required = false) String species,
                       @RequestParam(required = false) String animalState,
                       @RequestParam(required = false) String keyword,
                       HttpServletRequest request,
                       Model model) {

        HttpSession session = request.getSession();

        if (pageNo == null) {
            initCondition(species, animalState, keyword, session);
            pageNo = 1;
        }

        saveCondition(species, animalState, keyword, session);

        species = (String) request.getSession().getAttribute("species");
        animalState = (String) request.getSession().getAttribute("animalState");
        keyword = (String) request.getSession().getAttribute("keyword");

        log.info("species = {}", species);
        log.info("animalState = {}", animalState);
        log.info("keyword = {}", keyword);

        LookBoardPageForm lookBoardPageForm = lookBoardService.getLookPage(pageNo, species, animalState, keyword);
        model.addAttribute("Looks", lookBoardPageForm);

        return "/look_board/listLookBoard";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("laNumber") int laNumber, Model model) {
        LookBoardDetailForm detailForm = lookBoardService.getDetailForm(laNumber);
        log.info("LookDetailForm = {}", detailForm);

        // ====== 조회수 추가된 부분 ======
        lookBoardService.updateViewCount(laNumber);

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

    //=======수정=======
    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam("laNumber") int laNumber, HttpServletRequest request, Model model) {
        LookBoardModifyForm lmForm = lookBoardService.getModifyForm(laNumber);

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        lmForm.setMNumber(mNumber);
        lmForm.setLaNumber(laNumber);

        model.addAttribute("lookMod", lmForm);

        return "/look_board/modifyLookBoardForm";
    }

    @PostMapping("/auth/modify")
    public String modify(@RequestParam("laNumber") int laNumber,
                         @ModelAttribute LookBoardModifyForm lookBoardModifyForm,
                         HttpServletRequest request,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        lookBoardModifyForm.setMNumber(mNumber);
        lookBoardModifyForm.setLaNumber(laNumber);

        log.info("LookModifyForm = {}", lookBoardModifyForm);

        lookBoardService.modify(lookBoardModifyForm);

        model.addAttribute("lookMod", lookBoardModifyForm);

        redirectAttributes.addAttribute("laNumber", laNumber);

        return "redirect:/lookBoard/detail?laNumber={laNumber}";
    }

    //=======삭제=======
    @GetMapping("/auth/delete")
    public String delete(@RequestParam("laNumber") int laNumber) {
        lookBoardService.delete(laNumber);

        return "/look_board/submitSuccess";
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }

    private void saveCondition(String species, String animalState, String keyword, HttpSession session) {
        if (species != null) {
            session.setAttribute("species", species);
        }

        if (species != null) {
            session.setAttribute("animalState", animalState);
        }

        if (keyword != null) {
            if (!keyword.equals("")) {
                session.setAttribute("keyword", keyword);
            } else {
                session.setAttribute("keyword", "allKeyword");
            }
        }
    }

    private void initCondition(String species, String animalState, String keyword, HttpSession session) {
        if (species == null && animalState == null && keyword == null) {
            session.removeAttribute("species");
            session.removeAttribute("animalState");
            session.removeAttribute("keyword");
        }
    }
}