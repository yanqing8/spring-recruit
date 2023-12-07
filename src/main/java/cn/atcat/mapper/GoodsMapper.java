package cn.atcat.mapper;


import cn.atcat.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("select * from goods where id = #{goodsId}")
    Goods selectById(Integer goodsId);

    @Select("select * from goods")
    List<Goods> getGoodsList();
}
