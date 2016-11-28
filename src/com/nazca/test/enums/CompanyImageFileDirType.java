/*
 * CompanyImageFileDirType.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-30 11:06:07
 */
package com.nazca.test.enums;

/**
 * 企业图片类型
 * @author Zhu Mengchao
 */
public enum CompanyImageFileDirType {
    COMPANY_LICENSE_IMG_DIR() {
        @Override
        public String toString() {
            return "营业执照";
        }

        @Override
        public String getDirectoryName() {
            return "companyLicenseImgFile";
        }

    },
    COMPANY_LOGO_IMG_DIR() {
        @Override
        public String toString() {
            return "LOGO图片";
        }

        @Override
        public String getDirectoryName() {
            return "companyLogoImgFile";
        }

    },
    COMPANY_QR_CODE_IMG_DIR() {
        @Override
        public String toString() {
            return "二维码图片";
        }

        @Override
        public String getDirectoryName() {
            return "companyQrCodeImgFile";
        }

    };

    public String getDirectoryName() {
        return null;
    }
}
