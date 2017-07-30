package com.kaishengit.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**首页
 * Created by Administrator on 2017/7/19 0019.
 */
@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
