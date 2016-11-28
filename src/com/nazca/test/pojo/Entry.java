/*
 * Entry.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-27 15:46:03
 */
package com.nazca.test.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pengruirui
 */
public class Entry implements Serializable {
    private static final long serialVersionUID = -9071112265609263200L;
    /**
     * 确定唯一性的ID
     */
    private String id;    
    
     /**
     * 确定图片
     */
    private String uploadPicture;   

    public String getUploadPicture() {
        return uploadPicture;
    }

    public void setUploadPicture(String uploadPicture) {
        this.uploadPicture = uploadPicture;
    }
    
 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entry other = (Entry) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
