package com.zs.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/26
 * @Time:21:09
 */
public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key) {
        try {
            return Long.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return false;
        }

    }

    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            System.out.println("------解析出来的验证码-------"+result+"---------");
            if (result != null) {
                //去除左右两边的空格（为什么？）
                result = result.trim();
            }
            if ("".equals(result)) {
                result = null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}




