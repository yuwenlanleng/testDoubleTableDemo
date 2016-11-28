/*
 * TableCheckBoxCellEditor.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-09-29 10:03:12
 */
package com.nazca.test.renderer;

import com.nazca.test.model.CheckableItem;
import com.nazca.ui.laf.blueocean.NazcaTableCellRenderer;
import com.nazca.ui.model.AbstractSimpleObjectTableModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

/**
 * 考生表格勾选框列的Renderer和Editor
 * @author Wu Jinghua
 */
public class TableCheckBoxCellEditor extends NazcaTableCellRenderer implements TableCellEditor, ItemListener, ActionListener {

    protected EventListenerList myListeners = new EventListenerList();
    protected ChangeEvent myChangeEvent = null;
    private JCheckBox cekBx = new JCheckBox();
    
    public TableCheckBoxCellEditor() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.add(cekBx, BorderLayout.CENTER);
        cekBx.setOpaque(false);
        cekBx.setHorizontalAlignment(CENTER);
        cekBx.addActionListener(this);
        cekBx.addItemListener(this);
        setRequestFocusEnabled(false);
    }
    
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
        
        boolean cekBxIsSelected = false;
        if (value != null) {
            cekBxIsSelected = (Boolean) value;
        }
        cekBx.setSelected(cekBxIsSelected);
        return this;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, drawFocus, row, column);
        boolean cekBxIsSelected = false;
        if (value != null) {
            cekBxIsSelected = (Boolean) value;
        }
         if (isChecked(table, row)) {
            super.checked = true;
        } else {
            super.checked = false;
        }
        cekBx.setSelected(cekBxIsSelected);
        return this;
    }

    public Object getCellEditorValue() {
        return cekBx.isSelected();
    }

    public boolean isCellEditable(EventObject anEvent) {
        if (anEvent instanceof MouseEvent) {
            return ((MouseEvent) anEvent).getClickCount() >= 1;
        }
        return true;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    public void cancelCellEditing() {
        fireEditingCanceled();
    }

    public void addCellEditorListener(CellEditorListener l) {
        myListeners.add(CellEditorListener.class, l);
    }

    public void removeCellEditorListener(CellEditorListener l) {
        myListeners.remove(CellEditorListener.class, l);
    }

    public void itemStateChanged(ItemEvent e) {
        stopCellEditing();
    }

    public void actionPerformed(ActionEvent e) {
        stopCellEditing();
    }
    
    protected void fireEditingStopped() {
        Object[] listeners = myListeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                if (myChangeEvent == null) {
                    myChangeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listeners[i + 1]).editingStopped(myChangeEvent);
            }
        }
    }

    protected void fireEditingCanceled() {
        Object[] listeners = myListeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                if (myChangeEvent == null) {
                    myChangeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listeners[i + 1]).editingCanceled(myChangeEvent);
            }
        }
    }
    
    /**
     * 判断所在行是否被勾选
     * @param table
     * @param row
     * @return 
     */
    private boolean isChecked(JTable table, int row) {
        boolean isChecked = false;
        CheckableItem item = (CheckableItem) ((AbstractSimpleObjectTableModel)table.getModel()).getData(row);
        if (item != null) {
            isChecked = item.isChecked();
        }
        return isChecked;
    }

}
