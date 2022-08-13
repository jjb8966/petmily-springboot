package kh.petmily.service;

import kh.petmily.domain.board.form.BoardPage;
import kh.petmily.domain.board.form.ModifyRequest;
import kh.petmily.domain.board.form.ReadBoardForm;
import kh.petmily.domain.board.form.WriteBoardForm;

public interface BoardService {
    public BoardPage getBoardPage(int pbNumber, String kindOfBoard);

    public void write(WriteBoardForm writeBoardForm);
}
