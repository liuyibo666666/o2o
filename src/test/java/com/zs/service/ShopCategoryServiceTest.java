package com.zs.service;

import com.zs.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import testParents.TestParent;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/9/3
 * @Time:20:39
 */
public class ShopCategoryServiceTest extends TestParent {

    @Autowired
    private ShopCategoryService shopCategoryService;
    @Test
    public void getShopCategoryList() {
        List<ShopCategory> list = shopCategoryService.getShopCategoryList(new ShopCategory());
        System.out.println("商铺类别；"+list.size());
    }
}