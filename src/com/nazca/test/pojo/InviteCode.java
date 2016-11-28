/*
 * InviteCode.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-06-20 11:59:24
 */
package com.nazca.test.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Lijin
 */
public class InviteCode implements Serializable {

    private static final long serialVersionUID = -5010551761875436403L;
    /**
     * 主键
     */
    private String id;
    /**
     * 所属销售人员
     */
    private String salesId;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 最大使用次数
     */
    private Integer maxNum;
    /**
     * 已使用次数
     */
    private Integer employNum;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 最新使用时间
     */
    private Date latestUseTime;
    /**
     * 数据库中不创建字段
     */
    private String uSMSName;

    public String getuSMSName() {
        return uSMSName;
    }

    public void setuSMSName(String uSMSName) {
        this.uSMSName = uSMSName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLatestUseTime() {
        return latestUseTime;
    }

    public void setLatestUseTime(Date latestUseTime) {
        this.latestUseTime = latestUseTime;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getEmployNum() {
        return employNum;
    }

    public void setEmployNum(Integer employNum) {
        this.employNum = employNum;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final InviteCode other = (InviteCode) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
