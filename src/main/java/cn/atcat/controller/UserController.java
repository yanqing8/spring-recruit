package cn.atcat.controller;

import cn.atcat.pojo.Result;
import cn.atcat.pojo.User;
import cn.atcat.service.UserService;
import cn.atcat.utils.JwtUtil;
import cn.atcat.utils.Md5Util;
import cn.atcat.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    // 注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$",message = "用户名5~16位非空字符") String username,
                           @Pattern(regexp = "^\\S{5,16}$",message = "密码5~16位非空字符") String password,
                           @Pattern(regexp = "^\\S{5,16}$",message = "密码5~16位非空字符") String rePassword,
                           @Min(value = 1) @Max(value = 2) Integer role){
        log.info("注册：username:{}, password:{}, rePassword:{}, role:{}", username, password, rePassword, role);
        User u = userService.findUserByUsername(username);
        if(u != null) return Result.error("用户已存在");
        if(!password.equals(rePassword)) return Result.error("两次密码不一致");

        userService.register(username, password, role);
        return Result.success();
    }

    // 登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$",message = "用户名5~16位非空字符") String username,
                                @Pattern(regexp = "^\\S{5,16}$",message = "密码5~16位非空字符") String password){
        log.info("登录：username:{}, password:{}", username, password);
        User loginUser = userService.findUserByUsername(username);
        if(loginUser == null) return Result.error("用户不存在");
        if(!Md5Util.checkPassword(password, loginUser.getPassword())) return Result.error("密码错误");

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginUser.getId());
        claims.put("username", loginUser.getUsername());
        String token = JwtUtil.genToken(claims);
        // 把token存入redis
        return Result.success(token);
    }

    // 获取用户信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");

        User user = userService.findUserByUsername(username);
        return Result.success(user);
    }

}
