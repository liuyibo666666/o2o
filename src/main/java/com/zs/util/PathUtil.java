package com.zs.util;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/25
 * @Time:17:51
 */
//"D:\\IDEAProject\\o2o\\src\\main\\resources\\th[6].jpg"
public class PathUtil {

    private static String seperator=System.getProperty("file.separator");

    public static String getImgBasePath(){

        String os =System.getProperty("os.name");

        String basePath="";

        if (os.toLowerCase().startsWith("win")) {

            basePath="D:/O2OIMG/image/";

        }

        else {

            basePath="/home/xiangze/image/";

        }

        basePath=basePath.replace("/",seperator);

        return  basePath;

    }

    public  static String getShopImagePath(long shopId){

        String imagePath="upload/item/shop/"+shopId +"/";

        return imagePath.replace("/",seperator);

    }

}
