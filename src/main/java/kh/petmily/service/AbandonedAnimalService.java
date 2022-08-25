package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;

public interface AbandonedAnimalService {

    public AbandonedAnimalDetailForm getDetailForm(int abNumber);

    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo, String species, String gender, String animalState, String keyword);

    String findName(int abNumber);
}
