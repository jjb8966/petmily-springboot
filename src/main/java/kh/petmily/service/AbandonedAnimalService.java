package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.pet.Pet;
import kh.petmily.domain.pet.form.PetPageForm;

public interface AbandonedAnimalService {

    public AbandonedAnimalDetailForm getDetailForm(int abNumber);

    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo);

    PetPageForm getPetPage(int pageNo);

    String findName(int abNumber);

    void savePet(Pet pet);

    void modifyPet(Pet pet);

    void deletePet(int cpNumber);
}
