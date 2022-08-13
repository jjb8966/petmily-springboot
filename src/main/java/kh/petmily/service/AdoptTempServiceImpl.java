package kh.petmily.service;

import kh.petmily.dao.AdoptDao;
import kh.petmily.dao.TempDao;
import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.temp.TempPet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdoptTempServiceImpl implements AdoptTempService{

    private final AdoptDao adoptDao;
    private final TempDao tempDao;

    @Override
    public void adopt(AdoptTempSubmitForm adoptTempSubmitForm) {
        Adopt adopt = toAdopt(adoptTempSubmitForm);
        adoptDao.insert(adopt);
    }

    @Override
    public void tempProtect(AdoptTempSubmitForm adoptTempSubmitForm) {
        TempPet tempPet = toTempPet(adoptTempSubmitForm);
        tempDao.insert(tempPet);
    }

    @Override
    public String findAnimalName(int abNumber) {
        return null;
    }

    @Override
    public String findMemberName(int mNumber) {
        return null;
    }

    private Adopt toAdopt(AdoptTempSubmitForm adoptTempSubmitForm) {
        return new Adopt(adoptTempSubmitForm.getMNumber(), adoptTempSubmitForm.getAbNumber(),
                adoptTempSubmitForm.getResidence(), adoptTempSubmitForm.getMaritalStatus(),
                adoptTempSubmitForm.getJob());
    }

    private TempPet toTempPet(AdoptTempSubmitForm adoptTempSubmitForm) {
        return new TempPet(adoptTempSubmitForm.getAbNumber(), adoptTempSubmitForm.getMNumber(),
                adoptTempSubmitForm.getResidence(), adoptTempSubmitForm.getMaritalStatus(),
                adoptTempSubmitForm.getJob());
    }
}
