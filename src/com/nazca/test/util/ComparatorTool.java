/*
 * ComparatorTool.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-20 10:37:04
 */
package com.nazca.test.util;

import com.nazca.test.pojo.Entity;
import java.util.Comparator;

/**
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class ComparatorTool {

    public static Comparator<Integer> getIntComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1.compareTo(o2) > 0) {
                return 1;
            } else if (o1.compareTo(o2) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    };
    public static Comparator<Double> getDoubeleComparator = new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            if (o1.compareTo(o2) > 0) {
                return 1;
            } else if (o1.compareTo(o2) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    };
    public static Comparator<String> getStringComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            if (o1.compareTo(o2) > 0) {
                return 1;
            } else if (o1.compareTo(o2) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    };
}
