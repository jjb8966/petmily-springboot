package kh.petmily.mapper;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AbandonedAnimalMapper {

    // =======BasicMapper 메소드=======
    AbandonedAnimal selectByPk(int pk);

    void insert(AbandonedAnimal abandonedAnimal);

    void update(AbandonedAnimal abandonedAnimal);

    void delete(int pk);
    // =======BasicMapper 메소드=======

    int selectCount();

    List<AbandonedAnimal> selectIndex(@Param("start") int start, @Param("end") int end);

    String selectName(int pk);

    List<AbandonedAnimal> selectAll();

    int selectsNumber(int pk);
}
