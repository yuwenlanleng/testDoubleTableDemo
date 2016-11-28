/*
 * ExportUtil.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-29 10:22:13
 */
package com.nazca.test.util;

import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.test.enums.FileType;
import com.nazca.test.pojo.CarCheckWarp;
import com.nazca.util.NazcaFormater;
//import com.yz.ncjc.consts.ErrorCode;
//import com.yz.ncjc.enums.AlarmType;
//import com.yz.ncjc.enums.FileType;
//import com.yz.ncjc.model.BandRecord;
//import com.yz.ncjc.model.wrap.AlarmWrap;
//import com.yz.ncjc.model.wrap.CarCheckWarp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 导出工具类
 *
 * @author 上官新建 <shangguanxinjian@yzhtech.com>
 */
public class ExportUtil {

    private static Log log = LogFactory.getLog(ExportUtil.class);

    public static File generateTempFile(FileType type) {
        File f = new File(
                System.getProperty("java.io.tmpdir") + File.separator + UUID.
                randomUUID().toString() + "."
                + type.getExtensionName());
        try {
            f.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return f;
    }

    public static void wirteCarRecordToExcel(File file, List<CarCheckWarp> list) throws HttpRPCException {
        HSSFWorkbook book = null;
        try {
            String sheetName = "手环记录查询表";
            List<String> columnlist = new ArrayList<>();
            columnlist.add("手环ID");
            columnlist.add("开始时间");
            columnlist.add("结束时间");
            HSSFSheet sheet = null;
            if (book == null) {
                book = new HSSFWorkbook();
                sheet = book.createSheet(sheetName);
                HSSFRow row = sheet.createRow(0);
                for (int i = 0; i < columnlist.size(); i++) {
                    row.createCell(i).setCellValue(columnlist.get(i));
                }
            } else {
                sheet = book.getSheet(sheetName);
            }
            int maxRow = 0;
            //生成数据
            for (int row = 1; row <= list.size(); row++) {
                CarCheckWarp info = list.get(row - 1);
                HSSFRow excelRow = sheet.createRow(row + maxRow);
                excelRow.createCell(0).setCellValue(info.getCarCarColor() == null ? "" : info.getCarCarColor());
                excelRow.createCell(1).setCellValue(info.getCarCarModel() == null ? "" : info.getCarCarModel());
                excelRow.createCell(2).setCellValue(info.getCarPlateNumber() == null ? "" : info.getCarPlateNumber());
                excelRow.createCell(3).setCellValue(info.getDriverName() == null ? "" : info.getDriverName());
            }
            FileOutputStream fos = new FileOutputStream(file);
            book.write(fos);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

    }

    public static void zipFile(File file,String fileName) throws IOException {
        byte[] buffer = new byte[1024];

        //生成的ZIP文件名为Demo.zip   
//        String strZipName = "d:/Demo.zip";
        File parentFile = file.getParentFile();
        String strZipName = parentFile.toString()+File.separator+fileName+".zip";
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipName));

        //需要同时下载的两个文件result.txt ，source.txt   
//        File[] file1 = {new File("d:/a.txt"), new File("d:/b.txt"), new File("d:/aa.txt"), new File("d:/bb.txt")};//打包多个文件
        File[] file1 = {file};

        for (int i = 0; i < file1.length; i++) {

            FileInputStream fis = new FileInputStream(file1[i]);

            out.putNextEntry(new ZipEntry(file1[i].getName()));

            int len;

            //读入需要下载的文件的内容，打包到zip文件   
            while ((len = fis.read(buffer)) > 0) {

                out.write(buffer, 0, len);

            }

            out.closeEntry();

            fis.close();

        }
        out.close();
        System.out.println("生成Demo.zip成功");
    }
}
