package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class BaseController {
    public Account getCurrUser(HttpSession session) {
        return (Account) session.getAttribute("curr_user");
    }

}
