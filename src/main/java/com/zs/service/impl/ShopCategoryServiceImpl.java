package com.zs.service.impl;

import com.zs.dao.ShopCategoryDao;
import com.zs.entity.ShopCategory;
import com.zs.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/9/2
 * @Time:21:25
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
        return shopCategoryDao.queryShopCategory(shopCategory);
    }
}
