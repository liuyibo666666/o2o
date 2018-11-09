package com.zs.dto;

import com.zs.entity.Shop;
import com.zs.enums.ShopStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 店铺操作结果的显示
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/25
 * @Time:20:06
 */
@Getter
@Setter
public class ShopExecution {

    /**
     * 状态显示
     */
    private int state;

    /**
     * 状态显示
     */
    private String stateInfo;

    /**
     * 店铺数量
     */
    private int count;

    /**
     * 操作的店铺（增删改店铺的时候可以用到）
     */
    private Shop shop;

    /**
     * 商铺列表（查询的时候用的到）
     */
    private List<Shop> shopList ;

    public ShopExecution() {

    }

    /**
     * 失败的构造器
     * @param shopStateEnum
     */
    public ShopExecution(ShopStateEnum shopStateEnum) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
    }

    /**
     * 成功的构造器
     * @param shopStateEnum
     * @param shop
     */
    public ShopExecution(ShopStateEnum shopStateEnum, Shop shop) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shop = shop;
    }

    /**
     * 成功的构造方法
     * @param shopStateEnum
     * @param shopList
     */
    public ShopExecution(ShopStateEnum shopStateEnum, List<Shop> shopList) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shopList = shopList;
    }

    @Override
    public String toString() {
        return "ShopExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", count=" + count +
                ", shop=" + shop +
                ", shopList=" + shopList +
                '}';
    }
}
