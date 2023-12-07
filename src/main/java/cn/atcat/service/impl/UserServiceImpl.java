package cn.atcat.service.impl;

import cn.atcat.mapper.CategoryMapper;
import cn.atcat.mapper.GoodsMapper;
import cn.atcat.mapper.RecruitMapper;
import cn.atcat.mapper.UserMapper;
import cn.atcat.pojo.Goods;
import cn.atcat.pojo.PageBean;
import cn.atcat.pojo.User;
import cn.atcat.service.UserService;
import cn.atcat.utils.Md5Util;
import cn.atcat.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RecruitMapper recruitMapper;

    @Override
    public User findUserByUsername(String username) {
        User u = userMapper.findUserByUsername(username);
        return u;
    }

    @Override
    public void register(String username, String password, Integer role) {
        // 加密处理
        String md5String = Md5Util.getMD5String(password);
        // 添加
        User u = new User();
        u.setUsername(username);
        u.setPassword(md5String);
        u.setRole(role);
        u.setCreateTime(LocalDateTime.now());
        u.setUpdateTime(LocalDateTime.now());
        userMapper.add(u);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map =  ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map =  ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), id);
    }

    @Override
    public PageBean<User> userList(Integer pageNum, Integer pageSize, Integer role, String otherParam) {
        // 创建pageBean对象
        PageBean<User> pageBean = new PageBean<>();
        // 开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 调用Mapper查询
        List<User> us = userMapper.userList(role, otherParam);
        Page<User> p = (Page<User>) us;

        // 把数据填充到PageBean中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        // 先删除用户有关的所有信息
        // 删除用户创建的分类
        categoryMapper.deleteCategoryByUserId(ids);
        // 删除用户创建的招聘或求职信息
        recruitMapper.deleteRecruitByUserId(ids);
        // 删除用户
        userMapper.delete(ids);
    }

    @Override
    public void add(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.add(user);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void resetPwd(Integer id) {
        userMapper.updatePwd(Md5Util.getMD5String("123456"), id);
    }
}
