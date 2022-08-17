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

    List<LookBoard> selectIndex(@Param("start") int start, @Param("end") int end);

    String selectName(int pk);

    // ====== 조회수 추가 ======
    int updateViewCount(int pk);

    List<LookBoard> selectByAsc(@Param("start") int start, @Param("end") int end);
}