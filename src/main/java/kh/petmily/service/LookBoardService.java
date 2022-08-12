package kh.petmily.service;

import kh.petmily.domain.look_board.form.LookBoardDetailForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;

public interface LookBoardService {
    public LookBoardPageForm getLookPage(int pageNo);

    public LookBoardDetailForm getDetailForm(int laNumber);

    String findName(int laNumber);
}