package com.nazca.test.util;

/*
 * ExportCarResultProcessor
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-21 14:41:15
 */
import javax.servlet.http.HttpSession;

/**
 *
 * @author Liu yizhe <lyz@yzhtech.com>
 */
public class ExportCarResultProcessor extends ExportResultProcessor {

    public ExportCarResultProcessor(HttpSession session) {
        super(session, "KEY_EXPORT_CAR_RESULT_MAP");
    }
}
