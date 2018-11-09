package com.zs.service;

import com.zs.entity.Area;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/24
 * @Time:16:21
 */

public interface AreaService {
    List<Area> getAreaList();
}
