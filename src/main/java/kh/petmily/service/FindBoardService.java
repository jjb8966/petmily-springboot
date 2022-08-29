package kh.petmily.service;

import kh.petmily.domain.admin.form.AdminBoardListForm;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.FindBoardModifyForm;
import kh.petmily.domain.find_board.form.FindBoardPageForm;
import kh.petmily.domain.find_board.form.FindBoardDetailForm;
import kh.petmily.domain.find_board.form.FindBoardWriteForm;

import java.util.List;

public interface FindBoardService {
    public void write(FindBoardWriteForm fwForm);

    public void modify(FindBoardModifyForm fwForm);

    public void delete(int faNumber);

    public FindBoardDetailForm getDetailForm(int faNumber);

    public FindBoardModifyForm getModifyForm(int faNumber);

    public FindBoardPageForm getFindPage(int pageNo);

    public FindBoardPageForm getMembersFindPage(int pageNo, int mNumber, String matched);

    public String findName(int mNumber);

    public FindBoard getFindBoard(int faNumber);

    public List<AdminBoardListForm> selectAll();
}