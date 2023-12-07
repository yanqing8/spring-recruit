package cn.atcat.controller;

import cn.atcat.pojo.Orders;
import cn.atcat.pojo.PageBean;
import cn.atcat.pojo.Result;
import cn.atcat.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    // 下单，生成订单

    @Autowired
    private OrderService orderService;

    /**
     * 下单 创建订单
     * @param orders
     * @return
     */
    @PostMapping("/create")
    public Result<Orders> create(@RequestBody Orders orders){
        log.info("下单：{}", orders);
        orderService.create(orders);
        Orders o = orderService.getOrders(orders.getGoodsId(), "待支付");
        return Result.success(o);
    }

    @GetMapping
    public Result<Orders> getOrders(Integer goodsId,
                                    @RequestParam(required = false) String state){
        log.info("查询订单：goodsId={}, state={}", goodsId, state);
        Orders orders = orderService.getOrders(goodsId, state);
        return Result.success(orders);
    }

    @GetMapping("/list")
    public Result<PageBean<Orders>> getList(Integer pageNum, Integer pageSize,
                                            @RequestParam(required = false) String orderId,
                                            @RequestParam(required = false) String state){
        log.info("查询订单列表：pageNum={}, pageSize={}, orderId={}, state={}",pageNum, pageSize, orderId, state);
        PageBean<Orders> pageBean = orderService.getList(pageNum, pageSize, orderId, state);
        return Result.success(pageBean);
    }

    // 更新状态
    @PutMapping("/state")
    public Result updateState(@RequestBody List<Orders> ordersList, String state){
        log.info("更新订单状态：ordersList={}, state={}", ordersList, state);
        orderService.updateState(ordersList, state);
        return Result.success();
    }

}
