/*
 * FakeDataFactory.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-02-06 12:04:53
 */
package com.nazca.test.util;

import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.io.httprpc.HttpRPCInjection;
import com.nazca.test.enums.CompanyImageFileDirType;
import com.nazca.test.enums.FileType;
import com.nazca.test.pojo.CarCheckWarp;
import com.nazca.test.pojo.InviteCode;
import com.nazca.test.pojo.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * 测试数据工厂类
 *
 * @author Qiu Dongyue <qdy@yzhtech.com>
 */
public class FakeDataFactory {

    private static boolean isFakeData = false;
    @HttpRPCInjection
    private static HttpServletRequest request;
    FakeDataFactory service = ServiceFactory.getService(FakeDataFactory.class,
                    request);

    /**
     * 是否采用测试数据
     *
     * @return
     */
    public static boolean isFake() {
        return isFakeData;
    }

    /**
     * 测试数据设置
     *
     * @param flag
     */
    public static void setFake(boolean flag) {
        isFakeData = flag;
    }

    /**
     * 查询所有的团队信息
     *
     * @return
     */
    public static List<InviteCode> queryAllInviteCodes() {
        List<InviteCode> list = new ArrayList<>();
        InviteCode t = new InviteCode();
        InviteCode t2 = new InviteCode();
        InviteCode t3 = new InviteCode();
        InviteCode t4 = new InviteCode();
        InviteCode t5 = new InviteCode();
        InviteCode t6 = new InviteCode();
        t.setId("1");
        t.setSalesId("1");
        t.setInviteCode("1");
        t.setMobile("13752219649");
        t.setMaxNum(Integer.MAX_VALUE);
        t.setEmployNum(1);
        t2.setId("2");
        t2.setSalesId("2");
        t2.setInviteCode("2");
        t2.setMobile("13752219649");
        t2.setMaxNum(Integer.MAX_VALUE);
        t2.setEmployNum(2);
        t3.setId("3");
        t3.setSalesId("3");
        t3.setInviteCode("3");
        t3.setMobile("13752219649");
        t3.setMaxNum(Integer.MAX_VALUE);
        t3.setEmployNum(3);
        t4.setId("4");
        t4.setSalesId("4");
        t4.setInviteCode("4");
        t4.setMobile("13752219649");
        t4.setMaxNum(Integer.MAX_VALUE);
        t4.setEmployNum(4);
        t5.setId("5");
        t5.setSalesId("5");
        t5.setInviteCode("5");
        t5.setMobile("13752219649");
        t5.setMaxNum(Integer.MAX_VALUE);
        t5.setEmployNum(5);
        t6.setId("6");
        t6.setSalesId("6");
        t6.setInviteCode("6");
        t6.setMobile("13752219649");
        t6.setMaxNum(Integer.MAX_VALUE);
        t6.setEmployNum(6);
        list.add(t);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        return list;
    }

