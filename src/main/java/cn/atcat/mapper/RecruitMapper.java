package cn.atcat.mapper;

import cn.atcat.pojo.Recruit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecruitMapper {
    void add(Recruit recruit);

    List<Recruit> list(Integer categoryId, Integer state, Integer userId);

    Recruit getById(Integer id);

    void update(Recruit recruit);

    @Delete("delete from recruit_info where id = #{id}")
    void delete(Integer id);

    void updateState(List<Integer> ids, Integer state);
}
