package kh.petmily.service;

import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.FindBoardModifyForm;
import kh.petmily.domain.find_board.form.FindBoardPageForm;
import kh.petmily.domain.find_board.form.FindBoardDetailForm;
import kh.petmily.domain.find_board.form.FindBoardWriteForm;

public interface FindBoardService {
    public void write(FindBoardWriteForm fwForm);

    public void modify(FindBoardModifyForm fwForm);

    public void delete(int faNumber);

    public FindBoardDetailForm getDetailForm(int faNumber);

    public FindBoardModifyForm getModifyForm(int faNumber);

    public FindBoardPageForm getFindPage(int pageNo);

    public FindBoardPageForm getMembersFindPage(int pageNo, int mNumber, String matched);

    String findName(int faNumber);

    FindBoard getFindBoard(int faNumber);
}