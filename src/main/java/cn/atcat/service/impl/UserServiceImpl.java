package cn.atcat.service.impl;

import cn.atcat.mapper.UserMapper;
import cn.atcat.pojo.User;
import cn.atcat.service.UserService;
import cn.atcat.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
