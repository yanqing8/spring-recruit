package cn.atcat.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {
    private Integer id;
    private String name;
    private String level;
    private BigDecimal price;
    private String createTime;
    private String updateTime;
}
