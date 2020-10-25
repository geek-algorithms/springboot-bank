package com.geek.springboot;

import java.util.Scanner;

public class TestDemo {
    static double levelA=100000*0.1;
    static double levelB=levelA+100000*0.075;
    static double levelC=levelB+200000*0.05;
    static double levelD=levelC+200000*0.03;
    static double levelE=levelD+400000*0.015;

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入业绩：");
        String temp=sc.nextLine();
        double money=Double.parseDouble(temp);
        double bounds=getLevel(money);
        System.out.println(bounds);
    }

    public static double getLevel(double money){
        if(money<100000){
            return money*0.1;
        }else if(money<200000){
            return levelA+(money-100000)*0.075;
        }else if(money<400000){
            return levelB+(money-200000)*0.05;
        }else if(money<600000){
            return levelC+(money-400000)*0.03;
        }else if(money<1000000){
            return levelD+(money-600000)*0.015;
        }else if(money>1000000){
            return levelE+(money-1000000)*0.01;
        }
        return 0;
    }
}
