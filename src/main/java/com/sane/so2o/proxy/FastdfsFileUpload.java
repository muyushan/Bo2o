package com.sane.so2o.proxy;

import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Set;

@Component("fastdfsFileUpload")
public class FastdfsFileUpload implements FileUpload {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private FdfsWebServer fdfsWebServer;
    @Override
    public String upload(byte[] buffers, String extName) {
        try{
            Set<MetaData> mataData = new HashSet<>();
            mataData.add(new MetaData("author", "muys"));
            mataData.add(new MetaData("description", "通过blog系统上传的文件"));
            StorePath path=fastFileStorageClient.uploadFile(new ByteArrayInputStream(buffers),buffers.length, extName, mataData);
            return fdfsWebServer.getWebServerUrl()+"/"+path.getFullPath();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();;
        }
        return "";
    }
}
