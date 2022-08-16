package kh.petmily.service;

import kh.petmily.dao.BoardDao;
import kh.petmily.domain.board.Board;
import kh.petmily.domain.board.form.BoardPage;
import kh.petmily.domain.board.form.ReadBoardForm;
import kh.petmily.domain.board.form.WriteBoardForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private int size = 5;

    @Override
    public BoardPage getBoardPage(int pageNum, String kindOfBoard) {
        int total = boardDao.selectCount(kindOfBoard);
        List<ReadBoardForm> content = boardDao.selectIndex((pageNum - 1) * size + 1, (pageNum - 1) * size + size, kindOfBoard);

        return new BoardPage(total, pageNum, size, content);
    }

    @Override
    public void write(WriteBoardForm writeBoardForm) {
        Board board = toBoard(writeBoardForm);
        boardDao.insert(board);
    }

    @Override
    public ReadBoardForm getBoard(int bNumber) {
        Board readBoardForm = boardDao.findByPk(bNumber);

        return new ReadBoardForm(
                readBoardForm.getBNumber(),
                readBoardForm.getMNumber(),
                readBoardForm.getName(),
                readBoardForm.getKindOfBoard(),
                readBoardForm.getTitle(),
                readBoardForm.getContent(),
                readBoardForm.getWrTime(),
                readBoardForm.getCheckPublic()
        );
    }

    @Override
    public BoardModifyForm getBoardModify(int bNumber) {
        Board board = boardDao.findByPk(bNumber);
        BoardModifyForm modReq =toBoardModify(board);
        return modReq;
    }

    @Override
    public void modify(BoardModifyForm modReq) {
        Board board = toBoardModifyForm(modReq);
        boardDao.update(board);
    }

    @Override
    public void delete(int bNumber) {
        boardDao.delete(bNumber);
    }

    private Board toBoard(WriteBoardForm req) {
        return new Board(
                req.getmNumber(),
                req.getKindOfBoard(),
                req.getTitle(),
                req.getContent(),
                req.getCheckPublic());
    }

    private Board toBoardModifyForm(BoardModifyForm modReq){
        return new Board(modReq.getBNumber(), modReq.getTitle(), modReq.getContent(), modReq.getCheckPublic());
    }

    private BoardModifyForm toBoardModify(Board board){
        return new BoardModifyForm(board.getBNumber(), board.getTitle(), board.getContent(), board.getCheckPublic());
    }
}
