package cn.atcat.mapper;

import cn.atcat.pojo.Recruit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitMapper {
    void add(Recruit recruit);
    List<Recruit> list(Integer type, Integer userId, Integer categoryId, Integer state, Integer sortOrd, String otherParam);

    Recruit getById(Integer id);

    void update(Recruit recruit);

    void updateState(List<Integer> ids, Integer state);


    void deleteRecruitByUserId(List<Integer> ids);

    void delete(List<Integer> ids);
}
