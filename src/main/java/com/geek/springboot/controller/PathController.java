package com.geek.springboot.controller;

import com.geek.springboot.pojo.UserBean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PathController {

    @RequestMapping("/deposit.html")
    public String deposit() {
        return "deposit";
    }

    @RequestMapping("/withdraws.html")
    public String withdraws() {
        return "withdraws";
    }

    @RequestMapping("/transfer.html")
    public String transfer() {
        return "transfer";
    }

    @RequestMapping("/log.html")
    public String log() {
        return "log";
    }

    @RequestMapping("/freeze.html")
    public String freeze() {
        return "freeze";
    }

    @RequestMapping("/refresh.html")
    public String refresh() {
        return "refresh";
    }

    @RequestMapping("/userLeft.html")
    public String userBack(){
        return "userLeft";
    }

    @RequestMapping("/findMoney.html")
    public String findMoney() {
        return "findMoney";
    }

    @RequestMapping("/adminLeft.html")
    public String adminBack(){
        return "adminLeft";
    }
}
