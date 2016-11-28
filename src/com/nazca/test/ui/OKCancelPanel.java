/*
 * Copyright(c) 2007-2010 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2010-11-15 10:50:46
 */
package com.nazca.test.ui;

import com.nazca.test.util.ErrorCodeFormater;
import com.nazca.ui.NActionPane;
import com.nazca.ui.NComponentStyleTool;
import com.nazca.ui.NLabelMessageTool;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JButton;

/**
 * 通用的确认取消面板
 * @author Zhang Chun Nan
 */
public class OKCancelPanel extends NActionPane {
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton okBtn;
    private List<OKCancelPanelListener> listeners = new CopyOnWriteArrayList<OKCancelPanelListener>();

    /** Creates new form OKCancelPanel */
    public OKCancelPanel() {
        okBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        okBtn.setText("确定");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("取消");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        add(cancelBtn);
        add(okBtn);
        super.getWaitingProcess();
        super.getMsgLabel();
        toAddButtonStyle();
        gotoNormalMode();
    }

    public void setDefaultCancel(){
        this.getRootPane().setDefaultButton(cancelBtn);
    }
    
    public void setDefaultOK(){
        this.getRootPane().setDefaultButton(okBtn);
    }
    
    /**
     * 模拟点击Ok按钮
     */
    public void clickOK() {
        okBtnActionPerformed(null);
    }

    /**
     * 模拟点击取消按钮
     */
    public void clickCancel() {
        cancelBtnActionPerformed(null);
    }

    /**
     * 添加监听器
     * @param lis 
     */
    public void addOKCancelListener(OKCancelPanelListener lis) {
        listeners.add(lis);
    }

    /**
     * 移除监听器
     */
    public void removeAllOKCancelListeners() {
        listeners.clear();
    }

    /**
     * 等待模式
     * @param waitMsg 
     */
    public void gotoWaitMode(String waitMsg) {
        setProgressBarVisible(true);
        setPlanText(waitMsg);
        msgLabel.setVisible(true);
        setButtonsEnabled(false);
    }

    /**
     * 错误模式
     * @param errMsg 
     */
    public void gotoErrorMode(String errMsg) {
        setProgressBarVisible(false);
        setErrorText(errMsg);
        msgLabel.setVisible(true);
        setButtonsEnabled(true);
    }

    /**
     * 错误模式
     * @param errMsg
     * @param errorCode 
     */
    public void gotoErrorMode(String errMsg, int errorCode) {
        setProgressBarVisible(false);
        setErrorInfo(errMsg, errorCode);
        msgLabel.setVisible(true);
        setButtonsEnabled(true);
    }

    /**
     * 警告模式
     * @param warningMsg 
     */
    public void gotoWarningMode(String warningMsg) {
        setProgressBarVisible(false);
        setWarningText(warningMsg);
        msgLabel.setVisible(true);
        setButtonsEnabled(true);
    }

    /**
     * 正常模式
     */
    public void gotoNormalMode() {
        setProgressBarVisible(false);
        msgLabel.setVisible(false);
        setButtonsEnabled(true);
    }

    /**
     * 禁用模式
     */
    public void gotoDisableMode() {
        setProgressBarVisible(false);
        msgLabel.setVisible(false);
        setButtonsEnabled(false);
    }

    /**
     * 成功模式
     * @param msg 
     */
    public void gotoSuccessMode(String msg) {
        setProgressBarVisible(false);
        setSuccessText(msg);
        msgLabel.setVisible(true);
    }

    /**
     * OK按钮文字
     * @param text 
     */
    public void setOKText(String text) {
        okBtn.setText(text);
    }

    /**
     * OK按钮文字
     * @return 
     */
    public String getOKText() {
        return okBtn.getText();
    }

    /**
     * cancel按钮文字
     * @param text 
     */
    public void setCancelText(String text) {
        cancelBtn.setText(text);
    }

    /**
     * cancel按钮文字
     * @return 
     */
    public String getCancelText() {
        return cancelBtn.getText();
    }

    /**
     * 激活/禁用 所有按钮
     * @param flag 
     */
    public void setButtonsEnabled(boolean flag) {
        okBtn.setEnabled(flag);
        cancelBtn.setEnabled(flag);
    }
    
    /**
     * 启用/禁用 确认按钮
     */
    public void setOKButtonEnabled(boolean flag){
        okBtn.setEnabled(flag);
    }

    /**
     * 变成默认按钮样式
     */
    public void toNormalButtonStyle() {
        NComponentStyleTool.normalStyle(okBtn);
    }

    /**
     * 变成添加按钮样式
     */
    public void toAddButtonStyle() {
        NComponentStyleTool.goodNewsStyle(okBtn);
    }

    /**
     * 变成删除按钮样式
     */
    public void toDeleteButtonStyle() {
        NComponentStyleTool.errorStyle(okBtn);
    }

    /**
     * 等待条是否可见
     * @param visible 
     */
    public void setProgressBarVisible(boolean visible) {
        waitingProcess.setVisible(visible);
        waitingProcess.setIndeterminate(visible);
    }

    /**
     * 等待条是否可见
     * @return 
     */
    public boolean isProgressBarVisible() {
        return waitingProcess.isVisible();
    }

    /**
     * 消息条是否可见
     * @param visible 
     */
    public void setMsgLbVisible(boolean visible) {
        waitingProcess.setVisible(visible);
    }

    /**
     * 消息条是否可见
     * @return 
     */
    public boolean isMsgLbVisible() {
        return waitingProcess.isVisible();
    }

    /**
     * 消息文字
     * @param text 
     */
    public void setMsgLbText(String text) {
        setPlanText(text);
    }

    /**
     * 消息文字
     * @return 
     */
    public String getMsgLbText() {
        return msgLabel.getText();
    }

    /**
     * 普通消息文字
     * @param msg 
     */
    public void setPlanText(String msg) {
        NLabelMessageTool.plainMessage(msgLabel, msg);
    }

    /**
     * 蓝色消息文字
     * @param msg 
     */
    public void setInfoText(String msg) {
        NLabelMessageTool.infoMessage(msgLabel, msg);
    }

    /**
     * 黄色消息文字
     * @param msg 
     */
    public void setWarningText(String msg) {
        NLabelMessageTool.warningMessage(msgLabel, msg);
    }

    /**
     * 错误消息文字
     * @param errMsg 
     */
    public void setErrorText(String errMsg) {
        NLabelMessageTool.errorMessage(msgLabel, errMsg);
    }

    /**
     * 错误消息文字
     * @param errMsg
     * @param errorCode 
     */
    public void setErrorInfo(String errMsg, int errorCode) {
        NLabelMessageTool.errorMessage(msgLabel, ErrorCodeFormater.formate(errMsg, errorCode));
    }

    /**
     * 成功消息文字
     * @param msg 
     */
    public void setSuccessText(String msg) {
        NLabelMessageTool.goodNewsMessage(msgLabel, msg);
    }

    /**
     * OK按钮
     * @return 
     */
    public JButton getOKButton() {
        return okBtn;
    }

    /**
     * cancel按钮
     * @return 
     */
    public JButton getCancelButton() {
        return cancelBtn;
    }

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        for (OKCancelPanelListener lis : listeners) {
            lis.onOKClicked();
        }
    }
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        for (OKCancelPanelListener lis : listeners) {
            lis.onCancelClicked();
        }
    }
}
