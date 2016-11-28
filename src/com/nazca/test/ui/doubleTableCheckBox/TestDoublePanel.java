/*
 * TestDoublePanel.java
 * jLabel7
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-09-27 14:02:22
 */
package com.nazca.test.ui.doubleTableCheckBox;

import com.nazca.test.agent.GetStudentsInfoAgent;
import com.nazca.test.common.PopMenu;
import com.nazca.test.listener.*;
import com.nazca.test.model.CheckableItem;
import com.nazca.test.model.StudentTableImmutableModel;
import com.nazca.test.pojo.Entity;
import com.nazca.test.pojo.InviteCode;
import com.nazca.test.renderer.StudentTableRenderer;
import com.nazca.test.renderer.TableCheckBoxCellEditor;
import com.nazca.test.renderer.TableCheckBoxHeaderRenderer;
import com.nazca.test.util.ComparatorTool;
import com.nazca.ui.*;
import com.nazca.ui.laf.border.IconLabelBorder;
import com.nazca.ui.table.CheckBoxTable;
import com.nazca.ui.util.CardLayoutWrapper;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class TestDoublePanel extends javax.swing.JPanel {

    private JTable checkBoxTableComp = null;
    private StudentTableImmutableModel tableModel = null;
    private StudentTableRenderer tableRenderer = null;
    private TableRowSorter<TableModel> rowSorter = null;
//    private CopyJTableToClipBoardAdapter excelAdapter = null;
//    private final NToolTip tipForLockBtn = null;
    private TableCheckBoxCellEditor tableCheckBoxCellRenderer = null;
    private TableCheckBoxCellEditor tableCheckBoxCellEditor = null;
    private TableCheckBoxHeaderRenderer tableCheckBoxHeaderRenderer = null;
    private GetStudentsInfoAgent getStudentsAgent = null;
    private final NInternalDiag<Object, JComponent> diagPrint = null;
    private CheckableItem<Entity> en = null;
    private CardLayoutWrapper rightCard = null;
    private IconLabelBorder leftBorder = null;
    private IconLabelBorder rightBorder = null;

    /**
     * Creates new form StudentOfCityMgmtPanel
     */
    public TestDoublePanel() {
        initComponents();
        initUI();
        initCommon();
        initModelAndRenderer();
        initAgentAndListener();
        getStudentsAgent.start();
    }

    private void initCommon() {
        checkBoxTableComp = new CheckBoxTable();
        rightCard = new CardLayoutWrapper(rightPanel);
    }

    private void initUI() {
        leftBorder = new IconLabelBorder(getClass().getResource(//左右面板标题
                "/com/nazca/test/res/32.png"), "信息列表");
        leftPanel.setBorder(leftBorder);
        rightBorder = new IconLabelBorder(getClass().getResource(
                "/com/nazca/test/res/32.png"), "详细信息");
        rightPanel.setBorder(rightBorder);
    }

    private void initModelAndRenderer() {
        tableModel = new StudentTableImmutableModel();
        checkBoxTableComp.setModel(tableModel);
        studentTableComp.setModel(tableModel);
        UIUtilities.setTableColumnFixedSize(checkBoxTableComp, tableModel.CHECK_BOX, 24);
        UIUtilities.setTableColumnFixedSize(checkBoxTableComp, tableModel.FIELD1, 54);
        tableRenderer = new StudentTableRenderer();
        checkBoxTableComp.setDefaultRenderer(Object.class, tableRenderer);
        studentTableComp.setDefaultRenderer(Object.class, tableRenderer);
//        excelAdapter = new CopyJTableToClipBoardAdapter(studentTableComp);
        tableCheckBoxCellRenderer = new TableCheckBoxCellEditor();
        checkBoxTableComp.getColumnModel().getColumn(StudentTableImmutableModel.CHECK_BOX).setCellRenderer(
                tableCheckBoxCellRenderer);
        tableCheckBoxCellEditor = new TableCheckBoxCellEditor();
        checkBoxTableComp.getColumnModel().getColumn(StudentTableImmutableModel.CHECK_BOX).setCellEditor(
                tableCheckBoxCellEditor);
//        checkBoxTableComp.getColumnModel().getColumn(StudentTableImmutableModel.FIELD1).setCellEditor(
//                tableCheckBoxCellEditor);

        tableCheckBoxHeaderRenderer = new TableCheckBoxHeaderRenderer(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                tableCheckBoxCellEditor.stopCellEditing();
                Object source = e.getSource();
                if (source instanceof AbstractButton == false) {
                    return;
                }
                boolean checked = e.getStateChange() == ItemEvent.SELECTED;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    tableModel.setValueAt(checked, i, 0);
                }
            }
        }, checkBoxTableComp);
        checkBoxTableComp.getColumnModel().getColumn(StudentTableImmutableModel.CHECK_BOX).setHeaderRenderer(
                tableCheckBoxHeaderRenderer);
        TableColumnModel columnModel1 = checkBoxTableComp.getColumnModel();
        TableColumnModel columnModel2 = studentTableComp.getColumnModel();
        for (int i = columnModel1.getColumnCount() - 1; i > 1; i--) {
            columnModel1.removeColumn(columnModel1.getColumn(i));
        }
        columnModel2.removeColumn(columnModel2.getColumn(1));
        columnModel2.removeColumn(columnModel2.getColumn(0));
        rowSorter = UIUtilities.generateAndSetTriStateRowSorter(studentTableComp, tableModel);
        checkBoxTableComp.setRowSorter(rowSorter);
        rowSorter.setComparator(StudentTableImmutableModel.FIELD1, ComparatorTool.getStringComparator);
        //TODO 临时解决双选择的问题
        //设置行和列被选择
