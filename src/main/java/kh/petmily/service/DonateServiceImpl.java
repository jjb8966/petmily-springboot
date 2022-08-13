package kh.petmily.service;

import kh.petmily.dao.AbandonedAnimalDao;
import kh.petmily.dao.DonationDao;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.abandoned_animal.form.DonateSubmitForm;
import kh.petmily.domain.donation.Donation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DonateServiceImpl implements DonateService{

    private final DonationDao donationDao;
    private final AbandonedAnimalDao abandonedAnimalDao;
    private final MemberDao memberDao;

    @Override
    public void donate(DonateSubmitForm donateSubmitForm) {
        Donation donation = toDonation(donateSubmitForm);
        log.info("donation = {}", donation);

        donationDao.insert(donation);
    }

    @Override
    public String findAnimalName(int abNumber) {
        String animalName = abandonedAnimalDao.selectName(abNumber);

        return animalName;
    }

    @Override
    public String findMemberName(int mNumber) {
        String memberName = memberDao.selectName(mNumber);

        return memberName;
    }

    private static Donation toDonation(DonateSubmitForm donateSubmitForm) {
        Donation donation = new Donation(
                donateSubmitForm.getAbNumber(),
                donateSubmitForm.getMNumber(),
                donateSubmitForm.getDonaSum(),
                donateSubmitForm.getBank(),
                donateSubmitForm.getAccountHolder(),
                donateSubmitForm.getAccountNumber());
        return donation;
    }
}
