package kh.petmily.service;

import kh.petmily.domain.look_board.form.LookBoardDetailForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardWriteForm;

public interface LookBoardService {
    public void write(LookBoardWriteForm lwForm);

    public LookBoardPageForm getLookPage(int pageNo);

    public LookBoardDetailForm getDetailForm(int laNumber);

    String findName(int laNumber);
}