    public static List<Entity> queryAllstudentList() {
        List<Entity> list = new ArrayList<>();
        Entity t = new Entity();
        Entity t2 = new Entity();
        Entity t3 = new Entity();
        Entity t4 = new Entity();
        Entity t5 = new Entity();
        Entity t6 = new Entity();
        t.setField1("测试");
        t.setField2("1");
        t.setField3("1");
        t.setField4("1");
        t.setField5("1");
        t.setField6("1");
        t.setField7("1");
        t.setField8("1");
        t.setField9("1");
        t.setField10("1");
        t.setField11("1");
        t.setField12("1");
        t.setField13("1");
        t.setField14("1");
        t.setField15("1");
        t.setField16("1");
        t.setField17("1");
        t.setField18("1");
        t.setField19("1");
        t.setField20("1");
        t.setField21("1");
        t.setField22("1");
        t.setField23("1");
        t.setField24("1");
        t.setField25("1");
        t2.setField1("2");
        t2.setField2("2");
        t2.setField3("2");
        t2.setField4("2");
        t2.setField5("2");
        t2.setField6("2");
        t2.setField7("2");
        t2.setField8("2");
        t2.setField9("2");
        t2.setField10("2");
        t2.setField11("2");
        t2.setField12("2");
        t2.setField13("2");
        t2.setField14("2");
        t2.setField15("2");
        t2.setField16("2");
        t2.setField17("2");
        t2.setField18("2");
        t2.setField19("2");
        t2.setField20("2");
        t2.setField21("2");
        t2.setField22("2");
        t2.setField23("2");
        t2.setField24("2");
        t2.setField25("2");
        t3.setField1("3");
        t3.setField2("3");
        t3.setField3("3");
        t3.setField4("3");
        t3.setField5("3");
        t3.setField6("3");
        t3.setField7("3");
        t3.setField8("3");
        t3.setField9("3");
        t3.setField10("3");
        t3.setField11("3");
        t3.setField12("3");
        t3.setField13("3");
        t3.setField14("3");
        t3.setField15("3");
        t3.setField16("3");
        t3.setField17("3");
        t3.setField18("3");
        t3.setField19("3");
        t3.setField20("3");
        t3.setField21("3");
        t3.setField22("3");
        t3.setField23("3");
        t3.setField24("3");
        t3.setField25("3");
        t4.setField1("4");
        t4.setField4("4");
        t4.setField3("4");
        t4.setField4("4");
        t4.setField5("4");
        t4.setField6("4");
        t4.setField7("4");
        t4.setField8("4");
        t4.setField9("4");
        t4.setField10("4");
        t4.setField11("4");
        t4.setField12("4");
        t4.setField13("4");
        t4.setField14("4");
        t4.setField15("4");
        t4.setField16("4");
        t4.setField17("4");
        t4.setField18("4");
        t4.setField19("4");
        t4.setField20("4");
        t4.setField21("");
        t2.setField22("4");
        t4.setField23("4");
        t4.setField24("4");
        t4.setField25("4");
        t5.setField1("5");
        t5.setField2("5");
        t5.setField3("2");
        t5.setField4("5");
        t5.setField5("5");
        t5.setField6("5");
        t5.setField7("5");
        t5.setField8("5");
        t5.setField9("5");
        t5.setField10("5");
        t5.setField11("5");
        t5.setField12("5");
        t5.setField13("5");
        t5.setField14("5");
        t5.setField15("5");
        t5.setField16("5");
        t5.setField17("5");
        t5.setField18("5");
        t5.setField19("2");
        t5.setField20("5");
        t5.setField21("5");
        t5.setField22("5");
        t5.setField23("5");
        t5.setField24("5");
        t5.setField25("5");
        t5.setField1("5");
        t5.setField2("5");
        t5.setField3("5");
        t5.setField4("5");
        t5.setField5("5");
        t5.setField6("5");
        t5.setField7("5");
        t5.setField8("5");
        t5.setField9("5");
        t5.setField10("5");
        t5.setField11("5");
        t5.setField12("5");
        t5.setField13("5");
        t5.setField14("5");
        t5.setField15("5");
        t5.setField16("5");
        t5.setField17("5");
        t5.setField18("5");
        t5.setField19("5");
        t5.setField20("5");
        t5.setField21("5");
        t5.setField22("5");
        t5.setField23("5");
        t5.setField24("5");
        t5.setField25("5");
        t6.setField1("6");
        t6.setField2("6");
        t6.setField3("6");
        t6.setField4("6");
        t6.setField5("6");
        t6.setField6("6");
        t6.setField7("6");
        t6.setField8("6");
        t6.setField9("6");
        t6.setField10("6");
        t6.setField11("6");
        t6.setField12("6");
        t6.setField13("6");
        t6.setField14("6");
        t6.setField15("6");
        t6.setField16("6");
        t6.setField17("6");
        t6.setField18("6");
        t6.setField19("6");
        t6.setField20("6");
        t6.setField21("6");
        t6.setField22("6");
        t6.setField23("6");
        t6.setField24("6");
        t6.setField25("6");
        list.add(t);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        return list;
    }

