package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.*;
import org.springframework.web.multipart.MultipartFile;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.pet.Pet;
import kh.petmily.domain.pet.form.PetPageForm;

import java.io.IOException;
import java.util.List;

import java.util.List;

public interface AbandonedAnimalService {

    public AbandonedAnimalDetailForm getDetailForm(int abNumber);

    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo);

    PetPageForm getPetPage(int pageNo);

    String findName(int abNumber);

    List<AbandonedAnimal> selectAll();

    public void write(AbandonedAnimalWriteForm abandonedAnimalWriteForm);

    public AbandonedAnimalModifyForm getAbandonedModify(int abNumber);

    public void modify(AbandonedAnimalModifyForm abandonedAnimalModifyForm);

    public String storeFile(MultipartFile file, String filePath) throws IOException;

    public void delete(int abNumber);

    void savePet(Pet pet);

    void modifyPet(Pet pet);

    void deletePet(int cpNumber);
}
