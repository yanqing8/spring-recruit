package cn.atcat.service.impl;

import cn.atcat.common.QiniuConfig;
import cn.atcat.service.FileUploadService;
import cn.atcat.utils.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private QiniuConfig qiniuConfig;
    private String ACCESS_KEY;
    private String SECREt_KEY;
    private String BUCKET_NAME;
    private String QINIU_IMAGE_DOMAIN;
    // 密钥配置
    Auth auth;
    Configuration cfg;
    UploadManager uploadManager;
    @PostConstruct
    public void init() {
        ACCESS_KEY = qiniuConfig.getAccessKey();
        SECREt_KEY = qiniuConfig.getSecretKey();
        BUCKET_NAME = qiniuConfig.getBucketName();
        QINIU_IMAGE_DOMAIN = qiniuConfig.getPath();
        auth = Auth.create(ACCESS_KEY, SECREt_KEY);
        cfg = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(cfg);
    }

    public String getUpToken(){
        return auth.uploadToken(BUCKET_NAME);
    }
    @Override
    public String upload(MultipartFile file) {
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            // 判断是否是合法的文件后缀
            if (!FileUtil.isFileAllowed(fileExt)) {
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                log.info("七牛异常:" + res.bodyString());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
