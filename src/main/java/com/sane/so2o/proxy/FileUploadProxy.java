package com.sane.so2o.proxy;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
@ConfigurationProperties(prefix = "upload")
public class FileUploadProxy implements ApplicationContextAware {
    private Map<String, List<String>>fileMap;
    private ApplicationContext applicationContext;
    public String upload(MultipartFile multipartFile) throws IOException {
        byte[]buffer=multipartFile.getBytes();
        String fileName=multipartFile.getOriginalFilename();
        String extName= FilenameUtils.getExtension(fileName);
        FileUpload fileUpload=null;
        for(Map.Entry<String,List<String>> entry:fileMap.entrySet()){
            if(entry.getValue().contains(extName)){
                fileUpload =applicationContext.getBean(entry.getKey(),FileUpload.class);
                break;
            }
        }
        Assert.notNull(fileUpload,"系统不支持扩展名为："+extName+"的文件上传");
        String filePath=fileUpload.upload(buffer,extName);
        return filePath;
    }


    public void setFileMap(Map<String, List<String>> fileMap) {
        this.fileMap = fileMap;
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
