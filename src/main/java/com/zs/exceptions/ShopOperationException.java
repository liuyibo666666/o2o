package com.zs.exceptions;

/**
 * @Created by IDEA
 * @author:LiuYiBo(小博)
 * @Date:2018/7/26
 * @Time:10:48
 */
public class ShopOperationException extends RuntimeException {


    private static final long serialVersionUID = 2361446884822298905L;
    public ShopOperationException(String message) {
        super(message);
    }

    public ShopOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