//        checkBoxTableComp.setRowSelectionAllowed(false);
//        checkBoxTableComp.setColumnSelectionAllowed(false);
        jScrollPane2.setRowHeaderView(checkBoxTableComp);
        jScrollPane2.setCorner(JScrollPane.UPPER_LEFT_CORNER, checkBoxTableComp.getTableHeader());
        jScrollPane2.setViewportView(studentTableComp);
        jScrollPane2.getRowHeader().setPreferredSize(new Dimension(78, 24));
        checkBoxTableComp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        studentTableComp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        checkBoxTableComp.getTableHeader().setReorderingAllowed(false);
        checkBoxTableComp.getTableHeader().setResizingAllowed(false);
        //设置左右表格公用一个model，左侧被选择，右侧也被选择
        checkBoxTableComp.setSelectionModel(studentTableComp.getSelectionModel());
    }

    private void initAgentAndListener() {
        getStudentsAgent = new GetStudentsInfoAgent();
        getStudentsAgent.addListener(getStudentsLis);
        tableModel.addListener(new CheckBoxTableIsCheckedListener() {
            @Override
            public void checkStateChanged(boolean checked) {
            }

            @Override
            public void updateCheckedCount(int count) {
                if (count >= tableModel.getRowCount()) {
                    tableCheckBoxCellEditor.stopCellEditing();
                    tableCheckBoxHeaderRenderer.setSelected(true);
                    revalidate();
                    repaint();
                } else if (count == 0) {
                    tableCheckBoxCellEditor.stopCellEditing();
                    tableCheckBoxHeaderRenderer.setSelected(false);
                    revalidate();
                    repaint();
                }
            }
        });
//        tableModel.addListener(new CheckBoxTableIsCheckedListener() {
//            @Override
//            public void checkStateChanged(boolean checked) {
//            }
//
//            @Override
//            public void updateCheckedCount(int count) {
//                if (count == tableModel.getRowCount() && count > 0) {
//                    tableCheckBoxCellEditor.stopCellEditing();
//                    tableCheckBoxHeaderRenderer.setSelected(true);
//                    revalidate();
//                    repaint();
//                } else {
//                    //记录勾掉的checkbox，勾掉所有，然后把其它的给勾上
//                    List<CheckableItem<Entity>> checkedDataList = new ArrayList<>();
//                    checkedDataList.addAll(tableModel.getCheckedItemList());
//                    tableCheckBoxHeaderRenderer.setSelected(false);
//                    List<Integer> list = new ArrayList<>();
//                    for (CheckableItem<Entity> entity : checkedDataList) {
//                        list.add(tableModel.getDataRow(entity));
//                    }
//                    for (int idx : list) {
//                        tableModel.setValueAt(true, idx, 0);
//                    }
//                    revalidate();
//                    repaint();
//                }
//            }
//        });
        studentTableComp.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    rightCard.show(CardLayoutWrapper.CONTENT);//切换面板
                    int selectedCount = studentTableComp.getSelectedRowCount();
                    if (selectedCount == 1) {
                        int index = studentTableComp.getSelectedRow();
                        if (index >= 0) {
                            index = studentTableComp.convertRowIndexToModel(index);
                            en = tableModel.getData(index);
                            testTxtFld.setText(en.getItem().getField1());
                            fild1TxdFld.setText(en.getItem().getField12());
                        }
                    } else if (selectedCount > 1) {
                        //TODO
//                        int[] rows=studentTableComp.getSelectedRows();
//                        studentTableComp.getSelectionModel().get;
                        nWaitingPanel1.setIndeterminate(true);
                        nWaitingPanel1.showWaitingMode();
                        rightCard.show(CardLayoutWrapper.WAIT);
                        nWaitingPanel1.showMsgMode("请选择一行数据", 0, NWaitingPanel.MSG_MODE_INFO);
                    } else {
                        //TODO
                        nWaitingPanel1.setIndeterminate(true);
                        nWaitingPanel1.showWaitingMode();
                        rightCard.show(CardLayoutWrapper.WAIT);
                        nWaitingPanel1.showMsgMode("请选择一行数据", 0, NWaitingPanel.MSG_MODE_INFO);
//                        studentTableComp.getSelectionModel().setSelectionInterval(0, 0);
                    }
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        importMenu = new javax.swing.JPopupMenu();
        importItem1 = new javax.swing.JMenuItem();
        importItem2 = new javax.swing.JMenuItem();
        importItem3 = new javax.swing.JMenuItem();
        importItem4 = new javax.swing.JMenuItem();
        exportMenu = new javax.swing.JPopupMenu();
        exportItem1 = new javax.swing.JMenuItem();
        exportItem2 = new javax.swing.JMenuItem();
        exportItem3 = new javax.swing.JMenuItem();
        exportItem4 = new javax.swing.JMenuItem();
        exportItem5 = new javax.swing.JMenuItem();
        exportItem6 = new javax.swing.JMenuItem();
        exportItem7 = new javax.swing.JMenuItem();
        signupMenu = new javax.swing.JPopupMenu();
        signupItem1 = new javax.swing.JMenuItem();
        signupItem2 = new javax.swing.JMenuItem();
        printMenu = new javax.swing.JPopupMenu();
        printItem1 = new javax.swing.JMenuItem();
        printItem2 = new javax.swing.JMenuItem();
        printItem6 = new javax.swing.JMenuItem();
        printItem3 = new javax.swing.JMenuItem();
        printItem4 = new javax.swing.JMenuItem();
        printItem5 = new javax.swing.JMenuItem();
        popMenu = new javax.swing.JPopupMenu();
        selectMenuItem = new javax.swing.JMenuItem();
        cancelSelectMenuItem = new javax.swing.JMenuItem();
        separatorMenuItem = new javax.swing.JPopupMenu.Separator();
        selectAllMenuItem1 = new javax.swing.JMenuItem();
        inverseSelectMenuItem = new javax.swing.JMenuItem();
        cancelSelectAllMenuItem = new javax.swing.JMenuItem();
        separatorMenuItem2 = new javax.swing.JPopupMenu.Separator();
        copyMenuItem = new javax.swing.JMenuItem();
        tabPane = new javax.swing.JTabbedPane();
        printStudentSignupConfirmPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        printMessageLbl = new javax.swing.JLabel();
        nActionPane1 = new com.nazca.ui.NActionPane();
        closeBtn = new javax.swing.JButton();
        printToPrinterBtn = new javax.swing.JButton();
        printToFileBtn = new javax.swing.JButton();
        tablePane = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentTableComp = new javax.swing.JTable();
        rightPanel = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        testTxtFld = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fild1TxdFld = new javax.swing.JTextField();
        nWaitingPanel1 = new com.nazca.ui.NWaitingPanel();

        importItem1.setText("导入网报数据");
        importItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importItem1ActionPerformed(evt);
            }
        });
        importMenu.add(importItem1);

        importItem2.setText("导入户籍比对数据");
        importItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importItem2ActionPerformed(evt);
            }
        });
        importMenu.add(importItem2);

        importItem3.setText("关联替换");
        importItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importItem3ActionPerformed(evt);
            }
        });
        importMenu.add(importItem3);

        importItem4.setText("导入退役士兵网报信息");
        importItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importItem4ActionPerformed(evt);
            }
        });
        importMenu.add(importItem4);

        exportItem1.setText("导出考生数据");
        exportItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportItem1ActionPerformed(evt);
            }
        });
        exportMenu.add(exportItem1);

        exportItem2.setText("导出户籍比对数据");
        exportItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportItem2ActionPerformed(evt);
            }
        });
        exportMenu.add(exportItem2);

        exportItem3.setText("导出照片");
        exportItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportItem3ActionPerformed(evt);
            }
        });
        exportMenu.add(exportItem3);

        exportItem4.setText("导出考生简历信息");
        exportItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportItem4ActionPerformed(evt);
            }
        });
        exportMenu.add(exportItem4);

        exportItem5.setText("导出考生家庭成员信息");
        exportItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportItem5ActionPerformed(evt);
            }
        });
        exportMenu.add(exportItem5);

        exportItem6.setText("关联导出");
        exportItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportItem6ActionPerformed(evt);
            }
        });
        exportMenu.add(exportItem6);

        exportItem7.setText("导出体检表信息");
        exportItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportItem7ActionPerformed(evt);
            }
        });
        exportMenu.add(exportItem7);

        signupItem1.setText("新增考生");
        signupItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupItem1ActionPerformed(evt);
            }
        });
        signupMenu.add(signupItem1);

        signupItem2.setText("复制考生");
        signupItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupItem2ActionPerformed(evt);
            }
        });
        signupMenu.add(signupItem2);

        printItem1.setText("批量打印核对单");
        printItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printItem1ActionPerformed(evt);
            }
        });
        printMenu.add(printItem1);

        printItem2.setText("批量打印体检表");
        printItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printItem2ActionPerformed(evt);
            }
        });
        printMenu.add(printItem2);

        printItem6.setText("批量打印报名信息单");
        printItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printItem6ActionPerformed(evt);
            }
        });
        printMenu.add(printItem6);

        printItem3.setText("批量打印高水平准考证");
        printItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printItem3ActionPerformed(evt);
            }
        });
        printMenu.add(printItem3);

        printItem4.setText("批量打印体育专业考试素质准考证和专项准考证");
        printItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printItem4ActionPerformed(evt);
            }
        });
        printMenu.add(printItem4);

        printItem5.setText("批量打印体优生和等级运动员测试准考证");
        printItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printItem5ActionPerformed(evt);
            }
        });
        printMenu.add(printItem5);

        selectMenuItem.setText("选择");
        selectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMenuItemActionPerformed(evt);
            }
        });
        popMenu.add(selectMenuItem);

        cancelSelectMenuItem.setText("取消选择");
        cancelSelectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelSelectMenuItemActionPerformed(evt);
            }
        });
        popMenu.add(cancelSelectMenuItem);
        popMenu.add(separatorMenuItem);

        selectAllMenuItem1.setText("全选");
        selectAllMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllMenuItem1ActionPerformed(evt);
            }
        });
        popMenu.add(selectAllMenuItem1);

        inverseSelectMenuItem.setText("反选");
        inverseSelectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inverseSelectMenuItemActionPerformed(evt);
            }
        });
        popMenu.add(inverseSelectMenuItem);

        cancelSelectAllMenuItem.setText("取消所有选择");
        cancelSelectAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelSelectAllMenuItemActionPerformed(evt);
            }
        });
        popMenu.add(cancelSelectAllMenuItem);
        popMenu.add(separatorMenuItem2);

        copyMenuItem.setText("复制");
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        popMenu.add(copyMenuItem);

        tabPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        printStudentSignupConfirmPanel.setLayout(new java.awt.BorderLayout());

        printMessageLbl.setText("    您确定要批量打印报名信息单吗？");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printMessageLbl)
                .addContainerGap(336, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printMessageLbl)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        printStudentSignupConfirmPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        closeBtn.setText("关闭");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        nActionPane1.add(closeBtn);

        printToPrinterBtn.setText("打印到打印机");
        printToPrinterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printToPrinterBtnActionPerformed(evt);
            }
        });
        nActionPane1.add(printToPrinterBtn);

        printToFileBtn.setText("打印到文件");
        printToFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printToFileBtnActionPerformed(evt);
            }
        });
        nActionPane1.add(printToFileBtn);

        printStudentSignupConfirmPanel.add(nActionPane1, java.awt.BorderLayout.SOUTH);

        setLayout(new java.awt.BorderLayout());

        tablePane.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setResizeWeight(0.8);

        leftPanel.setPreferredSize(new java.awt.Dimension(2000, 534));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        studentTableComp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        studentTableComp.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        studentTableComp.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        studentTableComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentTableCompMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(studentTableComp);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 2007, Short.MAX_VALUE)
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(leftPanel);

        rightPanel.setPreferredSize(new java.awt.Dimension(100, 558));
        rightPanel.setLayout(new java.awt.CardLayout());

        contentPanel.setPreferredSize(new java.awt.Dimension(115, 558));

        jLabel1.setText("测试");

        testTxtFld.setText("jTextField1");

        jLabel2.setText("字段1");

        fild1TxdFld.setText("jTextField2");
        fild1TxdFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fild1TxdFldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(testTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fild1TxdFld, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(testTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fild1TxdFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(500, Short.MAX_VALUE))
        );

        rightPanel.add(contentPanel, "CONTENT");
        rightPanel.add(nWaitingPanel1, "WAIT");

        jSplitPane1.setRightComponent(rightPanel);

        tablePane.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        add(tablePane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void studentTableCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentTableCompMouseClicked
//    CheckableItem<Entity> entity;
//    if (SwingUtilities.isRightMouseButton(evt)) {
//        selectMenuItem.setEnabled(isSelectAbled());
//        cancelSelectMenuItem.setEnabled(isCancelSelectAbled());
//        selectAllMenuItem1.setEnabled(isSelectAllAbled());
//        cancelSelectAllMenuItem.setEnabled(isCancelSelectAllAbled());
//        popMenu.show(studentTableComp, evt.getX(), evt.getY());
//    } else if (UIUtilities.isLeftDoubleClick(evt)) {
//        int[] rows = studentTableComp.getSelectedRows();
//        boolean flag = true;
//        for (int index : rows) {
//            index = studentTableComp.convertRowIndexToModel(index);
//            entity = tableModel.getData(index);
//            if (!entity.isChecked()) {
//                flag = false;
//            }
//        }
//        if (flag) {
//            tableCheckBoxHeaderRenderer.setSelected(true);
//            this.revalidate();
//            this.repaint();
//        }
//        studentTableComp.getSelectionModel().get;
//    }
    if (SwingUtilities.isRightMouseButton(evt)) {//鼠标右键单击
        new PopMenu<InviteCode>(studentTableComp, tableCheckBoxHeaderRenderer).show(evt.getX(), evt.getY());
    } else if (UIUtilities.isLeftDoubleClick(evt)) {
    }
}//GEN-LAST:event_studentTableCompMouseClicked

