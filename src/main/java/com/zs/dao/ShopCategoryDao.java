package com.zs.dao;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/9/2
 * @Time:21:30
 */
import com.zs.entity.ShopCategory;

import com.zs.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;



import java.util.List;



/**

 * Created by Administrator on 2018/2/16.

 */

@Repository

public interface ShopCategoryDao {

    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

}
