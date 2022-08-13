package kh.petmily.service;

import kh.petmily.dao.BoardDao;
import kh.petmily.domain.board.form.BoardPage;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.ReadBoardForm;
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
        List<ReadBoardForm> content = boardDao.selectIndex((pageNum-1) * size + 1, (pageNum-1) * size + size, kindOfBoard);

        return new BoardPage(total, pageNum, size, content);
    }

    @Override
    public ReadBoardForm getBoard(int bNumber) {
        ReadBoardForm readBoardForm = boardDao.selectByContent(bNumber);

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
    public void delete(int bNumber) {
        boardDao.delete(bNumber);
    }

}