package cn.atcat.service.impl;

import cn.atcat.mapper.RecruitMapper;
import cn.atcat.pojo.PageBean;
import cn.atcat.pojo.Recruit;
import cn.atcat.service.RecruitService;
import cn.atcat.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitMapper recruitMapper;
    @Override
    public void add(Recruit recruit) {
        // 获取用户Id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        recruit.setState(0); // 待审核
        recruit.setCreateUser(userId);
        recruit.setCreateTime(LocalDateTime.now());
        recruit.setUpdateTime(LocalDateTime.now());
        recruitMapper.add(recruit);
    }

    // 前台查询招聘或求职信息列表，不需要传入userId


    @Override
    public PageBean<Recruit> getList(Integer pageNum, Integer pageSize, Integer type, Integer userId, Integer categoryId, Integer state, Integer sortOrd, String otherParam) {
        // 创建pageBean对象
        PageBean<Recruit> pageBean = new PageBean<>();
        // 开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 调用Mapper查询
        List<Recruit> rs = recruitMapper.list(type,userId,categoryId, state,sortOrd,otherParam);
        Page<Recruit> p = (Page<Recruit>) rs;

        // 把数据填充到PageBean中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    @Override
    public Recruit getById(Integer id) {
        Recruit recruit = recruitMapper.getById(id);
        return recruit;
    }

    @Override
    public void update(Recruit recruit) {
        recruit.setUpdateTime(LocalDateTime.now());
        recruitMapper.update(recruit);
    }
    @Override
    public void updateState(List<Integer> ids, Integer state) {
        recruitMapper.updateState(ids, state);
    }

    @Override
    public void delete(List<Integer> ids) {
        recruitMapper.delete(ids);
    }
}
