/*
 * GetInviteCodeAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-07-05 16:36:52
 */
package com.nazca.test.agent;

import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.test.util.FakeDataFactory;
import com.nazca.util.TimeFairy;
import java.io.InputStream;

/**
 *
 * @author Shi Jie <your.name at your.org>
 */
public class UploadPictureAgent extends AbstractAgent<String> {
    private String fileName;
    private InputStream is;

    public void setParame(String fileName,InputStream is) {
        this.is = is;
        this.fileName = fileName;
    }

    @Override
    protected String doExecute() throws HttpRPCException {
        TimeFairy tf = new TimeFairy();
        String entity = null;
        if (FakeDataFactory.isFake()) {
            entity = FakeDataFactory.uplaodPicture(fileName,is);
        }
        tf.sleepIfNecessary();
        return entity;
    }
}
