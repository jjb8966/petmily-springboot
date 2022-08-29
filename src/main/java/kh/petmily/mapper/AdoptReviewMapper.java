package kh.petmily.mapper;

import kh.petmily.domain.adopt_review.AdoptReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdoptReviewMapper {

    // =======BasicMapper 메소드=======
    AdoptReview selectByPk(int pk);

    void insert(AdoptReview board);

    void update(AdoptReview board);

    void delete(int pk);
    // =======BasicMapper 메소드=======

    int selectCount(String kindOfBoard);

    List<AdoptReview> selectIndex(@Param("start") int start, @Param("end") int end, @Param("kindOfBoard") String kindOfBoard);

    String selectName(int pk);

    List<AdoptReview> selectAll(String kindOfBoard);
}