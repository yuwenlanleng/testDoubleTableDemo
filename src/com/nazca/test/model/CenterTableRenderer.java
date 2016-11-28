/*
 * OrgAndUserSelectorTableRenderer.java
 * 
 * Copyright(c) 2007-2010 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2010-03-14 15:51:05
 */
package com.nazca.jk2c.mgmt.client.model;

import com.nazca.ui.laf.blueocean.NazcaTableDefaultCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 *
 * @author fred
 */
public class CenterTableRenderer extends NazcaTableDefaultCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row, column);
        label.setHorizontalAlignment(SwingConstants.CENTER);
         return this;
    }
 
}
