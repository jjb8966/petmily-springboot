package kh.petmily.controller;

import kh.petmily.domain.board.form.BoardPage;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.ReadBoardForm;
import kh.petmily.domain.member.Member;
import kh.petmily.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("bNumber") int bNumber, Model model) {
        ReadBoardForm detailForm = boardService.getBoard(bNumber);

        model.addAttribute("detailForm", detailForm);

        return "/board/boardDetailForm";
    }


    @GetMapping("/auth/delete")
    public String delete(@RequestParam("bNumber") int bNumber){
        boardService.delete(bNumber);

        return "/board/deleteSuccess";
    }
}
