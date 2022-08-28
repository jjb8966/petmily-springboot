package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;

import java.util.List;

public interface AbandonedAnimalService {

    public AbandonedAnimalDetailForm getDetailForm(int abNumber);

    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo);

    String findName(int abNumber);

    List<AbandonedAnimal> selectAll();
}
