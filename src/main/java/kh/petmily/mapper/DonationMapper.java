package kh.petmily.mapper;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.donation.Donation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DonationMapper {

    // =======BasicMapper 메소드=======
    Donation selectByPk(int pk);

    void insert(Donation obj);

    void update(Donation obj);

    void delete(int pk);
    // =======BasicMapper 메소드=======

}
