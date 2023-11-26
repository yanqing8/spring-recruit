package cn.atcat.controller;

import cn.atcat.pojo.PageBean;
import cn.atcat.pojo.Recruit;
import cn.atcat.pojo.Result;
import cn.atcat.service.RecruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruit")
@Slf4j
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    // 新增招聘或求职信息
    @PostMapping
    public Result add(@RequestBody @Validated(Recruit.Add.class) Recruit recruit) {
        log.info("新增招聘获求职信息：{}", recruit);
        recruitService.add(recruit);
        return Result.success();
    }

    // 查询招聘或求职信息列表
    @GetMapping
    public Result<PageBean<Recruit>> getList(Integer pageNum,
                                    Integer pageSize,
                                    @RequestParam(required = false) Integer categoryId,
                                    @RequestParam(required = false) Integer state) {
        log.info("查询招聘获求职信息列表：pageNum={}, pageSize={}, categoryId={}, state={}", pageNum, pageSize, categoryId, state);
        PageBean<Recruit> pageBean = recruitService.getList(pageNum, pageSize, categoryId, state);
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
    public Result update(@RequestBody @Validated(Recruit.Update.class) Recruit recruit){
        log.info("更新招聘获求职信息，{}", recruit);
        recruitService.update(recruit);
        return Result.success();
    }

    // 更新审核状态
    @PutMapping("/state")
    public Result updateState(@RequestParam() List<Integer> ids, Integer state){
        log.info("更新招聘获求职信息审核状态，ids={}, state={}", ids, state);
        recruitService.updateState(ids, state);
        return Result.success();
    }

    // 删除招聘或求职信息
    @DeleteMapping
    public Result delete(Integer id){
        log.info("删除招聘获求职信息，{}", id);
        recruitService.delete(id);
        return Result.success();
    }

}
