package com.zs.util;


import com.sun.imageio.plugins.common.ImageUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/25
 * @Time:15:26
 */

public class ImgUtil {
    private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r=new Random();
    private static Logger logger=LoggerFactory.getLogger(com.sun.imageio.plugins.common.ImageUtil.class);



    /**
     * 将CommonMultipartFile转化为File
     * @param cFile
     * @return
     */

    public static File transferCommonMultipartFileToFile(CommonsMultipartFile cFile){
        File newFile=new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        }catch (IllegalStateException e){
            logger.error(e.toString());
            e.printStackTrace();
        }

        catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;

    }

    /**

     * 处理缩略图，并返回新生成图片的相对值路径
     * @param fileName
     * @param targetAddr
     * @return

     */

    public  static  String generateThumbnail(InputStream thumbnailInputStream,String fileName, String targetAddr){
        String realFileName=getRandomFileName();
        String extension=getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr=targetAddr +realFileName+extension;
        logger.debug("current relativeAddr is :"+relativeAddr);
        String  dest = PathUtil.getImgBasePath()+relativeAddr;
        logger.debug("current complete addr is:"+PathUtil.getImgBasePath()+relativeAddr);
        try{
            Thumbnails.of(thumbnailInputStream).size(200,200)
                   .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f)
                    .outputQuality(0.8f).toFile(dest);

        }catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return  relativeAddr;

    }



    /**

     * 创建目标路径所涉及到的目录，即/home/work/xiangze/xx.jpg,
     * 那么home work xiangze这三个文件夹都得自动创建
     * @param targetAddr
     */

    private static void makeDirPath(String targetAddr) {
        String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
        File dirPath=new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }



    /**

     * 获取文件流的扩展名
     * @param fileName
     * @return
     */

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }



    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     * @return
     */

    public static String getRandomFileName() {
        //获取随机的五位数
        int rannum=r.nextInt(89999)+10000;
        String nowTimeStr=sDateFormat.format(new Date());
        return nowTimeStr+rannum;
    }



    public static void main(String[] args) throws IOException {
        InputStream is=new FileInputStream(new File("D:\\O2OIMG\\pppppp\\123.jpg"));
        Thumbnails.of(is)
                .size(200,200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f).outputQuality(0.8f)
                .toFile("D:\\O2OIMG\\pppppp\\xiongmao.jpg");
    }



    /**
     * storePath是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除该文件，
     * 如果storePath是目录路径则山车该目录下的所有文件
     * @param storePath
     */

    public static void deleteFileOrPatn(String storePath){
        File fileOrPath=new File(PathUtil.getImgBasePath()+storePath);
        if(fileOrPath.exists()){
            File files[]=fileOrPath.listFiles();
            for(int i=0;i<files.length;i++){
                files[i].delete();
            }
        }
        fileOrPath.delete();
    }


}
