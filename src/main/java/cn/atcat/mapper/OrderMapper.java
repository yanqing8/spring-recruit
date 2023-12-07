package cn.atcat.mapper;

import cn.atcat.pojo.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into orders(goods_id, user_id, name, order_id, state, total, create_time) values(#{goodsId}, #{userId} ,#{name}, #{orderId}, #{state}, #{total}, #{createTime})")
    void insert(Orders orders);
    
    @Select("select * from orders where order_id = #{outTradeNo}")
    Orders selectByOrderId(String outTradeNo);

    @Update("update orders set  alipay_no=#{alipayNo} ,pay_time = #{payTime} ,state = #{state} where id = #{id}")
    void updateById(Orders orders);

    Orders selectByGoodsIdAndState(Integer goodsId, String state,Integer userId);

    List<Orders> list(String orderId, String state);

    void updateState(List<Integer> ids, String state);
}
