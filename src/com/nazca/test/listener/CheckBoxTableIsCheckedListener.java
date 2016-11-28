/*
 * CheckBoxTableIsCheckedListener.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-11-09 15:59:03
 */
package com.nazca.test.listener;

/**
 * 监听带勾选框的表格是否被勾选
 * @author Qiu Dongyue
 */
public interface CheckBoxTableIsCheckedListener {
    /**
     * 状态发生变化
     * @param checked 有勾选为true，否则为false
     */
    public void checkStateChanged(boolean checked);
    /**
     * 更新勾选中条数
     * @param count 勾选中的条数
     */
    public void updateCheckedCount(int count);
}
