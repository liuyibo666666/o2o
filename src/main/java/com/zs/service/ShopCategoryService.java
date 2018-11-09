package com.zs.service;

import com.zs.entity.ShopCategory;

import java.util.List;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/9/2
 * @Time:21:24
 */
public interface ShopCategoryService {
    /**
     * 获得商品类列表
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);

}
