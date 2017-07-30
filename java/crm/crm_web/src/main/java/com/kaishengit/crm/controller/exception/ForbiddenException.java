package com.kaishengit.crm.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
/**
 * 访问没有权限的数据，抛出HTTP 403异常
 */
@ResponseStatus(code= HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
}
