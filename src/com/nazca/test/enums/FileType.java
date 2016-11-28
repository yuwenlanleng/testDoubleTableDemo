/*
 * FileType.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-05-09 11:59:52
 */
package com.nazca.test.enums;

/**
 *
 * @author Wu Jinghua <wjh@yzhtech.com>
 */
public enum FileType {
    excel() {
        @Override
        public String getExtensionName() {
            return "xlsx";
        }
    };

    public abstract String getExtensionName();
}
