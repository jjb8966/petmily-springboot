package kh.petmily.service;

import kh.petmily.domain.look_board.form.LookBoardDetailForm;
import kh.petmily.domain.look_board.form.LookBoardModifyForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardWriteForm;

public interface LookBoardService {
    public void write(LookBoardWriteForm lwForm);

    public void modify(LookBoardModifyForm lmForm);

    public void delete(int laNumber);

    public LookBoardPageForm getLookPage(int pageNo);

    public LookBoardDetailForm getDetailForm(int laNumber);

    public LookBoardModifyForm getModifyForm(int laNumber);

    String findName(int laNumber);

    public LookBoardPageForm getLookPageAsc(int pageNo);
}