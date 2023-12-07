package cn.atcat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Recruit {
    @NotNull(groups = Update.class)
    private Integer id;// 职位信息表的id
    @NotEmpty
    @Pattern(regexp = "^\\S{2,20}$",message = "标题格式不正确")
    private String title;// 岗位标题
    @NotEmpty
    @Pattern(regexp = "^\\S{2,20}$",message = "地址格式不正确")
    private String address;// 岗位地址
    @NotNull
    private Integer payMin; // 最低薪资，单位是k
    @NotNull
    private Integer payMax; // 最高薪资，单位是k
    @NotEmpty
    @Pattern(regexp = "^\\S{2,20}$",message = "公司名称格式不正确")
    private String company;// 公司名称
    private List<String> condition;// 岗位条件，默认是"不限"，以数组转为字符串的形式存储到数据库
    private String content;// 岗位的详细要求，富文本
    private List<String> tag;// 岗位的标签，以数组转为字符串的形式存储到数据库
    @NotNull(groups = UpdateState.class)
    @Max(value = 2,message = "审核状态值不正确",groups = UpdateState.class)
    @Min(value = 0,message = "审核状态值不正确",groups = UpdateState.class)
    private Integer state;// 0，1，2三个值分别表示待审核，审核通过、审核不通过
    @NotEmpty
    @URL
    private String imgUrl;// 岗位信息封面图
    @NotNull(groups = Add.class)
    @Max(value = 1,message = "类型值不正确",groups = Add.class)
    @Min(value = 0,message = "类型值不正确",groups = Add.class)
    private Integer type;// 0，1分别表示招聘信息和求职信息
    @NotNull
    private Integer categoryId;// 属于的类别
    private String categoryName;// 属于的类别名字
    private Integer createUser;// 创建的用户id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;// 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;// 更新时间

    public interface Add extends Default {

    }
    public interface Update extends Default {

    }

    public interface UpdateState extends Default {

    }

}