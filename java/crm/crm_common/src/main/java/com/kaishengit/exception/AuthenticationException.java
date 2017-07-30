package com.kaishengit.exception;

/**认证异常
 * Created by Administrator on 2017/7/19 0019.
 */
public class AuthenticationException extends ServiceException{

    public AuthenticationException(){};
    public AuthenticationException(String message){
        super(message);
    }
    public AuthenticationException(String message,Throwable th){
        super(message,th);
    }
}
