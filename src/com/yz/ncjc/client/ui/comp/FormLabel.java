/*
 * FormLabel.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-01-05 19:06:11
 */
package com.yz.ncjc.client.ui.comp;

import com.nazca.test.util.ResourceUtil;
import java.awt.Color;
import javax.swing.JLabel;

/**
 * 表单Label
 *
 * @author Zhang Chun Nan
 */
public class FormLabel extends JLabel {

    private static final Color LB_COLOR = Color.decode("#25435F");

    public FormLabel() {
        setForeground(LB_COLOR);
    }

    public void addRequiredIcon() {
        setIcon(ResourceUtil.readIcon("add_member.png"));
    }

    public void removeRequiredIcon() {
        setIcon(null);
    }
}
