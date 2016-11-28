/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-12-05 22:25:51
 */
package com.nazca.test.pojo;

import com.nazca.test.enums.ImportAndExportResultState;
import java.io.Serializable;

/**
 * 导入/导出结果
 *
 * @author Liu yizhe <lyz@yzhtech.com>
 */
public class ExportResult implements Serializable {

    private static final long serialVersionUID = -8791030518344426983L;
    private ImportAndExportResultState state = ImportAndExportResultState.running;
    //当前已读到的条数
    private int processCount;
    //总条数
    private int totalCount;
    private String filePath;//导出文件地址
    private StringBuilder logContent = new StringBuilder();

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getProcessCount() {
        return processCount;
    }

    public void setProcessCount(int processCount) {
        this.processCount = processCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public StringBuilder getLogContent() {
        return logContent;
    }

    public void setLogContent(StringBuilder logContent) {
        this.logContent = logContent;
    }

    public ImportAndExportResultState getState() {
        return state;
    }

    public void setState(ImportAndExportResultState state) {
        this.state = state;
    }
}
