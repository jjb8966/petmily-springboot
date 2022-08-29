package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.adopt.form.AdoptApplyPageForm;
import kh.petmily.domain.temp.form.TempApplyPageForm;

public interface AdoptTempService {
    public void adopt(AdoptTempSubmitForm adoptTempSubmitForm);

    public void tempProtect(AdoptTempSubmitForm adoptTempSubmitForm);

    AdoptApplyPageForm getAdoptApplyPage(int pageNo, int mNumber, String type);

    TempApplyPageForm getTempApplyPage(int pageNo, int mNumber, String type);
}