package com.zs.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/27
 * @Time:15:42
 */

@Controller
@RequestMapping(value = "shopadmin",method ={RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }
}
