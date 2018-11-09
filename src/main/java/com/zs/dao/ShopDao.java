package com.zs.dao;

import com.zs.entity.Shop;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/25
 * @Time:8:16
 */
public interface ShopDao {


    /**
     * 根据商铺id查询商铺信息
     * @param shopId
     * @return
     */
    Shop queryByShopId(Long shopId);


    /**
     * 新增店埔
     * @param shop
     * @return
     */
    int insertShop(Shop shop);


    /**
     * 跟新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
