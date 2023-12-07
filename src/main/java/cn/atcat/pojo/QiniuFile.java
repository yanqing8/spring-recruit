package cn.atcat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiniuFile {
    private  String url;
    private  String fileName;
    private  String originalName;
}
