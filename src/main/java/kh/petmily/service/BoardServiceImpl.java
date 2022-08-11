package kh.petmily.service;

import kh.petmily.dao.BoardDao;
import kh.petmily.domain.board.form.BoardPage;
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




/*
    public void modify(ModifyRequest modReq) {

            ReadBoardForm readBoardForm = boardDao.selectByContent(modReq.getbNumber());

            if (readBoardForm == null) {
                throw new BoardNotFoundException();
            }

            if (!canModify(modReq.getmNumber(), readBoardForm)) {
                throw new PermissionDeniedException();
            }

            boardDao.update(
                    conn,
                    modReq.getbNumber(),
                    modReq.getTitle(),
                    modReq.getContent(),
                    modReq.getCheckPublic());
    }

    private boolean canModify(int mNumber, ReadBoardForm readBoardForm) {
        return readBoardForm.getmNumber() == mNumber;
    }

    private int size = 5;

    public BoardPage getBoardPage(int pageNum, String kindOfBoard) {

            int total = boardDao.selectCount(conn, kindOfBoard);
            List<ReadBoardForm> content = boardDao.select(conn, (pageNum - 1) * size, size, kindOfBoard);
    }

    public void delete(int bNumber, User authUser) {

            ReadBoardForm readBoardForm = boardDao.selectByContent(bNumber);

            if (readBoardForm == null) {
                throw new BoardNotFoundException();
            }

            if (!canModify(authUser.getmNumber(), readBoardForm)) {
                throw new PermissionDeniedException();
            }

            boardDao.delete(bNumber);

    }
*/
}
