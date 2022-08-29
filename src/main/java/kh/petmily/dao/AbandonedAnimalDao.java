package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.mapper.AbandonedAnimalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AbandonedAnimalDao implements BasicDao {

    private final AbandonedAnimalMapper mapper;

    // =======BasicDao 메소드=======
    @Override
    public AbandonedAnimal findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((AbandonedAnimal) obj);
    }

    @Override
    public void update(DomainObj obj) { mapper.update((AbandonedAnimal) obj); }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }
    // =======BasicDao 메소드=======

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<AbandonedAnimalDetailForm> selectIndex(int start, int end) {
        List<AbandonedAnimal> list = mapper.selectIndex(start, end);
        List<AbandonedAnimalDetailForm> abandonedAnimalDetailForm = new ArrayList<>();

        for (AbandonedAnimal a : list) {
            AbandonedAnimalDetailForm ab = new AbandonedAnimalDetailForm(a.getAbNumber(), a.getSNumber(), a.getAge(), a.getWeight(), a.getGender(), a.getName(), a.getSpecies(), a.getKind(), a.getLocation(), a.getUniqueness(), a.getDescription(), a.getAnimalState(), a.getImgPath(), a.getAdmissionDate());
            abandonedAnimalDetailForm.add(ab);
        }

        return abandonedAnimalDetailForm;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public int selectsNumber(int pk) {
        return mapper.selectsNumber(pk);
    }
}
