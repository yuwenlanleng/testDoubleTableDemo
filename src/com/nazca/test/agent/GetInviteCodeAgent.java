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
import com.nazca.test.util.FakeDataFactory;
import com.nazca.util.TimeFairy;
import java.util.List;

/**
 *
 * @author Shi Jie <your.name at your.org>
 */
public class GetInviteCodeAgent  extends AbstractAgent <List<InviteCode>>{
    @Override
    protected List<InviteCode> doExecute() throws HttpRPCException {
        TimeFairy tf = new TimeFairy();
        List<InviteCode> inviteCodeList = null;
        if (FakeDataFactory.isFake()) {
            inviteCodeList = FakeDataFactory.queryAllInviteCodes();
        } 
        tf.sleepIfNecessary();
        return inviteCodeList;
    }
}
