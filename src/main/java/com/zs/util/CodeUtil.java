package com.zs.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/29
 * @Time:20:24
 */
public class CodeUtil {

    public static boolean checkVerifyCode(HttpServletRequest request) {

        String verifyCodeExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
System.out.println("request直接解析---------"+request.getParameter("verifyCodeActual"));
System.out.println("--------验证码1"+verifyCodeExpected+"----------");
System.out.println("--------验证码2"+verifyCodeActual+"----------");
        if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
            return false;
        }

        return true;

    }
}
