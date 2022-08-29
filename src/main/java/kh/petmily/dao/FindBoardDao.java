package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.mapper.FindBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FindBoardDao implements BasicDao {
    private final FindBoardMapper mapper;

    @Override
    public FindBoard findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        FindBoard findBoard = (FindBoard) obj;

        List<Integer> list = mapper.selectMatchedLa(findBoard);
        log.info("insert : list = {}", list);

        if (list.size() != 0) {
            mapper.insert(findBoard);

            int laNumber = mapper.selectByPkMax();
            log.info("insert : MAX laNumber = {}", laNumber);

            mapper.changeState(laNumber);

            for (Integer i : list) {
                mapper.changeStateLook(i);
            }
        } else {
            mapper.insert(findBoard);
        }
    }

    @Override
    public void update(DomainObj obj) {
        FindBoard findBoard = (FindBoard) obj;

        mapper.backState(findBoard.getFaNumber());

        FindBoard old_findBoard = mapper.selectByPk(findBoard.getFaNumber());
        List<Integer> old_list = mapper.selectMatchedLa(old_findBoard);
        log.info("update : old_list = {}", old_list);

        if(old_list.size() != 0) {
            for(Integer i : old_list) {
                mapper.backStateLook(i);
            }
        }

        List<Integer> list = mapper.selectMatchedLa(findBoard);
        log.info("update : list = {}", list);

        if (list.size() != 0) {
            mapper.update(findBoard);
            mapper.changeState(findBoard.getFaNumber());

            for (Integer i : list) {
                mapper.changeStateLook(i);
            }
        } else {
            mapper.update(findBoard);
        }
    }

    @Override
    public void delete(int pk) {
        FindBoard findBoard = mapper.selectByPk(pk);
        List<Integer> list = mapper.selectMatchedLa(findBoard);
        log.info("delete : list = {}", list);

        if(list.size() != 0) {
            for(Integer i : list) {
                mapper.backStateLook(i);
            }
        }

        mapper.delete(pk);
    }

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<FindBoard> selectIndex(int start, int end) {
        return mapper.selectIndex(start, end);
    }

    public int selectMemberCount(int mNumber, String matched) {
        return mapper.selectMemberCount(mNumber, matched);
    }

    public List<FindBoard> selectMemberIndex(int start, int end, int mNumber, String matched) {
        return mapper.selectMemberIndex(start, end, mNumber, matched);
    }

    public List<FindBoard> selectAll() {
        return mapper.selectAll();
    }
}