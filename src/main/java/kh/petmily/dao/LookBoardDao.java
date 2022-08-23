package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.LookBoardListForm;
import kh.petmily.mapper.FindBoardMapper;
import kh.petmily.mapper.LookBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LookBoardDao implements BasicDao {
    private final LookBoardMapper mapper;
    private final FindBoardMapper findBoardMapper;

    @Override
    public LookBoard findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        LookBoard lookBoard = (LookBoard) obj;

        List<Integer> list = mapper.selectMatchedFa(lookBoard);
        log.info("insert : list = {}", list);

        if (list.size() != 0) {
            mapper.insert(lookBoard);

            int laNumber = mapper.selectByPkMax();
            log.info("insert : MAX laNumber = {}", laNumber);

            mapper.changeState(laNumber);

            for (Integer i : list) {
                mapper.changeStateFind(i);
            }
        } else {
            mapper.insert(lookBoard);
        }
    }

    @Override
    public void update(DomainObj obj) {
        LookBoard lookBoard = (LookBoard) obj;

        mapper.backState(lookBoard.getLaNumber());

        LookBoard old_lookBoard = mapper.selectByPk(lookBoard.getLaNumber());
        List<Integer> old_list = mapper.selectMatchedFa(old_lookBoard);
        log.info("update : old_list = {}", old_list);

        if(old_list.size() != 0) {
            for(Integer i : old_list) {
                mapper.backStateFind(i);
            }
        }

        List<Integer> list = mapper.selectMatchedFa(lookBoard);
        log.info("update : list = {}", list);

        if (list.size() != 0) {
            mapper.update(lookBoard);
            mapper.changeState(lookBoard.getLaNumber());

            for (Integer i : list) {
                mapper.changeStateFind(i);
            }
        } else {
            mapper.update(lookBoard);
        }
    }

    @Override
    public void delete(int pk) {
        LookBoard lookBoard = mapper.selectByPk(pk);
        List<Integer> list = mapper.selectMatchedFa(lookBoard);
        log.info("delete : list = {}", list);

        if(list.size() != 0) {
            for(Integer i : list) {
                mapper.backStateFind(i);
            }
        }

        mapper.delete(pk);
    }

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<LookBoardListForm> selectIndex(int start, int end) {
        List<LookBoard> list = mapper.selectIndex(start, end);
        List<LookBoardListForm> liList = new ArrayList<>();

        for (LookBoard l : list) {
            LookBoardListForm li = new LookBoardListForm(l.getLaNumber(), selectName(l.getLaNumber()), l.getSpecies(), l.getKind(), l.getLocation(), l.getAnimalState(), l.getImgPath(), l.getWrTime(), l.getTitle());
            liList.add(li);
        }

        return liList;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public int selectMatchedCount(FindBoard findBoard) {
        List<Integer> list = findBoardMapper.selectMatchedLa(findBoard);
        return list.size();
    }

    public List<LookBoardListForm> selectMatchedIndex(int start, int end, FindBoard findBoard) {
        List<Integer> list = findBoardMapper.selectMatchedLa(findBoard);
        List<LookBoard> lookBoardList = new ArrayList<>();
        for(Integer i : list) {
            lookBoardList.add(mapper.selectByPk(i));

        }
        List<LookBoardListForm> liList = new ArrayList<>();

        for (LookBoard l : lookBoardList) {
            LookBoardListForm li = new LookBoardListForm(l.getLaNumber(), selectName(l.getLaNumber()), l.getSpecies(), l.getKind(), l.getLocation(), l.getAnimalState(), l.getImgPath(), l.getWrTime(), l.getTitle());
            liList.add(li);
        }

        return liList;
    }
}