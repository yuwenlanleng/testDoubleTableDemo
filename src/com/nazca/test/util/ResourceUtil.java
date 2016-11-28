/*
 * ResourceUtil.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-24 15:24:56
 */
package com.nazca.test.util;

import com.nazca.ui.GraphicsTool;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author pengruirui
 */
public class ResourceUtil {
    public static BufferedImage readImage(String fileName){
        return GraphicsTool.readImage(ResourceUtil.class.getResource("/com/nazca/test/res/" + fileName));
    }
    
    public static ImageIcon readIcon(String fileName){
        return new ImageIcon(ResourceUtil.class.getResource("/com/nazca/test/res/" + fileName));
    }
    
    public static InputStream readStream(String fileName){
        return ResourceUtil.class.getResourceAsStream("/com/nazca/test/res/" + fileName);
    }
}
