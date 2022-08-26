package kh.petmily.controller;

import kh.petmily.domain.admin.form.AdminBoardListForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.find_board.form.FindBoardModifyForm;
import kh.petmily.domain.look_board.form.LookBoardModifyForm;
import kh.petmily.domain.member.Member;
import kh.petmily.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final MemberService memberService;
    private final BoardService boardService;
    private final AdoptReviewService adoptReviewService;
    private final FindBoardService findBoardService;
    private final LookBoardService lookBoardService;

    @GetMapping
    public String adminPage() {
        return "/admin/adminPage";
    }

    @GetMapping("/member")
    public void memberPage() {

    }

    @GetMapping("/animal")
    public void animalPage() {

    }

    @GetMapping("/board")
    public String boardPage(@RequestParam("kindOfBoard") String kind, Model model) {
        List<AdminBoardListForm> boardForm = new ArrayList<>();

        if(kind.equals("자유")) {
            boardForm = boardService.selectAll("자유");
        } else if(kind.equals("문의")) {
            boardForm = boardService.selectAll("문의");
        } else if(kind.equals("입양후기")) {
            boardForm = adoptReviewService.selectAll("입양후기");
        } else if(kind.equals("find")) {
            boardForm = findBoardService.selectAll();
        } else if(kind.equals("look")) {
            boardForm = lookBoardService.selectAll();
        }

        model.addAttribute("boardForm", boardForm);

        return "/admin/adminBoardList";
    }

    @GetMapping("/board/write")
    public String boardWrite(@RequestParam("kindOfBoard") String kind, Model model) {
        List<Member> list = memberService.selectAll();
        model.addAttribute("Mems", list);

        if(kind.equals("자유") || kind.equals("문의")) {

            return "/board/writeBoardForm";
        } else if (kind.equals("입양후기")) {

            return "/adopt_review/writeAdoptReviewForm";
        } else if(kind.equals("find")) {

            return "/find_board/writeFindBoardForm";
        } else if(kind.equals("look")) {

            return "/look_board/writeLookBoardForm";
        }
        return null;
    }

    @GetMapping("/board/modify")
    public String boardModify(@RequestParam("kindOfBoard") String kind, @RequestParam("pk") int pk, Model model) {
        if(kind.equals("자유") || kind.equals("문의")) {
            BoardModifyForm bmForm = boardService.getBoardModify(pk);

            int mNumber = boardService.getBoard(pk).getMNumber();
            bmForm.setMNumber(mNumber);
            bmForm.setBNumber(pk);

            model.addAttribute("modReq", bmForm);

            return "/board/modifyForm";
        } else if (kind.equals("입양후기")) {
            AdoptReviewModifyForm armForm = adoptReviewService.getAdoptReviewModify(pk);

            int mNumber = adoptReviewService.getAdoptReview(pk).getMNumber();
            armForm.setMNumber(mNumber);
            armForm.setBNumber(pk);

            model.addAttribute("modReq", armForm);

            return "/adopt_review/modifyAdoptReviewForm";
        } else if(kind.equals("find")) {
            FindBoardModifyForm fmForm = findBoardService.getModifyForm(pk);

            int mNumber = findBoardService.getFindBoard(pk).getMNumber();
            fmForm.setMNumber(mNumber);
            fmForm.setFaNumber(pk);

            model.addAttribute("findMod", fmForm);

            return "/find_board/modifyFindBoardForm";
        } else if(kind.equals("look")) {
            LookBoardModifyForm lmForm = lookBoardService.getModifyForm(pk);

            int mNumber = lookBoardService.getLookBoard(pk).getMNumber();
            lmForm.setMNumber(mNumber);
            lmForm.setLaNumber(pk);

            model.addAttribute("lookMod", lmForm);

            return "/look_board/modifyLookBoardForm";
        }

        return null;
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("kindOfBoard") String kind, @RequestParam("pk") int pk, RedirectAttributes redirectAttributes) {
        if(kind.equals("자유") || kind.equals("문의")) {
            boardService.delete(pk);
        } else if (kind.equals("입양후기")) {
            adoptReviewService.delete(pk);
        } else if(kind.equals("find")) {
            findBoardService.delete(pk);
        } else if(kind.equals("look")) {
            lookBoardService.delete(pk);
        }

        redirectAttributes.addAttribute("kindOfBoard", kind);

        return "redirect:/admin/board?kindOfBoard={kindOfBoard}";
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