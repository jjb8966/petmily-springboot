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


    private Board toBoard(WriteBoardForm req) {
        return new Board(
                req.getmNumber(),
                req.getKindOfBoard(),
                req.getTitle(),
                req.getContent(),
                req.getCheckPublic());
    }
}

