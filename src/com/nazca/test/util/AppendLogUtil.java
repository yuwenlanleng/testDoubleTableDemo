/*
 * AppendLogUtil.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-9-23 11:22:25
 */
package com.yz.ncjc.client.util;

import com.nazca.ui.NLabelMessageTool;
import com.nazca.util.StringUtil;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Liu yizhe <lyz@yzhtech.com>
 */
public class AppendLogUtil {
    public static void appendWarnLog(JTextPane logTxPane, String text) {
        appendLog(logTxPane, text, NLabelMessageTool.getWarningColor());
    }

    public static void appendErrorLog(JTextPane logTxPane, String text) {
        appendLog(logTxPane, text, NLabelMessageTool.getErrorColor());
    }

    public static void appendInfoLog(JTextPane logTxPane, String text) {
        appendLog(logTxPane, text, NLabelMessageTool.getPlainColor());
    }

    public static void appendGoodNewsLog(JTextPane logTxPane, String text) {
        appendLog(logTxPane, text, NLabelMessageTool.getGoodNewsColor());
    }

    public static void appendLog(final JTextPane logTxPane, final String text, Color color) {
        final SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, color);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Document doc = logTxPane.getDocument();
                try {
                    doc.insertString(doc.getLength(), text + "\n", attr);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
                logTxPane.scrollRectToVisible(new Rectangle(0, logTxPane.getHeight(), 1, 1));
            }
        });
    }

    public static String UpperCaseFirstLetter(String str) {
        if (!StringUtil.isEmpty(str)) {
            String firstLetter = str.substring(0, 1);
            str = str.replaceFirst(firstLetter, firstLetter.toUpperCase());
        }
        return str;
    }
}
