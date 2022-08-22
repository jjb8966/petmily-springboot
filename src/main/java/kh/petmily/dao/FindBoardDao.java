package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.FindBoardListForm;
import kh.petmily.mapper.FindBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FindBoardDao implements BasicDao {
    private final FindBoardMapper mapper;

    @Override
    public FindBoard findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((FindBoard) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((FindBoard) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    public int selectCount(String species, String animalState, String keyword) {
        return mapper.selectCountWithCondition(species, animalState, keyword);
    }

    public List<FindBoardListForm> selectIndex(int start, int end, String species, String animalState, String keyword) {
        List<FindBoardListForm> fiList = new ArrayList<>();

        List<FindBoard> findBoards = mapper.selectIndexWithCondition(start, end, species, animalState, keyword);

        for (FindBoard board : findBoards) {
            FindBoardListForm fi = new FindBoardListForm(board.getFaNumber(), selectName(board.getFaNumber()), board.getSpecies(), board.getKind(), board.getLocation(), board.getAnimalState(), board.getImgPath(), board.getWrTime(), board.getTitle(), board.getViewCount());
            fiList.add(fi);
        }

        return fiList;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    // 조회수
    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }
}