package com.zs.service;

import com.zs.dto.ShopExecution;
import com.zs.entity.Area;
import com.zs.entity.PersonInfo;
import com.zs.entity.Shop;
import com.zs.entity.ShopCategory;
import com.zs.enums.ShopStateEnum;
import com.zs.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import testParents.TestParent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/26
 * @Time:10:55
 */
public class ShopServiceTest extends TestParent {

    @Autowired
    private ShopService shopService;

    @Test
    public  void  testModifyShop() throws ShopOperationException,FileNotFoundException{

        Shop shop=new Shop();
        shop.setShopId(2L);
        shop.setShopName("修改收的店铺名称");
        File shopImg=new File("E:\\壁纸\\1521946924453.jpg");
        InputStream is=new FileInputStream(shopImg);
        ShopExecution shopExecution= shopService.modifyShop(shop,is,"dabai.jpg");
        System.out.println("新的图片地址："+shopExecution.getShop().getShopImg());

    }

    @Test
    public void addShop() throws FileNotFoundException {

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
        shop.setPhone("test11");
        shop.setCreateTime(new Date());
        shop.setShopName("测试店铺11");
        shop.setAdvice("审核中");
        shop.setShopDesc("test11");
        shop.setShopAddr("test11");
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        File shopImg=new File("E:\\壁纸\\5a796b2d41c3e.jpg");
        InputStream is=new FileInputStream(shopImg);
        System.out.println( shopService.addShop(shop,is,shopImg.getName()));
    }
}