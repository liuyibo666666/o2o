package com.zs.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created with IDEA
 * @author:LiuYiBo(小博)
 * Date:2018/7/23
 * Time:19:02
 */
@Getter
@Setter
public class ProductImg {

    /**
     * 主键ID
     */
    private Long productImgId;

    /**
     * 图片地址
     */
    private String imgAddr;

    /**
     * 图片简介
     */
    private String imgDesc;

    /**
     * 权重，越大越排前显示
     */
    private Integer priority;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 标明是属于哪个商品的图片
     */
    private Long productId;

}
