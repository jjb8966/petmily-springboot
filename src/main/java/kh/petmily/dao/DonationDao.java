package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.donation.Donation;
import kh.petmily.mapper.DonationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DonationDao implements BasicDao {

    private final DonationMapper mapper;

    // =======BasicDao 메소드=======
    @Override
    public Donation findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((Donation) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((Donation) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }
    // =======BasicDao 메소드=======
}
