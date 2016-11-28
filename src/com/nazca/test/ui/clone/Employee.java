/*
 * Employee.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-11-15 13:55:30
 */
package com.nazca.test.ui.clone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 深克隆
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class Employee implements Cloneable {

    private String name;
    private int age;
    private Address address;
    private List<Address> addressS;//测试对集合的clone
    private List<String> clothe;

    public Employee(String name, int age, Address address) {
        super();
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getClothe() {
        return clothe;
    }

    public void setClothe(List<String> clothe) {
        this.clothe = clothe;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Address> getAddressS() {
        return addressS;
    }

    public void setAddressS(List<Address> addressS) {
        this.addressS = addressS;
    }

    
    @Override
    public Employee clone() {
        Employee employee = null;
        try {
            employee = (Employee) super.clone();
            employee.address = address.clone();  //对引用类型的域进行克隆

//            Iterator<Address> iteratorCopy = addressS.iterator();
//            while (iteratorCopy.hasNext()) {
//                employee.addressS.add(iteratorCopy.next().clone());//对集合类型的clone,集合泛型是支持深克隆的
//            }
//            employee.clothe = new ArrayList<>();
//            employee.clothe.addAll(clothe);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("姓名：" + name + ",");
        sb.append("年龄：" + age + ", ");
        sb.append("地址：" + address);
        sb.append("上身衣服：" + clothe.get(0) + ",");
        sb.append("下身衣服：" + clothe.get(1) + ",");
        sb.append("鞋子：" + clothe.get(2) + ",");
        return sb.toString();
    }
}
