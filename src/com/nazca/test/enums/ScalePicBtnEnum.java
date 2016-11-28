/*
 * ScalePicBtnEnum.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-24 14:38:59
 */
package com.nazca.test.enums;

/**
 *
 * @author pengruirui
 */
public enum ScalePicBtnEnum {
    big() {
        @Override
        public String toString() {
            return "放大";
        }
    },
    small() {
        @Override
        public String toString() {
            return "缩小";
        }
    },
    reset() {
        @Override
        public String toString() {
            return "重置";
        }
    },
    left() {
        @Override
        public String toString() {
            return "逆时针旋转 ";
        }
    },
    right() {
        @Override
        public String toString() {
            return "顺时针旋转 ";
        }
    };
}
