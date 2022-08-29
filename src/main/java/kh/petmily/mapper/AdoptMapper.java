package kh.petmily.mapper;

import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdoptTempListForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AdoptMapper {
    Adopt selectByPk(int pk);

    void insert(Adopt obj);

    void update(Adopt obj);
    
    void delete(int pk);

    int selectCount();

    List<AdoptTempListForm> selectIndex(@Param("start") int start, @Param("end") int end, @Param("status") String status);

    List<AdoptTempListForm> adoptApprove(int pk);

    List<AdoptTempListForm> adoptRefuse(int pk);
}
