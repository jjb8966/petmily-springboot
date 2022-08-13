package kh.petmily.controller;

import kh.petmily.domain.board.form.BoardPage;
import kh.petmily.domain.board.form.WriteBoardForm;
import kh.petmily.domain.member.Member;
import kh.petmily.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) throws Exception {
        String pbNumberVal = request.getParameter("pbNumber");
        String kindOfBoard = request.getParameter("kindOfBoard");

        int pbNumber = 1;

        if (pbNumberVal != null) {
            pbNumber =
                    Integer.parseInt(pbNumberVal);
        }

        BoardPage boardPage = boardService.getBoardPage(pbNumber, kindOfBoard);
        model.addAttribute("readBoardForm", boardPage);
        model.addAttribute("kindOfBoard", kindOfBoard);

        return "/board/boardList";
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

        return "/board/writeBoardSuccess";
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");
        return member;
    }
}
