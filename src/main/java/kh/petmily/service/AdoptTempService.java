package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;

public interface AdoptTempService {

    public void adopt(AdoptTempSubmitForm adoptTempSubmitForm);

    public void tempProtect(AdoptTempSubmitForm adoptTempSubmitForm);

    public String findAnimalName(int abNumber);

    public String findMemberName(int mNumber);
}
