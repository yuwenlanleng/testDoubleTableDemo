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
import com.nazca.test.pojo.InviteCode;
import com.nazca.test.pojo.Entity;
import com.nazca.test.util.FakeDataFactory;
import com.nazca.util.TimeFairy;
import java.util.List;

/**
 *
 * @author Shi Jie <your.name at your.org>
 */
public class GetStudentsInfoAgent  extends AbstractAgent <List<Entity>>{
    @Override
    protected List<Entity> doExecute() throws HttpRPCException {
        TimeFairy tf = new TimeFairy();
        List<Entity> studentList = null;
        if (FakeDataFactory.isFake()) {
            studentList = FakeDataFactory.queryAllstudentList();
        } 
        tf.sleepIfNecessary();
        return studentList;
    }
}
