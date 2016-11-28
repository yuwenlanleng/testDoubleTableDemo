/*
 * CheckableItem.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-09-29 10:47:41
 */
package com.nazca.test.model;

/**
 * 可勾选类模板
 * @author Qiu Dongyue
 */
public class CheckableItem<T> {
    private T item;
    private boolean checked;

    public CheckableItem(T item) {
        this.item = item;
    }

    public CheckableItem() {
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CheckableItem<T> other = (CheckableItem<T>) obj;
        if (this.item != other.item && (this.item == null ||
                !this.item.equals(other.item))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.item != null ? this.item.hashCode() : 0);
        return hash;
    }
}
