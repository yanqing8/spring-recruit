package cn.atcat.controller;

import cn.atcat.pojo.PageBean;
import cn.atcat.pojo.Recruit;
import cn.atcat.pojo.Result;
import cn.atcat.pojo.User;
import cn.atcat.service.RecruitService;
import cn.atcat.service.UserService;
import cn.atcat.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recruit")
@Slf4j
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    @Autowired
    private UserService userService;

    // 新增招聘或求职信息
    @PostMapping
    public Result add(@RequestBody @Validated(Recruit.Add.class) Recruit recruit) {
        log.info("新增招聘获求职信息：{}", recruit);
        // 获取用户id
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User u = userService.findUserByUsername(username);
        if (u.getLevel() < 2) {
            return Result.error("您的等级不够，无法发布招聘或求职信息");
        }
        recruitService.add(recruit);
        return Result.success();
    }

    // 查询招聘或求职信息列表

    /**
     * 查询招聘或求职信息列表
     *
     * @param pageNum
     * @param pageSize
     * @param type       类型 0，1分别表示招聘信息和求职信息
     * @param userId     用户id
     * @param categoryId 分类id
     * @param state      状态
     * @param sortOrd    排序方式 0,1,分别是最新发布、薪资最高
     * @param otherParam 其他参数
     * @return
     */
    @GetMapping("/list")
    public Result<PageBean<Recruit>> getList(Integer pageNum, Integer pageSize, Integer type,
                                             @RequestParam(required = false) Integer userId,
                                             @RequestParam(required = false) Integer categoryId,
                                             @RequestParam(required = false) Integer state,
                                             @RequestParam(required = false) Integer sortOrd,
                                             @RequestParam(required = false) String otherParam) {
        log.info("查询招聘获求职信息列表：pageNum={}, pageSize={}, type={}, userId={}, categoryId={}, state={}, sortOrd={}, otherParam={}",
                pageNum, pageSize, type, userId, categoryId, state, sortOrd, otherParam);
        PageBean<Recruit> pageBean = recruitService.getList(pageNum, pageSize, type, userId, categoryId, state, sortOrd, otherParam);
        return Result.success(pageBean);
    }

    // 通过id查询招聘或求职信息详情
    @GetMapping("/detail")
    public Result<Recruit> detail(Integer id) {
        log.info("查询招聘获求职信息详情：{}", id);
        Recruit recruit = recruitService.getById(id);
        return Result.success(recruit);
    }

    // 更新招聘或求职信息
    @PutMapping
    public Result update(@RequestBody @Validated(Recruit.Update.class) Recruit recruit) {
        log.info("更新招聘获求职信息，{}", recruit);
        recruitService.update(recruit);
        return Result.success();
    }

    // 更新审核状态
    @PutMapping("/state")
    public Result updateState(@RequestParam() List<Integer> ids, Integer state) {
        log.info("更新招聘获求职信息审核状态，ids={}, state={}", ids, state);
        recruitService.updateState(ids, state);
        return Result.success();
    }

    // 删除招聘或求职信息
    @DeleteMapping
    public Result delete(@RequestParam() List<Integer> ids) {
        log.info("删除招聘获求职信息，{}", ids);
        recruitService.delete(ids);
        return Result.success();
    }

}
