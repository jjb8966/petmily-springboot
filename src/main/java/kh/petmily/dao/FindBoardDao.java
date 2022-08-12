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

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<FindBoardListForm> selectIndex(int start, int end) {
        List<FindBoard> list = mapper.selectIndex(start, end);
        List<FindBoardListForm> fiList = new ArrayList<>();

        for (FindBoard f : list) {
            FindBoardListForm fi = new FindBoardListForm(f.getFaNumber(), selectName(f.getFaNumber()), f.getSpecies(), f.getKind(), f.getLocation(), f.getAnimalState(), f.getImgPath(), f.getWrTime(), f.getTitle());
            fiList.add(fi);
        }

        return fiList;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }
}