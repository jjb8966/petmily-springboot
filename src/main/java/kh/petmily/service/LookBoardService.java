package kh.petmily.service;

import kh.petmily.domain.admin.form.AdminBoardListForm;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.LookBoardDetailForm;
import kh.petmily.domain.look_board.form.LookBoardModifyForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardWriteForm;

import java.util.List;

public interface LookBoardService {
    public void write(LookBoardWriteForm lwForm);

    public void modify(LookBoardModifyForm lmForm);

    public void delete(int laNumber);

    public LookBoardPageForm getLookPage(int pageNo);

    public LookBoardDetailForm getDetailForm(int laNumber);

    public LookBoardModifyForm getModifyForm(int laNumber);

    public String findName(int laNumber);

    public LookBoardPageForm getMatchedLookPage(int pageNo, FindBoard findBoard);

    public List<AdminBoardListForm> selectAll();

    public LookBoard getLookBoard(int laNumber);
}