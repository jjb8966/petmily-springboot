package kh.petmily.service;

import kh.petmily.dao.AbandonedAnimalDao;
import kh.petmily.dao.DonationDao;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.abandoned_animal.form.DonateSubmitForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

public interface DonateService {

    public void donate(DonateSubmitForm donateSubmitForm);

    public String findAnimalName(int abNumber);

    public String findMemberName(int mNumber);
}