//    private boolean isSelectAbled() {
//        for (int i : studentTableComp.getSelectedRows()) {
//            int index = studentTableComp.convertRowIndexToModel(i);
//            CheckableItem<Entity> item = tableModel.getData(index);
//            if (!item.isChecked()) {
//                return true;
//            }
//        }
//        return false;
//    }
//    private boolean isCancelSelectAbled() {
//        for (int i : studentTableComp.getSelectedRows()) {
//            int index = studentTableComp.convertRowIndexToModel(i);
//            CheckableItem<Entity> item = tableModel.getData(index);
//            if (item.isChecked()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isSelectAllAbled() {
//        for (CheckableItem<Entity> item : tableModel.getDatas()) {
//            if (item.isChecked() == false) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isCancelSelectAllAbled() {
//        for (CheckableItem<Entity> item : tableModel.getDatas()) {
//            if (item.isChecked() == true) {
//                return true;
//            }
//        }
//        return false;
//    }

    private void importItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importItem1ActionPerformed
    }//GEN-LAST:event_importItem1ActionPerformed

    private void importItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importItem2ActionPerformed
    }//GEN-LAST:event_importItem2ActionPerformed

    private void importItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importItem3ActionPerformed
    }//GEN-LAST:event_importItem3ActionPerformed

    private void exportItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportItem1ActionPerformed
    }//GEN-LAST:event_exportItem1ActionPerformed

    private void exportItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportItem2ActionPerformed
    }//GEN-LAST:event_exportItem2ActionPerformed

    private void signupItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupItem1ActionPerformed
    }//GEN-LAST:event_signupItem1ActionPerformed

    private void signupItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupItem2ActionPerformed
    }//GEN-LAST:event_signupItem2ActionPerformed

    private void printItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printItem1ActionPerformed

    }//GEN-LAST:event_printItem1ActionPerformed

    private void printItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printItem2ActionPerformed

    }//GEN-LAST:event_printItem2ActionPerformed

    private void selectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMenuItemActionPerformed
        tableCheckBoxCellEditor.stopCellEditing();
        for (int i : studentTableComp.getSelectedRows()) {
            int index = studentTableComp.convertRowIndexToModel(i);
            tableModel.setValueAt(true, index, 0);
        }
    }//GEN-LAST:event_selectMenuItemActionPerformed

    private void cancelSelectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelSelectMenuItemActionPerformed
        tableCheckBoxCellEditor.stopCellEditing();
        for (int i : studentTableComp.getSelectedRows()) {
            int index = studentTableComp.convertRowIndexToModel(i);
            tableModel.setValueAt(false, index, 0);
        }
    }//GEN-LAST:event_cancelSelectMenuItemActionPerformed

    private void selectAllMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllMenuItem1ActionPerformed
        tableCheckBoxCellEditor.stopCellEditing();
        tableCheckBoxHeaderRenderer.setSelected(true);
        this.revalidate();
        this.repaint();

    }//GEN-LAST:event_selectAllMenuItem1ActionPerformed

    private void inverseSelectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inverseSelectMenuItemActionPerformed
        tableCheckBoxCellEditor.stopCellEditing();
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
            List<CheckableItem<Entity>> unChecked = tableModel.getUnCheckedItemList();
            tableCheckBoxHeaderRenderer.setSelected(false);
            for (CheckableItem<Entity> item : unChecked) {
                tableModel.setValueAt(true, tableModel.getDatas().indexOf(item), 0);
            }
        } else {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(!tableModel.getData(i).isChecked(), i, 0);
            }
        }
        tableCheckBoxHeaderRenderer.setSelected(false);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_inverseSelectMenuItemActionPerformed

    private void cancelSelectAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelSelectAllMenuItemActionPerformed
        tableCheckBoxCellEditor.stopCellEditing();
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
    }//GEN-LAST:event_cancelSelectAllMenuItemActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
