package kh.petmily.service;

import kh.petmily.dao.AbandonedAnimalDao;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.pet.Pet;
import kh.petmily.domain.pet.form.PetPageForm;
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
    public PetPageForm getPetPage(int pageNum) {
        int total = abandonedAnimalDao.selectPetCount();
        List<Pet> content = abandonedAnimalDao.selectPetIndex((pageNum - 1) * size + 1, (pageNum - 1) * size + size);
        System.out.println("content = " + content);
        return new PetPageForm(total, pageNum, size, content);
    }

    @Override
    public String findName(int abNumber) {
        return abandonedAnimalDao.selectName(abNumber);
    }

    @Override
    public void savePet(Pet pet) {
        abandonedAnimalDao.insertPet(pet);
    }

    @Override
    public void modifyPet(Pet pet) {
        abandonedAnimalDao.updatePet(pet);
    }

    @Override
    public void deletePet(int cpNumber) {
        abandonedAnimalDao.deletePet(cpNumber);
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
