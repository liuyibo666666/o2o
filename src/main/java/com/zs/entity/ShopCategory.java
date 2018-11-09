package com.zs.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created with IDEA
 * author:LiuYiBo
 * Date:2018/7/19
 * Time:15:02
 */
@Getter
@Setter
public class ShopCategory {

    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date craeteTime;
    private Date lastEditTime;
    private ShopCategory parent;

}
