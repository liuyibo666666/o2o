package com.zs.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zs.dto.ShopExecution;
import com.zs.entity.Area;
import com.zs.entity.PersonInfo;
import com.zs.entity.Shop;
import com.zs.entity.ShopCategory;
import com.zs.enums.ShopStateEnum;
import com.zs.exceptions.ShopOperationException;
import com.zs.service.AreaService;
import com.zs.service.ShopCategoryService;
import com.zs.service.ShopService;
import com.zs.util.CodeUtil;
import com.zs.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/26
 * @Time:20:45
 */

@Controller

@RequestMapping("/shopadmin")

public class ShopManagementController {

    @Autowired

    private ShopService shopService;

    @Autowired

    private ShopCategoryService shopCategoryService;

    @Autowired

    private AreaService areaService;



    @RequestMapping(value = "/getshopmanagementinfo",method = RequestMethod.GET)

    @ResponseBody

    private Map<String, Object> getShopManagementInfo(HttpServletRequest request){

        Map<String, Object> modelMap = new HashMap<String, Object>();

        long shopId = HttpServletRequestUtil.getLong(request, "shopId");

        if(shopId <= 0) {

            Object currentShopObj = request.getSession().getAttribute("currentShop");

            if(currentShopObj == null) {

                modelMap.put("redirect", true);

                modelMap.put("url", "/shopadmin/shoplist");

            }else {

                Shop currentShop = (Shop)currentShopObj;

                modelMap.put("redirect", false);

                modelMap.put("shopId", currentShop.getShopId());

            }

        }else {

            Shop currentShop = new Shop();

            currentShop.setShopId(shopId);

            request.getSession().setAttribute("currentShop", currentShop);

            modelMap.put("redirect", false);

        }

        return modelMap;

    }



//    @RequestMapping(value = "/getshoplist",method = RequestMethod.GET)
//
//    @ResponseBody
//
//    private Map<String, Object> getShopList(HttpServletRequest request){
//
//        Map<String, Object> modelMap = new HashMap<String, Object>();
//
//        PersonInfo user = new PersonInfo();
//
//        user.setUserId(1L);
//
//        user.setName("test");
//
//        request.getSession().setAttribute("user", user);
//
//        user = (PersonInfo) request.getSession().getAttribute("user");
//
//        try {
//
//            Shop shopCondition = new Shop();
//
//            shopCondition.setOwner(user);
//
//            ShopExecution se = shopService.getShopList(shopCondition, 0, 100);
//
//            modelMap.put("shopList", se.getShopList());
//
//            modelMap.put("user", user);
//
//            modelMap.put("success", true);
//
//        } catch (Exception e) {
//
//            modelMap.put("success", false);
//
//            modelMap.put("errMsg", e.getMessage());
//
//        }
//
//        return modelMap;
//
//    }
//
//    @RequestMapping(value = "/getshopbyid",method = RequestMethod.GET)
//
//    @ResponseBody
//
//    private Map<String, Object> getShopById(HttpServletRequest request){
//
//        Map<String, Object> modelMap = new HashMap<String, Object>();
//
//        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
//
//        if(shopId > -1) {
//
//            try {
//
//                Shop shop = shopService.getByShopId(shopId);
//
//                List<Area> areaList = areaService.getAreaList();
//
//                modelMap.put("shop", shop);
//
//                modelMap.put("areaList", areaList);
//
//                modelMap.put("success", true);
//
//            } catch (Exception e) {
//
//                modelMap.put("success", false);
//
//                modelMap.put("errMsg", e.toString());
//
//            }
//
//        }else {
//
//            modelMap.put("success", false);
//
//            modelMap.put("errMsg", "empty shopId");
//
//        }
//
//        return modelMap;
//
//    }



    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET)

    @ResponseBody

    private Map<String, Object> getShopInitInfo(HttpServletRequest request){

        Map<String, Object> modelMap = new HashMap<String, Object>();

        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();

        List<Area> areaList = new ArrayList<Area>();

        try {

            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());

        }

        return modelMap;

    }



    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody

    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        if(!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误！");
            return modelMap;

        }

        // 1接受并转化相应的参数，包括店铺信息以及图片信息

        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");

        ObjectMapper mapper = new ObjectMapper();

        Shop shop = null;

        try {

            shop = mapper.readValue(shopStr, Shop.class);

        } catch (Exception e) {

            modelMap.put("success", false);

            modelMap.put("errMsg", e.getMessage());

            return modelMap;

        }

        CommonsMultipartFile shopImg = null;

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        if (commonsMultipartResolver.isMultipart(request)) {

            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");

//			if(shopImg == null) {

//				modelMap.put("success", false);

//				modelMap.put("errMsg", "上传图片不能为空");

//				return modelMap;

//			}

        }else {//如果允许不传图片，else去掉

            modelMap.put("success", false);

            modelMap.put("errMsg", "上传图片不能为空");

            return modelMap;

        }

        //2.注册店铺

        if(shop!=null&&shopImg!=null){

            PersonInfo owner=new PersonInfo();

            // TODO: 2018/4/26 Session

            owner.setUserId(1L);

            shop.setOwner(owner);



            ShopExecution se= null;

            try {

                se = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());

                if(se.getState()==ShopStateEnum.CHECK.getState()){

                    modelMap.put("success",true);

                    //该用户可以操作的店铺列表

                    List<Shop> shopList=(List<Shop>)request.getSession().getAttribute("shopList");

                    if(shopList==null||shopList.size()==0){

                        shopList=new ArrayList<Shop>();

                    }



                    shopList.add(se.getShop());

                    request.getSession().setAttribute("shopList",shopList );



                }

                else{

                    modelMap.put("seccess",false);

                    modelMap.put("errMsg",se.getStateInfo());

                }

            }

            catch (ShopOperationException e){

                modelMap.put("success",false);

                modelMap.put("errMsg",e.getMessage());

            }

            catch (IOException e) {

                modelMap.put("success",false);

                modelMap.put("errMsg",e.getMessage());

            }

            if(se.getState()== ShopStateEnum.CHECK.getState()){

                modelMap.put("success",true);

            }else {

                modelMap.put("success",false);

                modelMap.put("errMsg",se.getStateInfo());

            }

            return modelMap;

        }else{

            modelMap.put("success",false);

            modelMap.put("errMsg","请输入店铺信息");

            return modelMap;

        }

    }



    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)

    @ResponseBody

    private Map<String, Object> modifyShop(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();

        if(!CodeUtil.checkVerifyCode(request)) {

            modelMap.put("success", false);

            modelMap.put("errMsg", "验证码错误！");

            return modelMap;

        }

        // 1接受并转化相应的参数，包括店铺信息以及图片信息

        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");

        ObjectMapper mapper = new ObjectMapper();

        Shop shop = null;

        try {

            shop = mapper.readValue(shopStr, Shop.class);

        } catch (Exception e) {

            modelMap.put("success", false);

            modelMap.put("errMsg", e.getMessage());

            return modelMap;

        }

        CommonsMultipartFile shopImg = null;

        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());

        if (cmr.isMultipart(request)) {

            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");

        }

        //2.修改店铺信息

        if(shop!=null&&shop.getShopId()!=null){





            ShopExecution se= null;

            try {



                if(shopImg==null){

                    se = shopService.modifyShop(shop,null, null);

                }

                else {



                    se = shopService.modifyShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());

                }

                if(se.getState()==ShopStateEnum.SUCCESS.getState()){

                    modelMap.put("success",true);

                }

                else{

                    modelMap.put("seccess",false);

                    modelMap.put("errMsg",se.getStateInfo());

                }

            }

            catch (ShopOperationException e){

                modelMap.put("success",false);

                modelMap.put("errMsg",e.getMessage());

            }

            catch (IOException e) {

                modelMap.put("success",false);

                modelMap.put("errMsg",e.getMessage());

            }

            if(se.getState()== ShopStateEnum.SUCCESS.getState()){

                modelMap.put("success",true);

            }else {

                modelMap.put("success",false);

                modelMap.put("errMsg",se.getStateInfo());

            }

            return modelMap;

        }else{

            modelMap.put("success",false);

            modelMap.put("errMsg","请输入店铺Id");

            return modelMap;

        }

    }

//	private static void inputStreamToFile(InputStream ins,File file) {

//		FileOutputStream os = null;

//		try {

//			os = new FileOutputStream(file);

//			int bytesRead = 0;

//			byte[] buffer = new byte[1024];

//			while((bytesRead = ins.read(buffer)) != -1) {

//				os.write(buffer, 0, bytesRead);

//			}

//		}catch (Exception e) {

//			throw new RuntimeException("调用inputStreamToFile产生异常:"+e.getMessage());

//		}finally {

//			try {

//				if(os != null) {

//					os.close();

//				}

//				if(ins != null) {

//					ins.close();

//				}

//			} catch (IOException e) {

//				throw new RuntimeException("inputStreamToFile关闭io产生异常:"+e.getMessage());

//			}

//		}

//	}

}
