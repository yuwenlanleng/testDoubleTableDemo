/*
 * ImageUtil.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-04-13 10:43:08
 */
package com.nazca.test.util;

import com.nazca.ui.GraphicsTool;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author Zhu Mengchao
 */
public final class ImageUtil {
    
    public static final int MIN_SCALE_PIXEL = 1500;

    public static BufferedImage readThumbnailImage(File file) {
        if (file == null) {
            return null;
        }
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            Iterator it = ImageIO.getImageReaders(iis);
            if (!it.hasNext()) {
                return null;
            }
            ImageReader ir = (ImageReader) it.next();
            ir.setInput(iis, true, true);
            ImageReadParam irp = ir.getDefaultReadParam();
            int h = ir.getHeight(0);
            int w = ir.getWidth(0);
            if (h > MIN_SCALE_PIXEL || w > MIN_SCALE_PIXEL) {
                if (h > w) {
                    int scale = (int) (h / (float) MIN_SCALE_PIXEL);
                    irp.setSourceSubsampling(scale, scale, 0, 0);
                } else {
                    int scale = (int) (w / (float) MIN_SCALE_PIXEL);
                    irp.setSourceSubsampling(scale, scale, 0, 0);
                }
            }

            return ir.read(0, irp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void scale2() {
        try {
            BufferedImage bi = readThumbnailImage(new File("d:\\a.jpg"));
            ImageIO.write(bi , "png", new File("d:\\a2.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        scaleImage();
        scale2();
        Thread.sleep(100000);
    }

    public static BufferedImage byteToImage(byte[] b) {
        if (b != null) {
            try(ByteArrayInputStream bais = new ByteArrayInputStream(b)) {
                return ImageIO.read(bais);
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
    
     /**
     * 将bufferedimage根据转化为图片类型为imgtype的byte数组
     *
     * @param img
     * @param imgType
     * @return
     */
    public static byte[] imageToByte(BufferedImage img, String imgType) {
        if (img != null) {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            try {
                ImageIO.write(img, imgType, bo);
                return bo.toByteArray();
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
