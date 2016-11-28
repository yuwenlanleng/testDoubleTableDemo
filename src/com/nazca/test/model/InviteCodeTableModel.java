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
import com.nazca.test.pojo.InviteCode;
import com.nazca.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Shi Jie <your.name at your.org>
 */
public class InviteCodeTableModel extends AbstractCheckableListTableModel<InviteCode> {
    public static final int CHECK_BOX = 0;
    public static final int INVITE_CODE = 1;
    public static final int SALES_ID = 2;
    public static final int MOBILE = 3;
    public static final int MAXNUM = 4;
    public static final int EMPLOYNUM = 5;
    private static String[] columns = new String[]{"","邀请码", "所属销售人员", "手机号", "最大使用次数", "已使用次数"};
    private boolean hasChecked = false;
    private int checkedCount = 0;
    private final List<CheckBoxTableIsCheckedListener> listeners = new ArrayList<>();
   
    public InviteCodeTableModel() {
        super(columns);
    }

    public void addListener(CheckBoxTableIsCheckedListener listener) {
        listeners.add(listener);
    }

    public void removeAllListeners() {
        listeners.clear();
    }

    private void fireCheckedCountChanged(int count) {
        for (CheckBoxTableIsCheckedListener lis : listeners) {
            lis.updateCheckedCount(count);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
        CheckableItem<InviteCode> item = dataList.get(rowIndex);
        InviteCode inviteCode = item.getItem();
        switch (columnIndex) {
            case CHECK_BOX:
                return item.isChecked();
            case INVITE_CODE:
                return inviteCode!=null && !StringUtil.isEmpty(inviteCode.getInviteCode()) ? inviteCode.getInviteCode() : "";
            case SALES_ID:
                return inviteCode.getSalesId();
            case MOBILE:
                return inviteCode.getMobile();
            case MAXNUM:
                return inviteCode.getMaxNum();
            case EMPLOYNUM:
                return inviteCode.getEmployNum();
            default:
                return null;
        }
    }

    public List<InviteCode> getInviteCodes() {
        List<InviteCode> list = new LinkedList<>();
        for (CheckableItem<InviteCode> item : dataList) {
            if (item.isChecked()) {
                list.add(item.getItem());
            }
        }
        return list;
    }
    
    public List<CheckableItem<InviteCode>> getUnCheckedItemList() {
        List<CheckableItem<InviteCode>> list = new LinkedList<CheckableItem<InviteCode>>();
        for (CheckableItem<InviteCode> item : dataList) {
            if (!item.isChecked()) {
                list.add(item);
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

    public void setInviteCodes(Collection<InviteCode> students) {
        List<CheckableItem<InviteCode>> cList = new ArrayList<>();
        for (InviteCode stu : students) {
            cList.add(new CheckableItem<>(stu));
        }
        setDatas(cList);
    }

    public void setDatas(Collection<CheckableItem<InviteCode>> datas) {
        List<CheckableItem<InviteCode>> list = (List<CheckableItem<InviteCode>>) datas;
        super.setDatas(list);
        checkedCount = 0;
        fireCheckedCountChanged(checkedCount);
        if (hasChecked) {
            hasChecked = false;
            fireCheckStateChanged(hasChecked);
        }
    }

    public void updateData(CheckableItem<InviteCode> data) {
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
        CheckableItem<InviteCode> item = dataList.get(rowIndex);
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
        for (CheckableItem<InviteCode> item : dataList) {
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
        table.getTableHeader().getColumnModel().getColumn(INVITE_CODE).setPreferredWidth(50);
         table.getTableHeader().getColumnModel().getColumn(SALES_ID).setPreferredWidth(50);
        table.getTableHeader().getColumnModel().getColumn(MOBILE).setPreferredWidth(200);
        table.getTableHeader().getColumnModel().getColumn(MAXNUM).setPreferredWidth(50);
        table.getTableHeader().getColumnModel().getColumn(EMPLOYNUM).setPreferredWidth(200);
       
    }
}
