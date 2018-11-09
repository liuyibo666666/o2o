package com.zs.enums;

import org.apache.ibatis.jdbc.Null;


/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/25
 * @Time:20:24
 */
public enum ShopStateEnum {
    CHECK(0,"审核中"),
    SUCCESS(1,"操作成功"),
    OFFLINE(-1,"非法店铺"),
    PASS(2,"通过认证"),
    INNER_ERROR(-1001,"系统内部错误"),
    NULL_SHOPID(-1002,"ShopId为空"),
    NULL_SHOP(-1003,"Shopc信息为空");



    private int state;
    private String stateInfo;

    ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static  ShopStateEnum stateInfo(int index){
        for (ShopStateEnum state : values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }

}

