package com.zs.service;

import com.zs.dto.ShopExecution;
import com.zs.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/25
 * @Time:20:54
 */
public interface ShopService {
    /**
     * 通过商铺id获取商铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 更新商铺信息，包括对图片的处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName);

    /**
     * 添加商铺
     * @param shop
     * @param shopImgInputSteam
     * @param fileName
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputSteam, String fileName);
}
