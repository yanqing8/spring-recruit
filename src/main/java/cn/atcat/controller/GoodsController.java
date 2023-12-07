package cn.atcat.controller;

import cn.atcat.pojo.Goods;
import cn.atcat.pojo.Result;
import cn.atcat.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    // 获取商品列表
    @GetMapping("/list")
    public Result<List<Goods>> getGoodsList() {
        log.info("获取商品列表");
        List<Goods> list = goodsService.getGoodsList();
        return Result.success(list);
    }
}
