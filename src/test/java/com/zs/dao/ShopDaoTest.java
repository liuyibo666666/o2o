package com.zs.dao;

import com.zs.entity.Area;
import com.zs.entity.PersonInfo;
import com.zs.entity.Shop;
import com.zs.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import testParents.TestParent;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/25
 * @Time:9:15
 */
public class ShopDaoTest extends TestParent {

    @Autowired
    private ShopDao shopDao;

    @Test
    public  void testQueryByShopId(){
        Long shopId = 1L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("AreaId:"+ shop.getArea().getAreaId());
        System.out.println("AreaName:"+ shop.getArea().getAreaName());

    }

    @Test
    public  void testInsertShop(){
        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setPhone("test");
        shop.setCreateTime(new Date());
        shop.setShopName("测试店铺");
        shop.setAdvice("审核中");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setShopImg("test");
        shop.setEnableStatus(1);
       System.out.println(shopDao.insertShop(shop));
    }
    @Test
    public  void testUpdateShop(){
        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();
        shop.setShopId(1L);
        shop.setShopDesc("测试店铺");
        shop.setShopAddr("测试地址");
        System.out.println(shopDao.updateShop(shop));
    }
}