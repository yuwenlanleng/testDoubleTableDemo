/*
 * ReadyUserTableModel.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-06-28 10:28:56
 */
package com.nazca.test.model;

import com.nazca.test.listener.CheckBoxTableIsCheckedListener;
import com.nazca.test.pojo.ReadyUserInfo;
import com.nazca.ui.model.AbstractSimpleObjectTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Shi Jie <your.name at your.org>
 */
public class ReadyUserTableModel extends AbstractSimpleObjectTableModel<CheckableItem<ReadyUserInfo>> {
    public static final int CHECK_BOX = 0;
//    public static final int NAME = 1;
//    public static final int EMAIL = 2;
//    public static final int IS_SEND_EMAIL = 3;
//    public static final int MEMO = 4;
    private static String[] columns = new String[]{""};
    private boolean hasChecked = false;
    private int checkedCount = 0;

    private final List<CheckBoxTableIsCheckedListener> listeners = new ArrayList<>();

    public ReadyUserTableModel() {
        super(columns);
    }

    public void addListener(CheckBoxTableIsCheckedListener listener) {
        listeners.add(listener);
    }

    public void removeAllListeners() {
        listeners.clear();
    }

    private void fireCheckStateChanged(boolean checked) {
        for (CheckBoxTableIsCheckedListener lis : listeners) {
            lis.checkStateChanged(checked);
        }
    }

    private void fireCheckedCountChanged(int count) {
        for (CheckBoxTableIsCheckedListener lis : listeners) {
            lis.updateCheckedCount(count);
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        CheckableItem<ReadyUserInfo> item = dataList.get(rowIndex);
//        ReadyUserInfo info = item.getItem();
        switch (columnIndex) {
            case CHECK_BOX:
                return item.isChecked();
//            case NAME:
//                return info!=null && !StringUtil.isEmpty(info.getUserName()) ? info.getUserName() : "";
//            case EMAIL:
//                return info!=null && !StringUtil.isEmpty(info.getEmail()) ? info.getEmail() : "";
//            case IS_SEND_EMAIL:
//                return info!=null &&info.getIsSendEmail() ? "是" : "否";
//            case MEMO:
//                return info!=null && !StringUtil.isEmpty(info.getMemo()) ? info.getMemo() : "";
            default:
                return null;
        }
    }

    public List<ReadyUserInfo> getCheckedReadyUserInfos() {
        List<ReadyUserInfo> list = new LinkedList<>();
        for (CheckableItem<ReadyUserInfo> item : dataList) {
            if (item.isChecked()) {
                list.add(item.getItem());
            }
        }
        return list;
    }

    public void clear() {
        super.clear();
        checkedCount = 0;
        fireCheckedCountChanged(checkedCount);
        if (hasChecked) {
            hasChecked = false;
            fireCheckStateChanged(hasChecked);
        }
    }

    public void setReadyUserInfos(Collection<ReadyUserInfo> students) {
        List<CheckableItem<ReadyUserInfo>> cList = new ArrayList<>();
        for (ReadyUserInfo stu : students) {
            cList.add(new CheckableItem<>(stu));
        }
        setDatas(cList);
    }

    public void setDatas(Collection<CheckableItem<ReadyUserInfo>> datas) {
        List<CheckableItem<ReadyUserInfo>> list = (List<CheckableItem<ReadyUserInfo>>) datas;
        super.setDatas(list);
        checkedCount = 0;
        fireCheckedCountChanged(checkedCount);
        if (hasChecked) {
            hasChecked = false;
            fireCheckStateChanged(hasChecked);
        }
    }

    public void updateData(CheckableItem<ReadyUserInfo> data) {
        boolean checked = false;
        if (data != null) {
            int index = dataList.indexOf(data);
            if (index >= 0 && index < dataList.size()) {
                checked = dataList.get(index).isChecked();
            }
        }
        super.updateData(data);
        if (checked) {
            checkedCount--;
            fireCheckedCountChanged(checkedCount);
            if (hasChecked) {
                if (notChecked()) {
                    hasChecked = false;
                    fireCheckStateChanged(hasChecked);
                }
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case CHECK_BOX:
                return Boolean.class;
            default:
                return super.getClass();
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CheckableItem<ReadyUserInfo> item = dataList.get(rowIndex);
        switch (columnIndex) {
            case CHECK_BOX:
                boolean checked = (Boolean) aValue;
                if (item.isChecked() != checked) {
                    item.setChecked(checked);
                    fireTableRowsUpdated(rowIndex, rowIndex);
                    if (checked) {
                        checkedCount++;
                    } else {
                        checkedCount--;
                    }
                    fireCheckedCountChanged(checkedCount);
                }

                if (hasChecked) {
                    if (notChecked()) {
                        hasChecked = false;
                        fireCheckStateChanged(hasChecked);
                    }
                } else if (checked) {
                    hasChecked = true;
                    fireCheckStateChanged(hasChecked);
                }
                break;
        }
    }

    private boolean notChecked() {
        for (CheckableItem<ReadyUserInfo> item : dataList) {
            if (item.isChecked()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case CHECK_BOX:
                return true;
            default:
                return false;
        }
    }
public void initTblHeaderWidth(JTable table) {
//        table.getTableHeader().getColumnModel().getColumn(NAME).setPreferredWidth(200);
//        table.getTableHeader().getColumnModel().getColumn(EMAIL).setPreferredWidth(200);
//        table.getTableHeader().getColumnModel().getColumn(IS_SEND_EMAIL).setPreferredWidth(30);
//        table.getTableHeader().getColumnModel().getColumn(MEMO).setPreferredWidth(200);
    }

}
