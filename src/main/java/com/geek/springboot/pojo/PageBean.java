package com.geek.springboot.pojo;

public class PageBean {
    private int sumLog;// 总记录数
    private int pageSize;// 一页装多少条记录
    private int sumPage;// 总页数

    public int getSumLog() {
        return sumLog;
    }

    public void setSumLog(int sumLog) {
        this.sumLog = sumLog;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSumPage() {
        return sumPage;
    }

    public PageBean(int sumLog, int pageSize) {
        super();
        this.sumLog = sumLog;
        this.pageSize = pageSize;
        this.sumPage = (int) Math.ceil(sumLog * 1.0 / pageSize);
    }
}
