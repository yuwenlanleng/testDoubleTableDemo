/*
 * TestEmployeeClass.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-11-15 13:56:59
 */
package com.nazca.test.ui.clone;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试深克隆
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class TestEmployeeClass {

    public static void main(String[] args) {
        System.out.println("克隆之前:");
        Address address = new Address("中国", "吉林", "长春");
        List<String> clothes = new ArrayList<>();
        clothes.add("平常上衣");
        clothes.add("平常裤子");
        clothes.add("平常鞋子");
        Employee employee1 = new Employee("明日科技", 12, address);
        employee1.setClothe(clothes);
        System.out.println("员工1信息：" + employee1);

        Employee employee2 = employee1.clone();

        employee2.getAddress().setState("中国");
        employee2.getAddress().setProvince("四川");
        employee2.getAddress().setCity("成都");
        List<String> clothes2 = new ArrayList<>();
        clothes.add("运动上衣");
        clothes.add("运动裤");
        clothes.add("运动鞋");
        employee2.getClothe().addAll(clothes2);
        System.out.println("克隆之后：");
        System.out.println("员工2信息：" + employee2);
        System.out.println("员工1信息：" + employee1);
    }
}
