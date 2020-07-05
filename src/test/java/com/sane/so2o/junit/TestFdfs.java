package com.sane.so2o.junit;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.domain.MetaData;
import com.sane.so2o.BaseTest;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class TestFdfs extends BaseTest {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    public void testFdfs() throws FileNotFoundException {
        Set<MetaData> mataData = new HashSet<>();
        mataData.add(new MetaData("author", "zonghui"));
        mataData.add(new MetaData("description", "xxx文件，嘿嘿嘿"));

        File file=new File("/Users/lixiuli/Downloads/IMG_4185.jpg");
        InputStream inputStream=new FileInputStream(file);

        // 上传   （文件上传可不填文件信息，填入null即可）
        try{
//            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), mataData);
//            System.out.println(storePath.getFullPath());
//            System.out.println(storePath.getGroup());
//            System.out.println(storePath.getPath());
            StorePath path=fastFileStorageClient.uploadImageAndCrtThumbImage(inputStream,file.length(),FilenameUtils.getExtension(file.getName()), mataData);
            System.out.println(path.getFullPath());
            System.out.println(path.getGroup());
            System.out.println(path.getPath());
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();;
        }

//        return storePath;
    }
}
