package com.sane.so2o.proxy;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@Component("aliyunFileUpload")
public class AliyunOSSFileUpload implements FileUpload{
    @Value("${aliyun.oss.bucket}")
    private String aliyun_oss_bucket;
    @Value("${aliyun.oss.url}")
    private String aliyun_oss_url;
    @Autowired
    private OSS ossClient;
    @Override
    public String upload(byte[] buffers, String extName) {
        String realName=UUID.randomUUID().toString()+"."+extName;
        PutObjectResult result = ossClient.putObject(aliyun_oss_bucket, "blog/"+realName, new ByteArrayInputStream(buffers));
        if (!StringUtils.isEmpty(result.getETag())) {
            return aliyun_oss_url+"/blog/"+realName;
        } else {
           return "";
        }
    }
}
