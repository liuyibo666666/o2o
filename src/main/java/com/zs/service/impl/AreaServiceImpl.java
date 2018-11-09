package com.zs.service.impl;

import com.zs.dao.AreaDao;
import com.zs.entity.Area;
import com.zs.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/24
 * @Time:16:22
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
