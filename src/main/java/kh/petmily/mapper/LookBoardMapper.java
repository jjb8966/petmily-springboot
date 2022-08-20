package kh.petmily.mapper;

import kh.petmily.domain.look_board.LookBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LookBoardMapper {
    LookBoard selectByPk(int pk);

    void insert(LookBoard obj);

    void update(LookBoard obj);

    void delete(int pk);

    int selectCount();

    int selectCountWithCondition(@Param("species") String species, @Param("animalState") String animalState, @Param("keyword") String keyword);

    List<LookBoard> selectIndex(@Param("start") int start, @Param("end") int end);

    List<LookBoard> selectIndexWithCondition(@Param("start") int start, @Param("end") int end, @Param("species") String species, @Param("animalState") String animalState, @Param("keyword") String keyword);

    String selectName(int pk);

    // ====== 조회수 추가 ======
    int updateViewCount(int pk);
}