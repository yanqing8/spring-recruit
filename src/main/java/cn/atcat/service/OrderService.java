package cn.atcat.service;

import cn.atcat.pojo.Orders;
import cn.atcat.pojo.PageBean;

import java.util.List;

public interface OrderService {
    void create(Orders orders);

    Orders getOrders(Integer goodsId, String state);

    PageBean<Orders> getList(Integer pageNum, Integer pageSize, String orderId, String state);

    void updateState(List<Orders> ordersList, String state);
}
