/*
 * ScaleCardPicButtonUI.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-24 14:37:40
 */
package com.nazca.test.util;

import com.nazca.test.enums.ScalePicBtnEnum;
import com.nazca.ui.GraphicsTool;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author pengruirui
 */
public class ScaleCardPicButtonUI extends BasicButtonUI {

    private Color normalBgColor = new Color(0xffffff);
    private Color rolloverBgColor = new Color(0xD8E9FB);
    private Color pressedBgColor = new Color(0x3E91D4);
    private BufferedImage icon = null;
    String hoverSuffix = "-hover.png";
    String pressedSuffix = "-pressed.png";
    String normalSuffix = "-normal.png";
    String forbiddenSuffix = "-forbidden.png";

    private ScalePicBtnEnum btnEnum = null;
    private BufferedImage[] temp = new BufferedImage[4];

    public ScaleCardPicButtonUI(ScalePicBtnEnum btnEnum) {
        this.btnEnum = btnEnum;
        temp[0] = ResourceUtil.readImage(btnEnum.name() + pressedSuffix);
        temp[1] = ResourceUtil.readImage(btnEnum.name() + hoverSuffix);
        temp[2] = ResourceUtil.readImage(btnEnum.name() + normalSuffix);
        if (btnEnum.equals(ScalePicBtnEnum.big) || btnEnum.equals(ScalePicBtnEnum.small)) {
            temp[3] = ResourceUtil.readImage(btnEnum.name() + forbiddenSuffix);
        }
    }

    static {
//        GraphicsTool.defaultAnimatorTimingSource();
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);
        return d;
    }

    @Override
    public void installUI(final JComponent c) {
        super.installUI(c);
        c.setBorder(null);
    }

    @Override
    public void uninstallUI(JComponent c) {
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        JButton b = (JButton) c;
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints org = g2d.getRenderingHints();
        GraphicsTool.setQuanlifiedGraphics(g2d);
        int w = b.getWidth();
        int h = b.getHeight();
        //获得鼠标的状态
        ButtonModel bm = b.getModel();
        //背景颜色
        if (bm.isPressed()) {
            icon = temp[0];
            g2d.setColor(pressedBgColor);
        } else if (bm.isRollover()) {
            icon = temp[1];
            g2d.setColor(rolloverBgColor);
        } else {
            icon = temp[2];
            g2d.setColor(normalBgColor);
        }
        if (!b.isEnabled()) {
            icon = temp[3];
        }
        g2d.fillRect(0, 0, w, h);
        g2d.drawImage(icon, 6, 6, b);
        g2d.setRenderingHints(org);
    }

    @Override
    protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
    }

    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
    }

    @Override
    protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
    }

}
