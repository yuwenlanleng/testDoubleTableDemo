/*
 * ErrorCodeFormater.java
 * 
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2009-12-23 17:44:15
 */

package com.nazca.test.util;

import java.text.DecimalFormat;

/**
 *
 * @author fred
 */
public final class ErrorCodeFormater {
    private ErrorCodeFormater(){}
    private static DecimalFormat df = new DecimalFormat("000000");
    /**
     * 格式化错误代码和错误释义
     * @param msg
     * @param code
     * @return 返回结果样例为：用户名或密码错误(#000010)
     */
    public static String formate(String msg, int code){
        if(code >= 0){
            String codeString = df.format(code);
            return msg + "(#" + codeString + ")";
        }else{
            return msg + "(#" + Integer.toHexString(code) + ")";
        }
    }
}
