package kh.petmily.controller;

import kh.petmily.domain.find_board.form.FindBoardDetailForm;
import kh.petmily.domain.find_board.form.FindBoardModifyForm;
import kh.petmily.domain.find_board.form.FindBoardPageForm;
import kh.petmily.domain.find_board.form.FindBoardWriteForm;
import kh.petmily.domain.member.Member;
import kh.petmily.service.FindBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;

@Controller
@RequestMapping("/findBoard")
@RequiredArgsConstructor
@Slf4j
public class FindBoardController {
    private final FindBoardService findBoardService;

    @ResponseBody
    @GetMapping("/upload")
    public ResponseEntity<Resource> list(String filename, HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";
        fullPath = fullPath + filename;

        log.info("fullPath = {}", fullPath);

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new UrlResource("file:" + fullPath));
        } catch (MalformedURLException e) {
            log.info("fullPath = {}", fullPath);

            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        String pageNoVal = request.getParameter("pageNo");
        int pageNo = 1;

        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }

        FindBoardPageForm Finds = findBoardService.getFindPage(pageNo);
        model.addAttribute("Finds", Finds);

        return "/find_board/listFindBoard";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("faNumber") int faNumber, Model model) {
        FindBoardDetailForm detailForm = findBoardService.getDetailForm(faNumber);
        log.info("FindDetailForm = {}", detailForm);

        model.addAttribute("findIn", detailForm);

        return "/find_board/detailFindBoard";
    }

    //=======작성=======
    @GetMapping("/auth/write")
    public String writeForm() {

        return "/find_board/writeFindBoardForm";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute FindBoardWriteForm findBoardWriteForm, HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        findBoardWriteForm.setMNumber(mNumber);

        String filename = "";

        if(!findBoardWriteForm.getImgPath().isEmpty()) {
            try {
                filename = findBoardService.storeFile(findBoardWriteForm.getImgPath(), fullPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            findBoardWriteForm.setFullPath(filename);
        } else {
            findBoardWriteForm.setFullPath("");
        }

        log.info("FindWriteForm = {}", findBoardWriteForm);

        findBoardService.write(findBoardWriteForm);

        return "/find_board/submitSuccess";
    }

    //=======수정=======
    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam("faNumber") int faNumber, HttpServletRequest request, Model model) {
        FindBoardModifyForm fmForm = findBoardService.getModifyForm(faNumber);

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        fmForm.setMNumber(mNumber);
        fmForm.setFaNumber(faNumber);

        model.addAttribute("findMod", fmForm);

        return "/find_board/modifyFindBoardForm";
    }

    @PostMapping("/auth/modify")
    public String modify(@RequestParam("faNumber") int faNumber,
                         @ModelAttribute FindBoardModifyForm findBoardModifyForm,
                         HttpServletRequest request,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        findBoardModifyForm.setMNumber(mNumber);
        findBoardModifyForm.setFaNumber(faNumber);

        String filename = null;

        try {
            filename = findBoardService.storeFile(findBoardModifyForm.getImgPath(), fullPath);
            findBoardModifyForm.setFullPath(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("FinModifyForm = {}", findBoardModifyForm);

        findBoardService.modify(findBoardModifyForm);

        model.addAttribute("findMod", findBoardModifyForm);

        redirectAttributes.addAttribute("faNumber", faNumber);

        return "redirect:/findBoard/detail?faNumber={faNumber}";
    }

    //=======삭제=======
    @GetMapping("/auth/delete")
    public String delete(@RequestParam("faNumber") int faNumber) {
        findBoardService.delete(faNumber);

        return "/find_board/submitSuccess";
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }
}