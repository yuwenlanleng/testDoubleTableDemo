/*
 * PopMenu.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-26 12:38:21
 */
package com.nazca.test.common;

import com.nazca.test.model.AbstractCheckableListTableModel;
import com.nazca.test.model.CheckableItem;
import com.nazca.test.renderer.TableCheckBoxHeaderRenderer;
import java.util.List;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 * 带复选框的表格右键弹出菜单
 * @author Zhou Ning
 * @param <T>
 */
public class PopMenu<T> extends JPopupMenu{
    private javax.swing.JMenuItem selectMenuItem;
    private javax.swing.JMenuItem cancelSelectMenuItem;
    private javax.swing.JPopupMenu.Separator separatorMenuItem;
    private javax.swing.JMenuItem selectAllMenuItem1;
    private javax.swing.JMenuItem inverseSelectMenuItem;
    private javax.swing.JMenuItem cancelSelectAllMenuItem;
    private JTable jTable;
    private AbstractCheckableListTableModel<T> tableModel;
    private TableCheckBoxHeaderRenderer tableCheckBoxHeaderRenderer ;
    
    public PopMenu(){
    }
    public PopMenu(JTable jTable,
            TableCheckBoxHeaderRenderer tableCheckBoxHeaderRenderer){
        this.jTable = jTable;
        this.tableModel = (AbstractCheckableListTableModel<T>) this.jTable.getModel();
        this.tableCheckBoxHeaderRenderer = tableCheckBoxHeaderRenderer;
        initComponent();
        initUI();
        initListener();
    }
    private void initComponent() {
        selectMenuItem = new javax.swing.JMenuItem();
        cancelSelectMenuItem = new javax.swing.JMenuItem();
        separatorMenuItem = new javax.swing.JPopupMenu.Separator();
        selectAllMenuItem1 = new javax.swing.JMenuItem();
        inverseSelectMenuItem = new javax.swing.JMenuItem();
        cancelSelectAllMenuItem = new javax.swing.JMenuItem();
    }
    private void initUI() {
        selectMenuItem.setEnabled(isSelectAbled());
        cancelSelectMenuItem.setEnabled(isCancelSelectAbled());
        selectAllMenuItem1.setEnabled(isSelectAllAbled());
        cancelSelectAllMenuItem.setEnabled(isCancelSelectAllAbled());
    }

    private void initListener() {
        selectMenuItem.setText("选择");
        selectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMenuItemActionPerformed(evt);
            }
        });
        this.add(selectMenuItem);

        cancelSelectMenuItem.setText("取消选择");
        cancelSelectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelSelectMenuItemActionPerformed(evt);
            }
        });
        this.add(cancelSelectMenuItem);
        this.add(separatorMenuItem);

        selectAllMenuItem1.setText("全选");
        selectAllMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllMenuItem1ActionPerformed(evt);
            }
        });
        this.add(selectAllMenuItem1);

        inverseSelectMenuItem.setText("反选");
        inverseSelectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inverseSelectMenuItemActionPerformed(evt);
            }
        });
        this.add(inverseSelectMenuItem);

        cancelSelectAllMenuItem.setText("取消所有选择");
        cancelSelectAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelSelectAllMenuItemActionPerformed(evt);
            }
        });
        this.add(cancelSelectAllMenuItem);
    }
    private void selectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                               
        for (int i : jTable.getSelectedRows()) {
            int index = jTable.convertRowIndexToModel(i);
            tableModel.setValueAt(true, index, 0);
        }
    }                                              

    private void cancelSelectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        for (int i : jTable.getSelectedRows()) {
            int index = jTable.convertRowIndexToModel(i);
            tableModel.setValueAt(false, index, 0);
        }
    }                                                    

    private void selectAllMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        tableCheckBoxHeaderRenderer.setSelected(true);
        this.revalidate();
        this.repaint();
    }                                                  

    private void inverseSelectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        if (tableModel.getCheckedDataList().isEmpty()) {
            if (tableCheckBoxHeaderRenderer.isSelected()) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    tableModel.setValueAt(true, i, 0);
                }
            } else {
                tableCheckBoxHeaderRenderer.setSelected(true);
            }
        } else if (tableModel.getCheckedDataList().size() == tableModel.getRowCount()) {
            if (tableCheckBoxHeaderRenderer.isSelected()) {
                tableCheckBoxHeaderRenderer.setSelected(false);
            } else {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    tableModel.setValueAt(false, i, 0);
                }
            }
        } else if (tableCheckBoxHeaderRenderer.isSelected()) {
            List<CheckableItem<T>> unChecked = tableModel.getUnCheckedItemList();
            tableCheckBoxHeaderRenderer.setSelected(false);
            for (CheckableItem item : unChecked) {
                tableModel.setValueAt(true, tableModel.getDatas().indexOf(item), 0);
            }
        } else {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(!tableModel.getData(i).isChecked(), i, 0);
            }
        }
        this.revalidate();
        this.repaint();
    }                                                     

    private void cancelSelectAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                        
        if (tableCheckBoxHeaderRenderer.isSelected()) {
            tableCheckBoxHeaderRenderer.setSelected(false);
        } else {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(false, i, 0);
            }
        }
        tableCheckBoxHeaderRenderer.setSelected(false);
        this.revalidate();
        this.repaint();
    }   

    public void show(int x, int y) {
        super.show(jTable, x, y);
    }
    private boolean isSelectAbled() {
        for (int i : jTable.getSelectedRows()) {
            int index = jTable.convertRowIndexToModel(i);
            CheckableItem<T> item = tableModel.getData(index);
            if (!item.isChecked()) {
                return true;
            }
        }
        return false;
    }

    private boolean isCancelSelectAbled() {
        for (int i : jTable.getSelectedRows()) {
            int index = jTable.convertRowIndexToModel(i);
            CheckableItem<T> item = tableModel.getData(index);
            if (item.isChecked()) {
                return true;
            }
        }
        return false;
    }

    private boolean isSelectAllAbled() {
        for (CheckableItem<T> item : tableModel.getDatas()) {
            if (item.isChecked() == false) {
                return true;
            }
        }
        return false;
    }

    private boolean isCancelSelectAllAbled() {
        for (CheckableItem<T> item : tableModel.getDatas()) {
            if (item.isChecked() == true) {
                return true;
            }
        }
        return false;
    }
    
}
