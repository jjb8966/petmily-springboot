package kh.petmily.mapper;

import kh.petmily.domain.adopt.Adopt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdoptMapper {
    Adopt selectByPk(int pk);

    void insert(Adopt obj);

    void update(Adopt obj);
    
    void delete(int pk);
}
