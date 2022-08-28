package kh.petmily.service;

import kh.petmily.dao.AbandonedAnimalDao;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbandonedAnimalServiceImpl implements AbandonedAnimalService {

    private final AbandonedAnimalDao abandonedAnimalDao;
    private int size = 12;

    @Override
    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo) {
        int total = abandonedAnimalDao.selectCount();
        List<AbandonedAnimal> content = abandonedAnimalDao.selectIndex((pageNo - 1) * size, size);

        return new AbandonedAnimalPageForm(total, pageNo, size, content);
    }

    @Override
    public String findName(int abNumber) {
        return abandonedAnimalDao.selectName(abNumber);
    }

    @Override
    public List<AbandonedAnimal> selectAll() {
        return abandonedAnimalDao.selectAll();
    }

    @Override
    public AbandonedAnimalDetailForm getDetailForm(int abNumber) {
        AbandonedAnimal findABAnimal = abandonedAnimalDao.findByPk(abNumber);
        AbandonedAnimalDetailForm detailForm = toDetailForm(findABAnimal);

        return detailForm;
    }

    private AbandonedAnimalDetailForm toDetailForm(AbandonedAnimal domain) {
        return new AbandonedAnimalDetailForm(
                domain.getAbNumber(),
                domain.getSNumber(),
                domain.getAge(),
                domain.getWeight(),
                domain.getGender(),
                domain.getName(),
                domain.getSpecies(),
                domain.getKind(),
                domain.getLocation(),
                domain.getUniqueness(),
                domain.getDescription(),
                domain.getAnimalState(),
                domain.getImgPath(),
                domain.getAdmissionDate());
    }
}
