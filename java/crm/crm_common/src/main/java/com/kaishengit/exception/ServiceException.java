package com.kaishengit.exception;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
public class ServiceException extends RuntimeException{

    public ServiceException(){};
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(String message,Throwable th){
        super(message,th);
    }
}
