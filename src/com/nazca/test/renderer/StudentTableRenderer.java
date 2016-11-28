/*
 * StudentTableRenderer.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-09-28 14:54:1
 */
package com.nazca.test.renderer;

import com.nazca.test.model.CheckableItem;
import com.nazca.test.model.StudentTableImmutableModel;
import com.nazca.test.pojo.Entity;
import com.nazca.ui.NLabelMessageTool;
import com.nazca.ui.laf.blueocean.NazcaTableDefaultCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;

/**
 * 考生表格Renderer
 *
 * @author Qiu Dongyue
 */
public class StudentTableRenderer extends NazcaTableDefaultCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        row = table.convertRowIndexToModel(row);
        column = table.convertColumnIndexToModel(column);

        if (isChecked(table, row)) {
            super.checked = true;
        } else {
            super.checked = false;
        }

        setIcon(null);
        switch (column) {
            case StudentTableImmutableModel.FIELD1:
                break;
            case StudentTableImmutableModel.FIELD2:
                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD3:
//                setText(NazcaFormater.getSimpleDateString((Date) value));
//                setToolTipText(NazcaFormater.getSimpleDateString((Date) value));
                break;
            case StudentTableImmutableModel.FIELD4:

                break;
            case StudentTableImmutableModel.FIELD5:
                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD6:

                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD7:

                break;
            case StudentTableImmutableModel.FIELD8:

                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD9:

                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD10:

                break;
            case StudentTableImmutableModel.FIELD11:

                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD12:
                break;
            case StudentTableImmutableModel.FIELD13:

                break;
            case StudentTableImmutableModel.FIELD14:

                break;
            case StudentTableImmutableModel.FIELD15:

                break;
            case StudentTableImmutableModel.FIELD16:

                break;
            case StudentTableImmutableModel.FIELD17:

                break;
            case StudentTableImmutableModel.FIELD18:

                break;
            case StudentTableImmutableModel.FIELD19:

                break;
            case StudentTableImmutableModel.FIELD20:

                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD21:

                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD22:

                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD23:

                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD24:

                setHorizontalAlignment(CENTER);
                break;
            case StudentTableImmutableModel.FIELD25:

                setHorizontalAlignment(CENTER);
                break;
        }
        if (doesStudentHasProblem(table, row)) {
            label.setForeground(NLabelMessageTool.ERROR_COLOR);
        } else {
            label.setForeground(Color.BLACK);
        }

        return this;
    }

    /**
     * 考生校验信息是否有问题
     *
     * @param table
     * @param row
     * @return
     */
    private boolean doesStudentHasProblem(JTable table, int row) {
        CheckableItem<Entity> item = ((StudentTableImmutableModel) table.getModel()).getData(row);
        if (item != null) {
            Entity student = item.getItem();
            if (student != null) {
                return student.getField1() != null;
            }
        }
        return false;
    }

    /**
     * 获取校验描述
     *
     * @param table
     * @param row
     * @return
     */
//    private String getCheckProblemsHTML(JTable table, int row) {
//        CheckableItem<Entity> item = ((StudentTableImmutableModel) table.getModel()).getData(row);
//        if (item != null) {
//            Entity student = item.getItem();
//            if (student != null && student.getField1() != null) {
//                StringBuilder buf = new StringBuilder();
////                List<String> descList = StudentUtil.parseCheckRuleDescs(student.getCheckDesc());
//                List<String> descList = student.getField1();
//                for (int i = 0; i < descList.size(); i++) {
//                    buf.append(descList.get(i)).append(",");
//                }
//                buf.deleteCharAt(buf.length() - 1);
//                return buf.toString();
//            }
//                return student.getCheckDesc();
//        }
//        return null;
//    }

    /**
     * 判断所在行是否被勾选
     *
     * @param table
     * @param row
     * @return
     */
    private boolean isChecked(JTable table, int row) {
        boolean isChecked = false;
        CheckableItem<Entity> item = ((StudentTableImmutableModel) table.getModel()).getData(row);
        if (item != null) {
            isChecked = item.isChecked();
        }
        return isChecked;
    }

    /**
     * 判断所在行考生是否为少数民族
     *
     * @param table
     * @param row
     * @return
     */
//    private boolean isMinority(JTable table, int row) {
//        boolean minority = false;
//        CheckableItem<Entity> item = ((StudentTableImmutableModel) table.getModel()).getData(row);
//        if (item != null) {
//            Entity s = item.getItem();
//            if (s != null) {
//                if (!"01".equals(s.getNationCode()) && !"98".equals(s.getNationCode())) {
//                    minority = true;
//                }
//            }
//        }
//        return minority;
//    }
}
