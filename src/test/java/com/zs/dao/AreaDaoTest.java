package com.zs.dao;

import com.zs.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testParents.TestParent;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/24
 * @Time:15:58
 */

public class AreaDaoTest extends TestParent {

    @Autowired
    private AreaDao areaDao;
    @Test
    public void queryArea() {
        List<Area> areaList = areaDao.queryArea();
        System.out.println(areaList.size());
    }
}