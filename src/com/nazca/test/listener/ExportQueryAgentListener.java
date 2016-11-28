/*
 * ExportQueryAgentListener.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-21 17:01:18
 */
package com.nazca.test.listener;

import com.nazca.test.pojo.ExportResult;
import java.util.EventListener;

/**
 *
 * @author Hu Qin<huqin@yzhtech.com>
 */
public interface ExportQueryAgentListener extends EventListener {

    void onResultFetched(ExportResult result);

    void onExportFinished(ExportResult result);

    void onFailed(String msg, int code);
}
