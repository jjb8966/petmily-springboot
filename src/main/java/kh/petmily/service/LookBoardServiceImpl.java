package kh.petmily.service;

import kh.petmily.dao.LookBoardDao;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.*;
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
    public void modify(LookBoardModifyForm lmForm) {
        LookBoard lookBoard = toLookFromLM(lmForm);
        lookBoard.setLaNumber(lmForm.getLaNumber());
        lookBoardDao.update(lookBoard);
    }

    private LookBoard toLookFromLM(LookBoardModifyForm req) {
        return new LookBoard(req.getMNumber(), req.getSpecies(), req.getKind(), req.getLocation(), null, req.getTitle(), req.getContent());
    }

    @Override
    public void delete(int laNumber) {
        lookBoardDao.delete(laNumber);
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

    // ====== 조회수 추가된 부분 ======
    private LookBoardDetailForm toDetailForm(LookBoard lookBoard) {
        return new LookBoardDetailForm(lookBoard.getLaNumber(), lookBoard.getMNumber(), findName(lookBoard.getLaNumber()), lookBoard.getSpecies(), lookBoard.getKind(), lookBoard.getLocation(), lookBoard.getAnimalState(), lookBoard.getImgPath(), lookBoard.getWrTime(), lookBoard.getTitle(), lookBoard.getContent(), lookBoard.getViewCount());
    }

    @Override
    public LookBoardModifyForm getModifyForm(int laNumber) {
        LookBoard lookBoard = lookBoardDao.findByPk(laNumber);
        LookBoardModifyForm modifyForm = toModifyForm(lookBoard);

        return modifyForm;
    }

    private LookBoardModifyForm toModifyForm(LookBoard lookBoard) {
        return new LookBoardModifyForm(lookBoard.getSpecies(), lookBoard.getKind(), lookBoard.getLocation(), lookBoard.getTitle(), lookBoard.getContent());
    }

    @Override
    public String findName(int laNumber) {
        return lookBoardDao.selectName(laNumber);
    }

    //====== 조회수 추가 ======
    @Override
    public int updateViewCount(int laNumber) {
        return lookBoardDao.updateViewCount(laNumber);
    }
}