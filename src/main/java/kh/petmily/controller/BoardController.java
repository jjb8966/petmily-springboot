package kh.petmily.controller;

import kh.petmily.domain.board.form.BoardPage;
import kh.petmily.domain.board.form.WriteBoardForm;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.ReadBoardForm;
import kh.petmily.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        String pbNumberVal = request.getParameter("pbNumber");
        String kindOfBoard = request.getParameter("kindOfBoard");

        int pbNumber = 1;

        if (pbNumberVal != null) {
            pbNumber = Integer.parseInt(pbNumberVal);
        }

        BoardPage boardPage = boardService.getBoardPage(pbNumber, kindOfBoard);
        model.addAttribute("readBoardForm", boardPage);
        model.addAttribute("kindOfBoard", kindOfBoard);

        return "/board/boardList";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("bNumber") int bNumber, Model model) {
        ReadBoardForm detailForm = boardService.getBoard(bNumber);

        model.addAttribute("detailForm", detailForm);

        return "/board/boardDetailForm";
    }

    @GetMapping("/auth/write")
    public String writeForm() {
        return "/board/writeBoardForm";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute WriteBoardForm writeBoardForm, HttpServletRequest request) {
        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        writeBoardForm.setMNumber(mNumber);

        log.info("WriteBoardForm = {}", writeBoardForm);

        boardService.write(writeBoardForm);

        log.info("WriteBoardForm = {}", writeBoardForm);

        return "/board/writeBoardSuccess";
    }

    @GetMapping("/auth/modify")
    private String modifyForm(@RequestParam("bNumber") int bNumber, HttpServletRequest request, Model model) {
        BoardModifyForm modReq = boardService.getBoardModify(bNumber);
        Member authUser = getAuthMember(request);

        int mNumber = authUser.getMNumber();
        modReq.setMNumber(mNumber);

        log.info("bNumber = {}", bNumber);

        model.addAttribute("modReq", modReq);

        return "/board/modifyForm";
    }

    @PostMapping("/auth/modify")
    public String modify(@ModelAttribute BoardModifyForm modReq, HttpServletRequest request, Model model){
        Member authUser = getAuthMember(request);

        int mNumber = authUser.getMNumber();
        modReq.setMNumber(mNumber);

        log.info("BoardModifyForm = {}", modReq);

        boardService.modify(modReq);
        model.addAttribute("modReq", modReq);

        log.info("boardModifyForm = {}", modReq);

        return "/board/modifySuccess";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam("bNumber") int bNumber){
        boardService.delete(bNumber);

        return "/board/deleteSuccess";
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");
        return member;
    }
}