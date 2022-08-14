package kh.petmily.service;

import kh.petmily.domain.board.form.BoardPage;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.ReadBoardForm;

public interface BoardService {
    public BoardPage getBoardPage(int pbNumber, String kindOfBoard);

    public ReadBoardForm getBoard(int bNumber);

    public BoardModifyForm getBoardModify(int bNumber);

    public void modify(BoardModifyForm modReq);

    public void delete(int bNumber);
}
