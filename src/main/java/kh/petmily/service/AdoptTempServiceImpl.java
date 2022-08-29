package kh.petmily.service;

import kh.petmily.dao.AdoptDao;
import kh.petmily.dao.TempDao;
import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdoptDetailForm;
import kh.petmily.domain.adopt.form.AdoptPageForm;
import kh.petmily.domain.adopt.form.TempDetailForm;
import kh.petmily.domain.adopt.form.TempPageForm;
import kh.petmily.domain.temp.TempPet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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

    @Override
    public String findAnimalName(int abNumber) {
        return adoptDao.selectAnimalName(abNumber);
    }

    @Override
    public String findMemberName(int mNumber) {
        return adoptDao.selectMemberName(mNumber);
    }

    @Override
    public String findMemberId(int mNumber) {
        return adoptDao.selectMemberId(mNumber);
    }

    public Adopt findByPk(int adNumber) {
        return adoptDao.findByPk(adNumber);
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

//    ======== 관리자 페이지 ========

    @Override
    public AdoptPageForm getAdoptWaitPage(int pageNo, String status) {
        int total = adoptDao.selectCount();
        List<AdoptDetailForm> content = adoptDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, status);

        return new AdoptPageForm(total, pageNo, size, content);
    }

    @Override
    public TempPageForm getTempWaitPage(int pageNo, String status) {
        int total = tempDao.selectCount();
        List<TempDetailForm> content = tempDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, status);

        return new TempPageForm(total, pageNo, size, content);
    }

    @Override
    public List<AdoptDetailForm> adoptApprove(int adNumber) {
        return adoptDao.adoptApprove(adNumber);
    }

    @Override
    public List<AdoptDetailForm> adoptRefuse(int adNumber) {
        return adoptDao.adoptRefuse(adNumber);
    }

    @Override
    public List<TempDetailForm> tempApprove(int tNumber) {
        return tempDao.tempApprove(tNumber);
    }

    @Override
    public List<TempDetailForm> tempRefuse(int tNumber) {
        return tempDao.tempRefuse(tNumber);
    }
}
