package com.zs.service.impl;

import com.zs.entity.Area;
import com.zs.service.AreaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import testParents.TestParent;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/24
 * @Time:16:30
 */
public class AreaServiceImplTest extends TestParent {

    @Autowired
    private AreaService areaService;
    @Test
    public void getAreaList() {
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList.size());
    }
}