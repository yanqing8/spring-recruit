package cn.atcat.service.impl;

import cn.atcat.mapper.GoodsMapper;
import cn.atcat.mapper.OrderMapper;
import cn.atcat.mapper.UserMapper;
import cn.atcat.pojo.Goods;
import cn.atcat.pojo.Orders;
import cn.atcat.pojo.PageBean;
import cn.atcat.service.OrderService;
import cn.atcat.utils.SnowFlake;
import cn.atcat.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void create(Orders orders) {
        // 根据商品id查询价格，以防止用户篡改价格
        Goods goods = goodsMapper.selectById(orders.getGoodsId());
        orders.setTotal(goods.getPrice());
        // 生成订单号
        String orderId = String.valueOf(SnowFlake.nextId());
        orders.setOrderId(orderId);
        // 设置状态
        orders.setState("待支付");
        orders.setName(goods.getName());
        //设置创建时间
        orders.setCreateTime(LocalDateTime.now());
        // 查询用户的id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        orders.setUserId(userId);
        // 插入订单
        orderMapper.insert(orders);
    }

    @Override
    public Orders getOrders(Integer goodsId, String state) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Orders orders = orderMapper.selectByGoodsIdAndState(goodsId, state,userId);
        return orders;
    }

    @Override
    public PageBean<Orders> getList(Integer pageNum, Integer pageSize, String orderId, String state) {
        // 创建pageBean对象
        PageBean<Orders> pageBean = new PageBean<>();
        // 开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 调用Mapper查询
        List<Orders> os = orderMapper.list(orderId, state);
        Page<Orders> p = (Page<Orders>) os;

        // 把数据填充到PageBean中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    @Override
    public void updateState(List<Orders> ordersList, String state) {
        if(state.equals("已通过")){
            for(Orders orders : ordersList){
                // 查询商品
                Goods goods = goodsMapper.selectById(orders.getGoodsId());
                Integer lv = 0;
                if(goods.getLevel().equals("vip")){
                    lv = 2;
                }else if(goods.getLevel().equals("svip")) {
                    lv = 3;
                }
                userMapper.updateLv(orders.getUserId(), lv);
            }
        }
        // 把订单id取出来
        List<Integer> ids = ordersList.stream()
                                .map(Orders::getId)
                                .collect(Collectors.toList());
        orderMapper.updateState(ids, state);
    }
}
