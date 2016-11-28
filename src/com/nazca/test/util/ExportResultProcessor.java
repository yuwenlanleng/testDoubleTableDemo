/*
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-21 14:41:39
 */
package com.nazca.test.util;

import com.nazca.test.enums.ImportAndExportResultState;
import com.nazca.test.pojo.ExportResult;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Liu yizhe <lyz@yzhtech.com>
 */
public abstract class ExportResultProcessor {

    private HttpSession session = null;
    private String resultMapKey = null;

    protected ExportResultProcessor(HttpSession session, String resultMapKey) {
        this.session = session;
        this.resultMapKey = resultMapKey;
    }

    private Map<String, ExportResult> getImportMap() {
        Map<String, ExportResult> importMap = (Map<String, ExportResult>) session.getAttribute(resultMapKey);
        if (importMap == null) {
            importMap = new HashMap<>();
            session.setAttribute(resultMapKey, importMap);
        }
        return importMap;
    }

    public void startNewImport() {
        getImportMap().put(resultMapKey, new ExportResult());
    }

    public ExportResult getResult() {
        if (getImportMap() != null) {
            return getImportMap().get(resultMapKey);
        } else {
            return null;
        }
    }

    public void processed(int processCount, int totalCount) {
        if (getResult() != null) {
            getResult().setProcessCount(processCount);
            getResult().setTotalCount(totalCount);
        }
    }

    public void gotoSucceed() {
        if (getResult() != null) {
            getResult().setState(ImportAndExportResultState.succeed);
        }
    }

    public void gotoFailed() {
        if (getResult() != null) {
            getResult().setState(ImportAndExportResultState.failed);
        }
    }
}
