package com.burning8393.template.biz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * FileUploadController class
 *
 * @author : Pangxw
 * @date : 2019/2/26 14:19
 * @description :
 */
@RestController
@Slf4j
public class FileUploadController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "上传文件不能为空";
        }
        String contentType = file.getContentType();
        String fileName = file.getName();
        log.info("服务器文件名：" + fileName);
        String originalFileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        file.transferTo(new File("d://burning8393-" + originalFileName));
        return String.format(file.getClass().getName() + " 文件上传成功！ \n 文件名：%s，文件类型:%s, 文件大小： %s", originalFileName, contentType, fileSize);
    }
}
