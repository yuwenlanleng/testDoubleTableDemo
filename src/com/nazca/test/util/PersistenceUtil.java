/*
 * PersistenceUtil.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-30 11:13:17
 */
package com.nazca.test.util;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Zhu Mengchao
 */
public class PersistenceUtil {

    public static File getTemplateForDownloadFile(String fileName, String AttachmentDirType) throws IOException {
        String destPath = System.getProperty("user.home") + File.separator + "." + "myTestFolder"
                + File.separator + AttachmentDirType;
        File dir = new File(destPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String path = destPath + File.separator + fileName;
        File f = new File(path);
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }

}
