package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.LookBoardListForm;
import kh.petmily.mapper.LookBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LookBoardDao implements BasicDao {

    private final LookBoardMapper mapper;

    @Override
    public LookBoard findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((LookBoard) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((LookBoard) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    public int selectCount(String species, String animalState, String keyword) {
        return mapper.selectCountWithCondition(species, animalState, keyword);
    }

    public List<LookBoardListForm> selectIndex(int start, int end, String species, String animalState, String keyword) {
        List<LookBoardListForm> result = new ArrayList<>();

        List<LookBoard> lookBoards = mapper.selectIndexWithCondition(start, end, species, animalState, keyword);

        for (LookBoard board : lookBoards) {
            // ====== 조회수 추가된 부분 ======
            LookBoardListForm li = new LookBoardListForm(board.getLaNumber(), selectName(board.getLaNumber()), board.getSpecies(), board.getKind(), board.getLocation(), board.getAnimalState(), board.getImgPath(), board.getWrTime(), board.getTitle(), board.getViewCount());
            result.add(li);
        }

        return result;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    // ====== 조회수 추가 ======
    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }

}