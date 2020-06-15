package com.sane.so2o.config;

import com.aliyun.oss.*;
import com.aliyun.oss.common.comm.Protocol;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class OSSClientConfig {
    @Value("${aliyun.oss.AccessKeyID}")
    private String aliyun_oss_key;
    @Value("${aliyun.oss.AccessSecurity}")
    private String aliyun_oss_sec;
    @Value("${aliyun.oss.endpoint}")
    private String aliyun_oss_endpoint;
    @Bean
    public OSS generagetOSSClient(){
        OSS ossClient = new OSSClientBuilder().build(aliyun_oss_endpoint, aliyun_oss_key, aliyun_oss_sec);
        ClientConfiguration ossConfig=new ClientBuilderConfiguration();
        ossConfig.setProtocol(Protocol.HTTPS);
        return ossClient;
    }

    public void UploadAFile(MultipartFile multipartFile){

    }
}
