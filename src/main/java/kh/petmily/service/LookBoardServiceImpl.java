package kh.petmily.service;

import kh.petmily.dao.LookBoardDao;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.LookBoardDetailForm;
import kh.petmily.domain.look_board.form.LookBoardListForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardWriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LookBoardServiceImpl implements LookBoardService {
    private final LookBoardDao lookBoardDao;
    private int size = 6;

    @Override
    public void write(LookBoardWriteForm lwForm) {
        LookBoard lookBoard = toLookFromLW(lwForm);
        lookBoardDao.insert(lookBoard);
    }

    private LookBoard toLookFromLW(LookBoardWriteForm req) {
        return new LookBoard(req.getMNumber(), req.getSpecies(), req.getKind(), req.getLocation(), null, req.getTitle(), req.getContent());
    }

    @Override
    public LookBoardPageForm getLookPage(int pageNo) {
        int total = lookBoardDao.selectCount();
        List<LookBoardListForm> content = lookBoardDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new LookBoardPageForm(total, pageNo, size, content);
    }

    @Override
    public LookBoardDetailForm getDetailForm(int laNumber) {
        LookBoard lookBoard = lookBoardDao.findByPk(laNumber);
        LookBoardDetailForm detailForm = toDetailForm(lookBoard);

        return detailForm;
    }

    private LookBoardDetailForm toDetailForm(LookBoard lookBoard) {
        return new LookBoardDetailForm(lookBoard.getLaNumber(), lookBoard.getMNumber(), findName(lookBoard.getLaNumber()), lookBoard.getSpecies(), lookBoard.getKind(), lookBoard.getLocation(), lookBoard.getAnimalState(), lookBoard.getImgPath(), lookBoard.getWrTime(), lookBoard.getTitle(), lookBoard.getContent());
    }

    @Override
    public String findName(int laNumber) {
        return lookBoardDao.selectName(laNumber);
    }
}