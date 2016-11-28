/*
 * StudentTableImmutableModel.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-09-28 14:45:00
 */
package com.nazca.test.model;

import static com.nazca.test.model.AbstractCheckableListTableModel.CHECK_BOX;
import com.nazca.test.pojo.Entity;
import javax.swing.JTable;

/**
 * 考生表格Model
 *
 * @author Qiu Dongyue
 */
public class StudentTableImmutableModel extends AbstractCheckableListTableModel<Entity> {

    public static final int FIELD1 = 1;

    public static final int FIELD2 = 2;

    public static final int FIELD3 = 3;

    public static final int FIELD4 = 4;

    public static final int FIELD5 = 5;

    public static final int FIELD6 = 6;

    public static final int FIELD7 = 7;

    public static final int FIELD8 = 8;

    public static final int FIELD9 = 9;

    public static final int FIELD10 = 10;

    public static final int FIELD11 = 11;

    public static final int FIELD12 = 12;

    public static final int FIELD13 = 13;

    public static final int FIELD14 = 14;

    public static final int FIELD15 = 15;

    public static final int FIELD16 = 16;

    public static final int FIELD17 = 17;

    public static final int FIELD18 = 18;

    public static final int FIELD19 = 19;

    public static final int FIELD20 = 20;

    public static final int FIELD21 = 21;

    public static final int FIELD22 = 22;

    public static final int FIELD23 = 23;

    public static final int FIELD24 = 24;

    public static final int FIELD25 = 25;
    public static final int FIELD26 = 26;
    public static final int FIELD27 = 27;

