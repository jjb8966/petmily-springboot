package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.temp.form.TempMemberApplyListForm;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.mapper.AbandonedAnimalMapper;
import kh.petmily.mapper.TempMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TempDao implements BasicDao{

    private final TempMapper mapper;
    private final AbandonedAnimalMapper abandonedAnimalMapper;

    @Override
    public TempPet findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((TempPet) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((TempPet) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    public int selectCount(int mNumber) {
        return mapper.selectCount(mNumber);
    }

    public List<TempMemberApplyListForm> selectIndex(int start, int end, int mNumber) {
        List<TempPet> list = mapper.selectIndex(start, end, mNumber);
        List<TempMemberApplyListForm> maList = new ArrayList<>();

        for(TempPet t : list) {
            TempMemberApplyListForm ma = new TempMemberApplyListForm(t.getTNumber(), getAbNameByAbNumber(t.getAbNumber()), t.getStatus());
            maList.add(ma);
        }

        return maList;
    }

    private String getAbNameByAbNumber(int abNumber) {
        return abandonedAnimalMapper.selectName(abNumber);
    }
}
