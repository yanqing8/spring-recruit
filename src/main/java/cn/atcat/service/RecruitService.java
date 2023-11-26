package cn.atcat.service;

import cn.atcat.pojo.PageBean;
import cn.atcat.pojo.Recruit;

import java.util.List;

public interface RecruitService {
    void add(Recruit recruit);

    PageBean<Recruit> getList(Integer pageNum, Integer pageSize, Integer categoryId, Integer state);

    Recruit getById(Integer id);

    void update(Recruit recruit);

    void delete(Integer id);

    void updateState(List<Integer> ids, Integer state);
}
