/*
 * GetCompanyLicenseExamineImgAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-08 12:32:15
 */
package com.nazca.test.agent;

import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.test.util.FakeDataFactory;
import com.nazca.util.TimeFairy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class GetCompanyLicenseExamineImgAgent extends AbstractAgent<BufferedImage> {

    private String imageName;

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    protected BufferedImage doExecute() throws HttpRPCException {
        TimeFairy tf = new TimeFairy();
        InputStream is = null;
        BufferedImage curImage = null;
        if (imageName
                != null) {
            if (FakeDataFactory.isFake()) {
                is = FakeDataFactory.getlicenseImg(imageName);
            }
            if (is != null) {
                try {
                    curImage = ImageIO.read(is);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        tf.sleepIfNecessary();
        return curImage;
    }
}
