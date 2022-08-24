package kh.petmily.service;

import kh.petmily.dao.AdoptDao;
import kh.petmily.dao.TempDao;
import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdoptApplyPageForm;
import kh.petmily.domain.adopt.form.AdoptMemberApplyListForm;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.domain.temp.form.TempApplyPageForm;
import kh.petmily.domain.temp.form.TempMemberApplyListForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdoptTempServiceImpl implements AdoptTempService{
    private final AdoptDao adoptDao;
    private final TempDao tempDao;

    private int size = 10;

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

    @Override
    public AdoptApplyPageForm getAdoptApplyPage(int pageNo, int mNumber, String type) {
        int total = adoptDao.selectCount(mNumber);
        List<AdoptMemberApplyListForm> content = adoptDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new AdoptApplyPageForm(total, pageNo, size, content);
    }

    @Override
    public TempApplyPageForm getTempApplyPage(int pageNo, int mNumber, String type) {
        int total = tempDao.selectCount(mNumber);
        List<TempMemberApplyListForm> content = tempDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new TempApplyPageForm(total, pageNo, size, content);
    }
}