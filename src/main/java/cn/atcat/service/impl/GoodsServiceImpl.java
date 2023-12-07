package cn.atcat.service.impl;

import cn.atcat.mapper.GoodsMapper;
import cn.atcat.pojo.Goods;
import cn.atcat.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getGoodsList() {
        List<Goods> list = goodsMapper.getGoodsList();
        return list;
    }
}
