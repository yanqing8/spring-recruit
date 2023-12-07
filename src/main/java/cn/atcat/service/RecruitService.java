package cn.atcat.service;

import cn.atcat.pojo.PageBean;
import cn.atcat.pojo.Recruit;

import java.util.List;

public interface RecruitService {
    void add(Recruit recruit);

    PageBean<Recruit> getList(Integer pageNum, Integer pageSize, Integer type, Integer userId, Integer categoryId, Integer state, Integer sortOrd, String otherParam);

    Recruit getById(Integer id);

    void update(Recruit recruit);
    

    void updateState(List<Integer> ids, Integer state);

    void delete(List<Integer> ids);
}