    private static String[] columns = new String[]{"", "测试", "字段1", "字段2", "字段3", "字段4",
        "字段5", "字段6", "字段7", "字段8",
        "字段9", "字段10", "字段11", "字段12", "字段13", "字段14", "字段15", "字段16", "字段17",
        "字段18", "字段19", "字段20", "字段21", "字段22", "字段23", "字段24", "字段25"};
//    private List<SortableItem> sortOrderList = null;
//
//    public List<SortableItem> getSortOrderList() {
//        if (sortOrderList == null || sortOrderList.isEmpty()) {
//            return null;
//        }
//        return sortOrderList;
//    }

//    public void setSortOrder(final List<SortableItem> list) {
//        sortOrderList = list;
//        if (sortOrderList.isEmpty()) {
//            customComparator = null;
//        } else {
//            customComparator = new Comparator<CheckableItem<Student>>() {
//                public int compare(CheckableItem<Student> o1, CheckableItem<Student> o2) {
//                    try {
//                        Class c = Class.forName("com.nazca.themis.common.model.signup.jpa.Entity");
//                        Entity s1 = o1.getItem();
//                        Entity s2 = o2.getItem();
//                        for (SortableItem item : sortOrderList) {
//                            StudentPropertyEnum property = item.getProperty();
//                            boolean asc = item.isAsc();
//                            String getName = "get"
//                                    + property.name().substring(0, 1).toUpperCase()
//                                    + property.name().substring(1);
//                            Method m = c.getMethod(getName);
//                            Object obj1 = m.invoke(s1);
//                            Object obj2 = m.invoke(s2);
//                            if (obj1 instanceof String) {
//                                if (obj1 == null && obj2 == null) {
//                                    continue;
//                                } else if (obj1 == null) {
//                                    return asc ? -1 : 1;
//                                } else if (obj2 == null) {
//                                    return asc ? 1 : -1;
//                                } else {
//                                    if (0 == PinYinTool.compare((String) obj1, (String) obj2)) {
//                                        continue;
//                                    }
//                                    return asc ? PinYinTool.compare((String) obj1, (String) obj2)
//                                            : PinYinTool.compare((String) obj2, (String) obj1);
//                                }
//                            } else if (obj1 instanceof Integer) {
//                                if (obj1 == null && obj2 == null) {
//                                    continue;
//                                } else if (obj1 == null) {
//                                    return asc ? -1 : 1;
//                                } else if (obj2 == null) {
//                                    return asc ? 1 : -1;
//                                } else {
//                                    if (0 == (Integer) obj1 - (Integer) obj2) {
//                                        continue;
//                                    }
//                                    return asc ? (Integer) obj1 - (Integer) obj2 : (Integer) obj2 - (Integer) obj1;
//                                }
//                            } else if (obj1 instanceof Date) {
//                                if (obj1 == null && obj2 == null) {
//                                    continue;
//                                } else if (obj1 == null) {
//                                    return asc ? -1 : 1;
//                                } else if (obj2 == null) {
//                                    return asc ? 1 : -1;
//                                } else {
//                                    if (0 == ((Date) obj1).compareTo((Date) obj2)) {
//                                        continue;
//                                    }
//                                    return asc ? ((Date) obj1).compareTo((Date) obj2) : ((Date) obj2).compareTo(
//                                            (Date) obj1);
//                                }
//                            } else if (obj1 instanceof Double) {
//                                if (obj1 == null && obj2 == null) {
//                                    continue;
//                                } else if (obj1 == null) {
//                                    return asc ? -1 : 1;
//                                } else if (obj2 == null) {
//                                    return asc ? 1 : -1;
//                                } else {
//                                    if (0 == (int) ((Double) obj1 - (Double) obj2)) {
//                                        continue;
//                                    }
//                                    return asc ? (int) ((Double) obj1 - (Double) obj2) : (int) ((Double) obj2
//                                            - (Double) obj1);
//                                }
//                            } else {
//                                if (obj1 == null && obj2 == null) {
//                                    continue;
//                                } else if (obj1 == null) {
//                                    return asc ? -1 : 1;
//                                } else if (obj2 == null) {
//                                    return asc ? 1 : -1;
//                                } else {
//                                    if (0 == PinYinTool.compare(obj1.toString(), obj2.toString())) {
//                                        continue;
//                                    }
//                                    return asc ? PinYinTool.compare(obj1.toString(), obj2.toString()) : PinYinTool.
//                                            compare(obj2.toString(), obj1.toString());
//                                }
//                            }
//                        }
//                    } catch (Throwable th) {
//                        th.printStackTrace();
//                    }
//                    return -1;
//                }
//            };
//            Collections.sort(dataList, customComparator);
//            fireTableDataChanged();
//        }
//    }
    public StudentTableImmutableModel() {
        super(columns);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        CheckableItem<Entity> item = dataList.get(rowIndex);
        Entity student = item.getItem();
        switch (columnIndex) {
            case CHECK_BOX:
                return item.isChecked();
            case FIELD1:
                return student.getField1();
            case FIELD2:
                return student.getField2();
            case FIELD3:
                return student.getField3();
            case FIELD4:
                return student.getField4();
            case FIELD5:
                return student.getField5();
            case FIELD6:
                return student.getField6();
            case FIELD7:
                return student.getField7();
            case FIELD8:
                return student.getField8();
            case FIELD9:
                return student.getField9();
            case FIELD10:
                return student.getField10();
            case FIELD11:
                return student.getField11();
            case FIELD12:
                return student.getField12();
            case FIELD13:
                return student.getField13();
            case FIELD14:
                return student.getField14();
            case FIELD15:
                return student.getField15();
            case FIELD16:
                return student.getField16();
            case FIELD17:
                return student.getField17();
            case FIELD18:
                return student.getField18();
            case FIELD19:
                return student.getField19();
            case FIELD20:
                return student.getField20();
            case FIELD21:
                return student.getField21();
            case FIELD22:
                return student.getField22();
            case FIELD23:
                return student.getField23();
            case FIELD24:
                return student.getField24();
            case FIELD25:
                return student.getField25();
            case FIELD26:
                return "QQQQ";
            default:
                return null;
        }
    }

//    public List<CheckableItem<Entity>> getDatasBySignupNums(List<String> signupNumList) {
//        List<CheckableItem<Entity>> list = new ArrayList<CheckableItem<Entity>>();
//        for (CheckableItem<Entity> item : dataList) {
//            if (item.getItem() != null) {
//                if (signupNumList.contains(item.getItem().getSignupNum())) {
//                    list.add(item);
//                }
//            }
//        }
//        return list;
//    }
    public int indexOf(CheckableItem<Entity> item) {
        return dataList.indexOf(item);
    }

    public void initTblHeaderWidth(JTable checkBoxTableComp) {
        checkBoxTableComp.getTableHeader().getColumnModel().getColumn(CHECK_BOX).setPreferredWidth(50);
    }
}
