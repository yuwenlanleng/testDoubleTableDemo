/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-03-24 10:13:38
 */
package com.nazca.test.model;

import com.nazca.test.listener.CheckBoxTableIsCheckedListener;
import com.nazca.ui.model.AbstractSimpleObjectTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Qiu Dongyue
 */
public class AbstractCheckableListTableModel<T> extends AbstractSimpleObjectTableModel<CheckableItem<T>> {

    public static final int CHECK_BOX = 0;
    protected boolean hasChecked = false;
    protected int checkedCount = 0;//做过修改
    protected Comparator<CheckableItem<T>> customComparator = null;

    protected AbstractCheckableListTableModel(String[] columns) {
        super(columns);
    }

    @Override
    public void clear() {
        super.clear();
        checkedCount = 0;
        fireUpdateCheckedCount(checkedCount);
        if (hasChecked) {
            hasChecked = false;
            fireCheckStateChanged(hasChecked);
        }
    }

    @Override
    public void setDatas(Collection<CheckableItem<T>> datas) {
        List<CheckableItem<T>> list = (List<CheckableItem<T>>) datas;
        if (customComparator != null) {
            Collections.sort(list, customComparator);
        }
        super.setDatas(list);
        checkedCount = 0;
        fireUpdateCheckedCount(checkedCount);
        if (hasChecked) {
            hasChecked = false;
            fireCheckStateChanged(hasChecked);
        }
    }

    @Override
    public void updateData(CheckableItem<T> data) {
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
            fireUpdateCheckedCount(checkedCount);
            if (hasChecked) {
                if (notChecked()) {
                    hasChecked = false;
                    fireCheckStateChanged(hasChecked);
                }
            }
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CheckableItem<T> item = dataList.get(rowIndex);
        switch (columnIndex) {
            case CHECK_BOX:
                boolean checked = Boolean.parseBoolean(aValue.toString());
                if (item.isChecked() != checked) {
                    item.setChecked(checked);
                    fireTableRowsUpdated(rowIndex, rowIndex);
                    if (checked) {
                        checkedCount++;
                    } else {
                        checkedCount--;
                    }
                    fireUpdateCheckedCount(checkedCount);
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == CHECK_BOX) {
            return dataList.get(rowIndex).isChecked();
        } else {
            return null;
        }
    }

    private boolean notChecked() {
        for (CheckableItem<T> item : dataList) {
            if (item.isChecked()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case CHECK_BOX:
                return true;
            default:
                return false;
        }
    }

    protected void fireCheckStateChanged(boolean checked) {
        for (CheckBoxTableIsCheckedListener lis : listeners) {
            lis.checkStateChanged(checked);
        }
    }

    protected void fireUpdateCheckedCount(int count) {
        for (CheckBoxTableIsCheckedListener lis : listeners) {
            lis.updateCheckedCount(count);
        }
    }

    private List<CheckBoxTableIsCheckedListener> listeners = new ArrayList<CheckBoxTableIsCheckedListener>();

    public void addListener(CheckBoxTableIsCheckedListener listener) {
        listeners.add(listener);
    }

    public void removeAllListeners() {
        listeners.clear();
    }

    public List<T> getAllDataList() {
        List<T> list = new LinkedList<T>();
        for (CheckableItem<T> item : dataList) {
            list.add(item.getItem());
        }
        return list;
    }

    public List<CheckableItem<T>> getCheckedItemList() {
        List<CheckableItem<T>> list = new LinkedList<CheckableItem<T>>();
        for (CheckableItem<T> item : dataList) {
            if (item.isChecked()) {
                list.add(item);
            }
        }
        return list;
    }

    public List<CheckableItem<T>> getUnCheckedItemList() {
        List<CheckableItem<T>> list = new LinkedList<CheckableItem<T>>();
        for (CheckableItem<T> item : dataList) {
            if (!item.isChecked()) {
                list.add(item);
            }
        }
        return list;
    }

    public List<T> getCheckedDataList() {
        List<T> list = new LinkedList<T>();
        for (CheckableItem<T> item : dataList) {
            if (item.isChecked()) {
                list.add(item.getItem());
            }
        }
        return list;
    }

    public List<T> getUnCheckedDataList() {
        List<T> list = new LinkedList<T>();
        for (CheckableItem<T> item : dataList) {
            if (!item.isChecked()) {
                list.add(item.getItem());
            }
        }
        return list;
    }
}
