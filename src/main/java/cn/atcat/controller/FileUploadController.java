package cn.atcat.controller;

import cn.atcat.pojo.Result;
import cn.atcat.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class FileUploadController {

    // 上传文件到阿里云OSS
//    @PostMapping("/upload")
//    public Result<String> upload(MultipartFile file) throws IOException {
//        // 获取文件名
//        String originalFilename = file.getOriginalFilename();
//        log.info("文件名:{}",originalFilename);
//        // 保证文件是唯一的，防止覆盖
//        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        String url = AliOssUtil.uploadFile(fileName, file.getInputStream());
//        return Result.success(url);
//    }

    // 上传文件到七牛云
    @Autowired
    private FileUploadService fileUploadService;
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("七牛云上传文件，文件名:{}", file.getOriginalFilename());
        if(file.isEmpty()) {
            return Result.error("文件不能为空");
        }
        String fileUrl = fileUploadService.upload(file);
        if(fileUrl == null) {
            return Result.error("上传失败");
        }log.info("七牛云上传文件，文件名:{} 上传成功", file.getOriginalFilename());
        return Result.success(fileUrl);
    }
}
