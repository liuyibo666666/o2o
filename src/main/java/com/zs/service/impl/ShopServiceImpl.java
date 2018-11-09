package com.zs.service.impl;

import com.sun.imageio.plugins.common.ImageUtil;
import com.zs.dao.ShopDao;
import com.zs.dto.ShopExecution;
import com.zs.entity.Shop;
import com.zs.enums.ShopStateEnum;
import com.zs.exceptions.ShopOperationException;
import com.zs.service.ShopService;
import com.zs.util.ImgUtil;
import com.zs.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/25
 * @Time:20:57
 */

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {

        //1.判断是否需要处理图片
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImgUtil.deleteFileOrPatn(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                }
                //2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error" + e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputSteam, String fileName) {
        //空值判断
       if (shop == null){
           return  new ShopExecution(ShopStateEnum.NULL_SHOP);
       }
       try{
           //给部分店铺信息初始化
           shop.setEnableStatus(0);
           shop.setCreateTime(new Date());
           shop.setLastEditTime(new Date());
           //添加商铺信息
           int effectedNum = shopDao.insertShop(shop);
           if (effectedNum <= 0){
               throw new ShopOperationException("店铺创建失败");
           }else {
               if (shopImgInputSteam != null){
                   //存储图片
                   try {
                       addShopImg(shop,shopImgInputSteam,fileName);
                   } catch (Exception e) {
                       throw new ShopOperationException("addShopImg error:"+e.getMessage());
                   }
                   //更新店铺的图片地址
                   effectedNum = shopDao.updateShop(shop);
                   if (effectedNum <= 0){
                       throw new ShopOperationException("更新图片地址失败");
                   }
               }
           }
       }catch (Exception e){
        throw new ShopOperationException("addShop error:"+ e.getMessage());
       }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName){
        //获取图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImgUtil.generateThumbnail(shopImgInputStream,fileName,dest);
        shop.setShopImg(shopImgAddr);
    }
}


