package com.kaishengit.exception;

/**
 * Created by Administrator on 2017/7/28 0028.
 */
public class WeiXinException extends RuntimeException{

    public WeiXinException(){};

    public WeiXinException(String message){
        super(message);
    };
    public WeiXinException(Throwable th){
        super(th);
    };
    public WeiXinException(String message,Throwable th){
        super(message,th);
    };



}
