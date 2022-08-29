package kh.petmily.service;

import kh.petmily.dao.LookBoardDao;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.admin.form.AdminBoardListForm;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LookBoardServiceImpl implements LookBoardService {
    private final LookBoardDao lookBoardDao;
    private final MemberDao memberDao;
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
        List<LookBoard> content = lookBoardDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        List<LookBoardListForm> liList = new ArrayList<>();

        for (LookBoard l : content) {
            LookBoardListForm li = new LookBoardListForm(l.getLaNumber(), findName(l.getMNumber()), l.getSpecies(), l.getKind(), l.getLocation(), l.getAnimalState(), l.getImgPath(), l.getWrTime(), l.getTitle());
            liList.add(li);
        }

        return new LookBoardPageForm(total, pageNo, size, liList);
    }

    @Override
    public LookBoardDetailForm getDetailForm(int laNumber) {
        LookBoard lookBoard = lookBoardDao.findByPk(laNumber);
        LookBoardDetailForm detailForm = toDetailForm(lookBoard);

        return detailForm;
    }

    private LookBoardDetailForm toDetailForm(LookBoard lookBoard) {
        return new LookBoardDetailForm(lookBoard.getLaNumber(), lookBoard.getMNumber(), findName(lookBoard.getMNumber()), lookBoard.getSpecies(), lookBoard.getKind(), lookBoard.getLocation(), lookBoard.getAnimalState(), lookBoard.getImgPath(), lookBoard.getWrTime(), lookBoard.getTitle(), lookBoard.getContent());
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
    public String findName(int mNumber) {
        return memberDao.selectName(mNumber);
    }

    @Override
    public LookBoardPageForm getMatchedLookPage(int pageNo, FindBoard findBoard) {
        int total = lookBoardDao.selectMatchedCount(findBoard);

        List<LookBoard> content = lookBoardDao.selectMatchedIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, findBoard);

        List<LookBoardListForm> liList = new ArrayList<>();

        for (LookBoard l : content) {
            LookBoardListForm li = new LookBoardListForm(l.getLaNumber(), findName(l.getMNumber()), l.getSpecies(), l.getKind(), l.getLocation(), l.getAnimalState(), l.getImgPath(), l.getWrTime(), l.getTitle());
            liList.add(li);
        }

        return new LookBoardPageForm(total, pageNo, size, liList);
    }

    @Override
    public List<AdminBoardListForm> selectAll() {
        List<AdminBoardListForm> list = new ArrayList<>();

        List<LookBoard> lookList = lookBoardDao.selectAll();

        for(LookBoard l : lookList) {
            AdminBoardListForm ad = new AdminBoardListForm(l.getLaNumber(), findName(l.getMNumber()), l.getWrTime(), l.getTitle());
            list.add(ad);
        }

        return list;
    }

    @Override
    public LookBoard getLookBoard(int laNumber) {
        return lookBoardDao.findByPk(laNumber);
    }
}