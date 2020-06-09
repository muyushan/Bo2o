package com.sane.so2o.util;

public class PathUtil {

    private static  final  String seperator=System.getProperty("file.separator");
    public static String getImageStoreBasePath(){

        String osName=System.getProperty("os.name");
        String userName=System.getProperty("user.name");
        String basePath="";
        if(osName.toLowerCase().startsWith("win")){
            basePath="D:/blog/";
        }else{
            basePath="/Users/"+userName+"/blog/";
        }

        basePath=basePath.replace("/", seperator);
        return  basePath;
    }

    public static String  getUserImagePath(long userId){
        String imagePath="/upload/item/user/"+userId+"/";
        return imagePath.replaceAll("/",seperator);
    }
}
