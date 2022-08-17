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

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<LookBoardListForm> selectIndex(int start, int end) {
        List<LookBoard> list = mapper.selectIndex(start, end);
        List<LookBoardListForm> liList = new ArrayList<>();

        for (LookBoard l : list) {
            // ====== 조회수 추가된 부분 ======
            LookBoardListForm li = new LookBoardListForm(l.getLaNumber(), selectName(l.getLaNumber()), l.getSpecies(), l.getKind(), l.getLocation(), l.getAnimalState(), l.getImgPath(), l.getWrTime(), l.getTitle(), l.getViewCount());
            liList.add(li);
        }

        return liList;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    // ====== 조회수 추가 ======
    public int updateViewCount(int pk) { return mapper.updateViewCount(pk); }
}