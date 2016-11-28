/*
 * TableCheckBoxHeaderRenderer.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-09-29 11:22:24
 */
package com.nazca.test.renderer;

import com.nazca.ui.laf.NLAFTheme;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Qiu Dongyue
 */
public class TableCheckBoxHeaderRenderer extends JCheckBox implements TableCellRenderer, MouseListener {
    private static final Image bgImg = null;
    private static final Color RIGHTLINECOLOR = Color.decode("#E9E9E9");
    private static final Color BOTTOMLINECOLOR = Color.decode("#CBCBCB");

    protected int column;
    protected boolean mousePressed = false;

    public TableCheckBoxHeaderRenderer(ItemListener itemListener, JTable table) {
        //TODO 整个类都需要变成既是Editor又是Renderer的形式
        addItemListener(itemListener);
        setOpaque(false);
        if (table != null) {
            JTableHeader header = table.getTableHeader();
            if (header != null) {
                setHorizontalAlignment(CENTER);
                setBackground(table.getTableHeader().getBackground());
                setForeground(table.getTableHeader().getForeground());
                setBounds(table.getTableHeader().getBounds());
                header.addMouseListener(this);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D)g;
//        Graphics2D g2d2 = (Graphics2D)g;
//        RenderingHints rh = GraphicsTool.setQuanlifiedGraphics(g2d); 
//        
//        //画底线
//        g2d.drawImage(bgImg, 0, 0, getWidth(), getHeight(), this);
//        g2d.setColor(BOTTOMLINECOLOR);
//        Stroke sk = new BasicStroke(3f);
//        g2d.setStroke(sk);
//        g2d.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
//        
//        
//        //画侧边线
//        g2d2.setColor(RIGHTLINECOLOR);
//        Stroke rightSk = new BasicStroke(1f);
//        g2d2.setStroke(rightSk);
//        g2d2.drawLine(getWidth() - 1, 13, getWidth() - 1, 26);
//        g2d2.setRenderingHints(rh);
        
        int x = 0;
        int y = 0;
        int w = getWidth();
        int h = getHeight();
        g.setColor(UIManager.getColor("TableHeader.borderColor"));
        g.drawLine(x, y + h - 1, x + w - 1, y + h - 1);
        g.drawLine(x, y + h - 1 - 1, x + w - 1, y + h - 1 - 1);
        g.setColor(NLAFTheme.COMP_COLOR_L1);
        g.drawLine(x + w - 1, y + h / 3, x + w - 1, y + h - h / 3 - 1);
        super.paintComponent(g); 
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setColumn(column);
        return this;
    }

    protected void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    protected void handleClickEvent(MouseEvent e) {
        if (mousePressed) {
            mousePressed = false;
            JTableHeader header = (JTableHeader) (e.getSource());
            JTable tableView = header.getTable();
            TableColumnModel columnModel = tableView.getColumnModel();
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());
            int columns = tableView.convertColumnIndexToModel(viewColumn);

            if (viewColumn == this.column && columns != -1) {
                doClick();
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        handleClickEvent(e);
        ((JTableHeader) e.getSource()).repaint();
    }

    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
