package kh.petmily.service;

import kh.petmily.domain.board.form.BoardPage;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.ReadBoardForm;
import kh.petmily.domain.board.form.WriteBoardForm;

public interface BoardService {
    public BoardPage getBoardPage(int pbNumber, String kindOfBoard);

    public void write(WriteBoardForm writeBoardForm);

    public ReadBoardForm getBoard(int bNumber);

    public BoardModifyForm getBoardModify(int bNumber);

    public void modify(BoardModifyForm modReq);

    public void delete(int bNumber);

    String boardName(int bNumber);
}