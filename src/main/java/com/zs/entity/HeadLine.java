package com.zs.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created with IDEA
 * author:LiuYiBo
 * Date:2018/7/19
 * Time:14:30
 */

@Getter
@Setter
public class HeadLine {
    private Long lineId;
    private String lineName;
    private String lineLink;
    private String LineImg;
    private Integer priority;
    //0不可用、1可用
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}

