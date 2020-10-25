package com.geek.springboot.controller;

import com.geek.springboot.pojo.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HelloController {

//    @ResponseBody//不经过视图组件直接发给浏览器
    @RequestMapping("hello")
    public ModelAndView hello(UserBean userBean){
        System.out.println("ttttt");
        return new ModelAndView("index");
//        return new ModelAndView(new RedirectView("login.html"));
    }
}
