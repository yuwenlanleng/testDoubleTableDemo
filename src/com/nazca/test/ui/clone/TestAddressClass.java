/*
 * TestAddressClass.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-11-15 11:45:00
 */
package com.nazca.test.ui.clone;

/**
 * 测试浅克隆和深克隆
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class TestAddressClass {
    public static void main(String[] args) {
        Address address = new Address("日本", "东京", "冲绳");
        System.out.println("被克隆对象：" + address);
        System.out.println(address.getClass().getName() + "@" + Integer.toHexString(address.hashCode()));
        //引用
        Address quoteAddress = address;
        System.out.println("引用对象："+quoteAddress);
        System.out.println(quoteAddress.getClass().getName() + "@" + Integer.toHexString(quoteAddress.hashCode()));
        //克隆
        Address cloneAddress = address.clone();
        System.out.println("克隆对象：" + cloneAddress);
        System.out.println(cloneAddress.getClass().getName() + "@" + Integer.toHexString(cloneAddress.hashCode()));
    }
}
