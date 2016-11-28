/*
 * Copyright(c) 2007-2011 by Yingzhi Tech All Rights Reserved
 *
 * Created at 2011-10-18 11:28:31
 */
package com.nazca.test.util;

import com.nazca.test.enums.FileType;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Wu Jinghua <wjh@yzhtech.com>
 */
public class FileFilterFactory {
    public final static String xlsx = "xlsx";
    public final static String XLSX = "XLSX";
//    public final static String xlsSuffix = ".xlsx";
    public final static String xlsxSuffix = ".xlsx";
    
    
    public static FileFilter getFilter(FileType type) {
        return new FileTypeFilter(type);
    }

    private static class FileTypeFilter extends FileFilter implements java.io.FileFilter {
        private FileType type = null;

        public FileTypeFilter(FileType type) {
            this.type = type;
        }
        
        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String extension = getExtension(f);
            if (extension != null) {
                if (extension.equals(type.getExtensionName())) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return type.name() + "文件";
        }
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}