//        excelAdapter.exportToClipBoard();
    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void exportItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportItem3ActionPerformed
    }//GEN-LAST:event_exportItem3ActionPerformed

    private void exportItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportItem4ActionPerformed
    }//GEN-LAST:event_exportItem4ActionPerformed

    private void exportItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportItem5ActionPerformed
    }//GEN-LAST:event_exportItem5ActionPerformed

    private void printItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printItem3ActionPerformed
    }//GEN-LAST:event_printItem3ActionPerformed

    private void printItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printItem4ActionPerformed
    }//GEN-LAST:event_printItem4ActionPerformed

    private void printItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printItem5ActionPerformed
    }//GEN-LAST:event_printItem5ActionPerformed

    private void exportItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportItem6ActionPerformed
    }//GEN-LAST:event_exportItem6ActionPerformed

    private void importItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importItem4ActionPerformed
    }//GEN-LAST:event_importItem4ActionPerformed

    private void printItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printItem6ActionPerformed
    }//GEN-LAST:event_printItem6ActionPerformed

    private void exportItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportItem7ActionPerformed
    }//GEN-LAST:event_exportItem7ActionPerformed

    private void printToFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printToFileBtnActionPerformed
    }//GEN-LAST:event_printToFileBtnActionPerformed

    private void printToPrinterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printToPrinterBtnActionPerformed
    }//GEN-LAST:event_printToPrinterBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
    }//GEN-LAST:event_closeBtnActionPerformed

    private void fild1TxdFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fild1TxdFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fild1TxdFldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cancelSelectAllMenuItem;
    private javax.swing.JMenuItem cancelSelectMenuItem;
    private javax.swing.JButton closeBtn;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem exportItem1;
    private javax.swing.JMenuItem exportItem2;
    private javax.swing.JMenuItem exportItem3;
    private javax.swing.JMenuItem exportItem4;
    private javax.swing.JMenuItem exportItem5;
    private javax.swing.JMenuItem exportItem6;
    private javax.swing.JMenuItem exportItem7;
    private javax.swing.JPopupMenu exportMenu;
    private javax.swing.JTextField fild1TxdFld;
    private javax.swing.JMenuItem importItem1;
    private javax.swing.JMenuItem importItem2;
    private javax.swing.JMenuItem importItem3;
    private javax.swing.JMenuItem importItem4;
    private javax.swing.JPopupMenu importMenu;
    private javax.swing.JMenuItem inverseSelectMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel leftPanel;
    private com.nazca.ui.NActionPane nActionPane1;
    private com.nazca.ui.NWaitingPanel nWaitingPanel1;
    private javax.swing.JPopupMenu popMenu;
    private javax.swing.JMenuItem printItem1;
    private javax.swing.JMenuItem printItem2;
    private javax.swing.JMenuItem printItem3;
    private javax.swing.JMenuItem printItem4;
    private javax.swing.JMenuItem printItem5;
    private javax.swing.JMenuItem printItem6;
    private javax.swing.JPopupMenu printMenu;
    private javax.swing.JLabel printMessageLbl;
    private javax.swing.JPanel printStudentSignupConfirmPanel;
    private javax.swing.JButton printToFileBtn;
    private javax.swing.JButton printToPrinterBtn;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JMenuItem selectAllMenuItem1;
    private javax.swing.JMenuItem selectMenuItem;
    private javax.swing.JPopupMenu.Separator separatorMenuItem;
    private javax.swing.JPopupMenu.Separator separatorMenuItem2;
    private javax.swing.JMenuItem signupItem1;
    private javax.swing.JMenuItem signupItem2;
    private javax.swing.JPopupMenu signupMenu;
    private javax.swing.JTable studentTableComp;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JPanel tablePane;
    private javax.swing.JTextField testTxtFld;
    // End of variables declaration//GEN-END:variables
    private final AgentListener<List<Entity>> getStudentsLis = new AgentListener<List<Entity>>() {
        @Override
        public void onStarted(long seq) {
        }

        @Override
        public void onSucceeded(List<Entity> result, long seq) {
            List<CheckableItem<Entity>> itemList
                    = new ArrayList<>();
            for (Entity s : result) {
                CheckableItem<Entity> item
                        = new CheckableItem<Entity>(s);
                itemList.add(item);
            }
            tableModel.setDatas(itemList);
            if (tableCheckBoxHeaderRenderer.isSelected()) {
                tableCheckBoxHeaderRenderer.setSelected(false);
            }
            if (!itemList.isEmpty()) {
                studentTableComp.getSelectionModel().setSelectionInterval(0, 0);
            }
            jScrollPane2.getHorizontalScrollBar().setValue(0);
            jScrollPane2.getVerticalScrollBar().setValue(0);
        }

        @Override
        public void onFailed(String errMsg, int errCode, long seq) {
        }
    };
}
