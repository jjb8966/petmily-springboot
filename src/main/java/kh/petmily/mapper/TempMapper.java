package kh.petmily.mapper;

import kh.petmily.domain.temp.TempPet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TempMapper {

    TempPet selectByPk(int pk);

    void insert(TempPet obj);

    void update(TempPet obj);

    void delete(int pk);
}