    public static String uplaodPicture(String folder, InputStream is) {
        try {
            return uploadImageByFileType(folder, is, CompanyImageFileDirType.COMPANY_LICENSE_IMG_DIR);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static String uploadImageByFileType(String fileName, InputStream is, CompanyImageFileDirType type) throws IOException, FileNotFoundException {
        File f = PersistenceUtil.getTemplateForDownloadFile(fileName, type.getDirectoryName());
        try (FileOutputStream os = new FileOutputStream(f)) {
            byte[] readByte = new byte[1024];
            int length = is.read(readByte);
            while (length > 0) {
                os.write(readByte, 0, length);
                length = is.read(readByte);
            }
            os.flush();
        }
        return fileName;
    }

    public static InputStream getlicenseImg(String imageName) {
        try {
            return getOriginalImagByFileType(imageName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static InputStream getOriginalImagByFileType(String imageName) throws IOException {
        String filePath = System.getProperty("user.home") + File.separator + "." + "myTestFolder"
                + File.separator + CompanyImageFileDirType.COMPANY_LICENSE_IMG_DIR.getDirectoryName() + File.separator + imageName;
        if (new File(filePath).exists()) {
            return new FileInputStream(new File(filePath));
        }
        return null;
    }

    public static void clearCarExportResult() {
        new ExportCarResultProcessor(request.getSession()).startNewImport();
    }

    public static void exportCarByCondition(boolean flag, Date startTime, Date endTime, String keywords) throws IOException, HttpRPCException {
       
//        ExportCarResultProcessor processor = new ExportCarResultProcessor(
//                request.getSession());
//        ExportResult result = processor.getResult();
        int totalCount = 0;
        File zipFile = null;
//        try {
//            CarRecordDao dao = new CarRecordDao();
            //是否把全部内容都导出完毕
//            boolean isExportOver = false;
//            //前一次查询总数
//            int oldTotalCount = 0;
//            //当前页，初始为1
//            int curPage = 1;
//            //到当前页为止可容纳的总条数，以判断是否导出完毕
//            int curTotalCount = 0;
//            //总条数(从数据库查询的list中的数据量)
//            totalCount = queryCarRecordCount();
////            result.setTotalCount(totalCount);
//            //实际已经读的条数
//            int realTotalCount = 0;
//            while (!isExportOver) {//没有读完
//                if (totalCount != 0) {
////                    if (totalCount != oldTotalCount) {//数据库数据改变，总条数发生变化，则重新生成文件
////                        oldTotalCount = totalCount;
////                        curPage = 1;
////                    } else {
////                        curPage = PageResult.recalculateCurPage(totalCount,
////                                curPage, 100);
////                    }
//                    //到当前页的总条数
////                    curTotalCount = curPage * 100;
////                    list = dao.queryCarRecord(key, startDate, endDate,
////                            PageRlistesult.getFromIndex(curPage, 100), 100);
//                    Thread.sleep(5000);
//                    realTotalCount += 1;
//                    if (curTotalCount >= totalCount) {
//                        isExportOver = true;
//                    }
//                    curPage++;
////                    result.setProcessCount(curTotalCount);
////                    processor.getResult().setState(
////                            ImportAndExportResultState.running);
////                    result.setTotalCount(realTotalCount);
//
//                } else {
////                    result.setTotalCount(0);
////                    processor.gotoSucceed();
//                    break;
//                }
//            }
            File file = ExportUtil.generateTempFile(FileType.excel);
            List<CarCheckWarp> list = new ArrayList<>();
            CarCheckWarp c1 = new CarCheckWarp();
            c1.setCarCarColor("1");
            c1.setCarCarModel("1");
            c1.setCarPlateNumber("1");
            c1.setDriverName("1");
            CarCheckWarp c2 = new CarCheckWarp();
            c1.setCarCarColor("2");
            c1.setCarCarModel("2");
            c1.setCarPlateNumber("2");
            c1.setDriverName("2");
            CarCheckWarp c3 = new CarCheckWarp();
            c1.setCarCarColor("3");
            c1.setCarCarModel("3");
            c1.setCarPlateNumber("3");
            c1.setDriverName("3");
            CarCheckWarp c4 = new CarCheckWarp();
            c1.setCarCarColor("4");
            c1.setCarCarModel("4");
            c1.setCarPlateNumber("4");
            c1.setDriverName("4");
            CarCheckWarp c5 = new CarCheckWarp();
            c1.setCarCarColor("5");
            c1.setCarCarModel("5");
            c1.setCarPlateNumber("5");
            c1.setDriverName("5");
            list.add(c1);
            list.add(c2);
            list.add(c3);
            list.add(c4);
            list.add(c5);
            ExportUtil.wirteCarRecordToExcel(file, list);

//        } catch (HttpRPCException | IOException | InterruptedException ex) {
////            result.setTotalCount(0);
////            processor.getResult().setState(ImportAndExportResultState.failed);
////            processor.gotoFailed();
//        } finally {
//            result.setFilePath(zipFile.getAbsolutePath());
//            processor.gotoSucceed();
        }

//    private static  int queryCarRecordCount(){
//        return  5;
//    }
//    public static ExportResult getCarExportResult() {
//        return new ExportCarResultProcessor(request.getSession()).getResult();
//    }

    public static InputStream getExportFile(String filePath) {
        try {
            return new FileInputStream(filePath);
        } catch (FileNotFoundException ex) {
        }
        return null;
    }
}
