package kh.petmily.mapper;

import kh.petmily.domain.adopt.Adopt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdoptMapper {
    Adopt selectByPk(int pk);

    void insert(Adopt obj);

    void update(Adopt obj);
    
    void delete(int pk);

    int selectCount(int mNumber);

    List<Adopt> selectIndex(@Param("start") int start, @Param("end") int end, @Param("mNumber") int mNumber);
}