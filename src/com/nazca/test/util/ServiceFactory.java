/*
 * ServiceFactory.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-11-09 13:58:29
 */
package com.nazca.test.util;

import com.nazca.io.httpdao.HttpServletTool;
import com.nazca.io.httprpc.HttpRPCConfigUtil;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public final class ServiceFactory {
    private ServiceFactory(){}
    
    public static <T> T getService(Class<T> c, HttpServletRequest request) {
        try {
            T service = HttpRPCConfigUtil.getUtil("服务端包名").getService(c);
            HttpServletTool.injectFields(service, request);
            return service;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

