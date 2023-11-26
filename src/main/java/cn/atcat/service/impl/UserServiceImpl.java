package cn.atcat.service.impl;

import cn.atcat.mapper.UserMapper;
import cn.atcat.pojo.User;
import cn.atcat.service.UserService;
import cn.atcat.utils.Md5Util;
import cn.atcat.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
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
        userMapper.add(username, md5String, role);
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
    public void pay(Integer lv) {
        // 等级+lv
        Map<String,Object> map =  ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateLv(lv, id);
    }
}
