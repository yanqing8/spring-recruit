package cn.atcat.controller;

import cn.atcat.pojo.PageBean;
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
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserService userService;

     @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        claims.put("role", loginUser.getRole());
        String token = JwtUtil.genToken(claims);
        // 把token存入redis
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        operations.set(token, token, 60, TimeUnit.MINUTES);
        // 把user+id作为key，token作为value存入redis
        operations.set("userToken" + loginUser.getId(), token, 60, TimeUnit.MINUTES);
        return Result.success(token);
    }

    // 获取用户信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");

        User user = userService.findUserByUsername(username);
        return Result.success(user);
    }

    @GetMapping("/userList")
    public Result<PageBean<User>> userList(Integer pageNum, Integer pageSize,
                                     @RequestParam(required = false) Integer role,
                                     @RequestParam(required = false) String otherParam){
        log.info("查询用户列表：pageNum={}, pageSize={}, role={}, otherParam={}", pageNum, pageSize, role, otherParam);
        PageBean<User> userList = userService.userList(pageNum, pageSize, role, otherParam);
        return Result.success(userList);
    }

    // 修改用户信息
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        log.info("修改用户信息：{}", user);
        userService.update(user);
        return Result.success();
    }

    // 修改头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@URL String avatarUrl){
        log.info("修改头像：{}", avatarUrl);
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    // 更新用户密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params){
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String rePwd = params.get("rePwd");
        log.info("修改密码：{},{},{}", oldPwd, newPwd, rePwd);
        // 校验参数
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("参数错误");
        }
        if(!newPwd.equals(rePwd)) return Result.error("两次密码不一致");
        // 校验原密码是否正确
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findUserByUsername(username);
        if(!Md5Util.checkPassword(oldPwd, user.getPassword())) return Result.error("原密码错误");

        userService.updatePwd(newPwd);
        // 删除原来的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete("userToken" + user.getId());
        return Result.success();
    }

    // 删除用户
    @DeleteMapping
    public Result delete(@RequestParam() List<Integer> ids){
        log.info("删除用户：{}", ids);
        userService.delete(ids);
        return Result.success();
    }

    // 管理员新增用户
    @PostMapping("/add")
    public Result add(@RequestBody Map<String, String> params) {
        log.info("新增用户：{},", params);
        // 获取当前用户角色
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userRole = (Integer) map.get("role");
        if (userRole != 0) return Result.error("权限不足");

        // 从map中获取以下数据，设置到user中
        String username = params.get("username");
        String nickname = params.get("nickname");
        String email = params.get("email");
        // 校验参数
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(params.get("role"))) {
            return Result.error("参数错误");
        }
        Integer role = Integer.parseInt(params.get("role"));

        // 创建用户对象
        User user = new User();
        // 判断params中是否有id，有则是修改，没有则是新增
        if (params.get("id") != null) {
            Integer id = Integer.parseInt((String) params.get("id"));
            // 校验用户名是否已经占用
            User existingUser = userService.findUserByUsername(username);
            if (existingUser != null && existingUser.getId() != id) return Result.error("用户名已被占用");

            user = userService.findUserById(id);
            user.setUsername(username);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setRole(role);

            userService.update(user);
        } else {
            // 校验用户名是否存在
            User existingUser = userService.findUserByUsername(username);
            if (existingUser != null) return Result.error("用户名已被占用");

            user.setPassword(Md5Util.getMD5String("123456"));
            user.setUsername(username);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setRole(role);

            userService.add(user);
        }
        return Result.success();
    }

    // 管理员重置用户密码
    @PatchMapping("/resetPwd")
    public Result resetPwd(Integer id){
        log.info("重置密码：{}", id);
        // 获取当前用户角色
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userRole = (Integer) map.get("role");
        if (userRole != 0) return Result.error("权限不足");

        // 重置密码
        userService.resetPwd(id);
        return Result.success();

    }

}
