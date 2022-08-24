package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdoptMemberApplyListForm;
import kh.petmily.mapper.AbandonedAnimalMapper;
import kh.petmily.mapper.AdoptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdoptDao implements BasicDao {
    private final AdoptMapper mapper;
    private final AbandonedAnimalMapper abandonedAnimalMapper;

    @Override
    public Adopt findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((Adopt) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((Adopt) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    public int selectCount(int mNumber) {
        return mapper.selectCount(mNumber);
    }

    public List<AdoptMemberApplyListForm> selectIndex(int start, int end, int mNumber) {
        List<Adopt> list = mapper.selectIndex(start, end, mNumber);
        List<AdoptMemberApplyListForm> maList = new ArrayList<>();

        for(Adopt a : list) {
            AdoptMemberApplyListForm ma = new AdoptMemberApplyListForm(a.getAdNumber(), getAbNameByAbNumber(a.getAbNumber()), a.getStatus());
            maList.add(ma);
        }

        return maList;
    }

    private String getAbNameByAbNumber(int abNumber) {
        return abandonedAnimalMapper.selectName(abNumber);
    }
}
