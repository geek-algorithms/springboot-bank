package com.geek.springboot.pojo;

import java.util.Date;

public class LogBean {
    private int log_id;
    private String log_type;//操作类型
    private double log_amount;//操作金额
    private Date log_time;//操作时间
    private UserBean userid;//操作帐户

    public LogBean(){

    }

    public LogBean(String log_type, double log_amount, Date log_time, UserBean userid) {
        this.log_type = log_type;
        this.log_amount = log_amount;
        this.log_time = log_time;
        this.userid = userid;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public double getLog_amount() {
        return log_amount;
    }

    public void setLog_amount(double log_amount) {
        this.log_amount = log_amount;
    }

    public Date getLog_time() {
        return log_time;
    }

    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }

    public UserBean getUserid() {
        return userid;
    }

    public void setUserid(UserBean userid) {
        this.userid = userid;
    }
}
