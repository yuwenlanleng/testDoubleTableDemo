package com.nazca.test.common;
import com.nazca.test.enums.ScalePicBtnEnum;
import com.nazca.test.util.ResourceUtil;
import com.nazca.test.util.ScaleCardPicButtonUI;
import com.nazca.ui.GraphicsTool;
import com.nazca.ui.NInternalDiag;
import com.nazca.ui.NInternalDiagListener;
import com.nazca.ui.NToolTip;
import com.nazca.ui.laf.NazcaLAFTool;
import com.nazca.util.StringUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author liuyizhe
 */
public class ScaleImagePane extends javax.swing.JPanel {

    private BufferedImage bg = null;
    private JPanel imgPane = null;
    private double scale = 1f;
    private double MAXSCALE = 3d;
    private double MINSCALE = 0.2d;
    private double scaleStep = 0.3d;
    private double x = Integer.MAX_VALUE;
    private double y = Integer.MAX_VALUE;
    private int paneW = 0;
    private int paneH = 0;
    private double imgW = 0;
    private double imgH = 0;
    private double moveX = 0;
    private double moveY = 0;
    private double angle = 0.0;
    private int baseMX = 0;
    private int baseMY = 0;
    private boolean firstSet = true;

    //如果图片的宽或高大于面板，则以鼠标为中心放大
    /**
     * Creates new form mainPnl
     *
     */
    public ScaleImagePane() {
        initComponents();
        bigBtn.setUI(new ScaleCardPicButtonUI(ScalePicBtnEnum.big));
        resetBtn.setUI(new ScaleCardPicButtonUI(ScalePicBtnEnum.reset));
        smallBtn.setUI(new ScaleCardPicButtonUI(ScalePicBtnEnum.small));
        leftBtn.setUI(new ScaleCardPicButtonUI(ScalePicBtnEnum.left));
        righBtn.setUI(new ScaleCardPicButtonUI(ScalePicBtnEnum.right));
        NToolTip.attach(bigBtn, "放大");
        NToolTip.attach(resetBtn, "重置");
        NToolTip.attach(smallBtn, "缩小");
        NToolTip.attach(leftBtn, "逆时针旋转");
        NToolTip.attach(righBtn, "顺时针旋转");
        jLayeredPane1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jPanel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imgPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg != null) {
                    //管理按钮
                    if (scale < MAXSCALE) {
                        bigBtn.setEnabled(true);
                    } else {
                        bigBtn.setEnabled(false);
                    }
                    if (scale > MINSCALE) {
                        smallBtn.setEnabled(true);
                    } else {
                        smallBtn.setEnabled(false);
                    }
                    //计算坐标
                    paneW = jLayeredPane1.getWidth();
                    paneH = jLayeredPane1.getHeight();
                    imgW = bg.getWidth() * scale;
                    imgH = bg.getHeight() * scale;

                    x = (int) (paneW - imgW) / 2;
                    y = (int) (paneH - imgH) / 2;
                    //绘制
                    Graphics2D g2 = (Graphics2D) g.create();
                    GraphicsTool.setQuanlifiedGraphics(g2);
                    AffineTransform at = new AffineTransform();
                    if (angle % 360 == 0) {
                        angle = 0;
                    }
                    at.translate((getWidth() - imgW) / 2, (getHeight() - imgH) / 2);
                    at.rotate(Math.toRadians(angle), (imgW) / 2, (imgH) / 2);
                    at.scale(scale, scale);
                    AffineTransform atTR = new AffineTransform();
                    atTR.translate(baseMX, baseMY);
                    atTR.concatenate(at);

                    g2.drawImage(bg, atTR, this);
                }
            }
        };
        imgPane.setBackground(Color.WHITE);
        jPanel2.add(imgPane, BorderLayout.CENTER);
        jLayeredPane1.add(jPanel2);
        jLayeredPane1.add(jPanel3);
        jLayeredPane1.setLayer(jPanel2, 0, 0);
        jLayeredPane1.setLayer(jPanel3, 10, 0);
        jLayeredPane1.setLayout(new TarvalMapPaneLayout());
        DragPicListener lis = new DragPicListener();
        imgPane.addMouseListener(lis);
        imgPane.addMouseMotionListener(lis);
        imgPane.addMouseWheelListener(lis);
    }

    /**
     * 设置图片
     *
     * @param img
     */
    public void setViewImg(BufferedImage img) {
        if (img != null) {
            firstSet = true;
            bg = img;
            angle = 0;
            resetBtn.doClick();
            jLayeredPane1.invalidate();
            jLayeredPane1.validate();
            jLayeredPane1.repaint();
            resetBtn.doClick();
        } else {
            throw new IllegalArgumentException("img can't be null");
        }
    }

    /**
     * 设置图片
     *
     * @param path
     */
    public void setViewImgPath(String path) {
        if (!StringUtil.isEmpty(path)) {
            try {
                firstSet = true;
                BufferedImage img = ImageIO.read(new File(path));
                bg = img;
                angle = 0;
                jLayeredPane1.invalidate();
                jLayeredPane1.validate();
                jLayeredPane1.repaint();
            } catch (IOException ex) {
            }
        } else {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        bigBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        resetBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        smallBtn = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        leftBtn = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        righBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setPreferredSize(new java.awt.Dimension(30, 159));

        bigBtn.setBackground(new java.awt.Color(255, 255, 255));
        bigBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nazca/test/res/big-normal.png"))); // NOI18N
        bigBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bigBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        bigBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        bigBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        bigBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        bigBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bigBtnActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(172, 172, 172));
        jSeparator2.setMaximumSize(new java.awt.Dimension(30, 1));
        jSeparator2.setMinimumSize(new java.awt.Dimension(30, 1));
        jSeparator2.setPreferredSize(new java.awt.Dimension(30, 1));

        resetBtn.setBackground(new java.awt.Color(255, 255, 255));
        resetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nazca/test/res/reset-normal.png"))); // NOI18N
        resetBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        resetBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        resetBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        resetBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        resetBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(172, 172, 172));
        jSeparator1.setMaximumSize(new java.awt.Dimension(30, 1));
        jSeparator1.setMinimumSize(new java.awt.Dimension(30, 1));
        jSeparator1.setPreferredSize(new java.awt.Dimension(30, 1));

        smallBtn.setBackground(new java.awt.Color(255, 255, 255));
        smallBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nazca/test/res/small-normal.png"))); // NOI18N
        smallBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        smallBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        smallBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        smallBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        smallBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        smallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smallBtnActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(172, 172, 172));
        jSeparator3.setMaximumSize(new java.awt.Dimension(30, 1));
        jSeparator3.setMinimumSize(new java.awt.Dimension(30, 1));
        jSeparator3.setPreferredSize(new java.awt.Dimension(30, 1));

        leftBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nazca/test/res/left-normal.png"))); // NOI18N
        leftBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        leftBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        leftBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        leftBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        leftBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftBtnActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(172, 172, 172));
        jSeparator4.setMaximumSize(new java.awt.Dimension(30, 1));
        jSeparator4.setMinimumSize(new java.awt.Dimension(30, 1));
        jSeparator4.setPreferredSize(new java.awt.Dimension(30, 1));

        righBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nazca/test/res/right-normal.png"))); // NOI18N
        righBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        righBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        righBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        righBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        righBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        righBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                righBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bigBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(smallBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leftBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(righBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(bigBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(resetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(smallBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(leftBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(righBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(300, 20));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("  注:按住鼠标左键, 可上下左右移动照片, 点击 \"放大\" \"缩小\"可以调整照片大小。");
        jLabel3.setPreferredSize(new java.awt.Dimension(300, 20));
        jPanel1.add(jLabel3, java.awt.BorderLayout.CENTER);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setLayout(new java.awt.BorderLayout());

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
        );

        add(jLayeredPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void smallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smallBtnActionPerformed
        if (scale > MINSCALE) {
            scale -= scaleStep;
        }
        repaint();
    }//GEN-LAST:event_smallBtnActionPerformed

    private void bigBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bigBtnActionPerformed
        if (scale < MAXSCALE) {
            scale += scaleStep;
        }
        repaint();
    }//GEN-LAST:event_bigBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        resetImg();
    }//GEN-LAST:event_resetBtnActionPerformed

    public void resetImg() {
        int imgW = bg.getWidth();
        int imgH = bg.getHeight();
        int width = getWidth();
        int height = getHeight();
        if (width > height) {
            if (imgW > imgH) {
                scale = (double) width / imgW;
            } else {
                scale = (double) height / imgH;
            }
        } else if (imgW <= imgH) {
            scale = (double) height / imgH;
        } else {
            scale = (double) width / imgW;
        }
        angle = 0;
        baseMX = 0;
        baseMY = 0;
        smallBtn.setEnabled(true);
        bigBtn.setEnabled(true);
        validate();
        repaint();
    }

    private void leftBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftBtnActionPerformed
        angle -= 90;
        repaint();
    }//GEN-LAST:event_leftBtnActionPerformed

    private void righBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_righBtnActionPerformed
        angle += 90;
        repaint();
    }//GEN-LAST:event_righBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bigBtn;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton leftBtn;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton righBtn;
    private javax.swing.JButton smallBtn;
    // End of variables declaration//GEN-END:variables

    class TarvalMapPaneLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
//            return new Dimension(100, 100);
              return new Dimension(60, 60);
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(10, 10);
        }

        @Override
        public void layoutContainer(Container parent) {
            jPanel2.setBounds(0, 0, jLayeredPane1.getWidth(), jLayeredPane1.getHeight());
            int width = jPanel3.getPreferredSize().width + 4;
            int height = jPanel3.getPreferredSize().height;
            int x = jLayeredPane1.getWidth() - jPanel3.getPreferredSize().width - 10;
            int y = jLayeredPane1.getHeight() - jPanel3.getPreferredSize().height - 10;
            jPanel3.setBounds(x, y, width, height);
        }
    }

    class DragPicListener extends MouseAdapter {

        Point point = null; //坐标点
        private int orgX = 0;
        private int orgY = 0;

        @Override
        public void mousePressed(MouseEvent e) {
            point = e.getPoint(); //得到当前坐标点
            orgX = baseMX;
            orgY = baseMY;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            point = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point newPoint = e.getPoint(); //转换坐标系统
            baseMX = orgX + (newPoint.x - point.x);
            baseMY = orgY + (newPoint.y - point.y);
            repaint();
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int rotation = e.getWheelRotation();
            if (rotation > 0) {
                if (scale > MINSCALE) {
                    scale -= scaleStep;
                }
            } else if (scale < MAXSCALE) {
                scale += scaleStep;
            }
            if (imgH > paneH || imgW > paneW) {
                point = e.getPoint();
            } else {
                point = null;
            }
            repaint();
        }
    }

    public void showMe(JComponent parent, BufferedImage image) {
        setViewImg(image);
        NInternalDiag d = new NInternalDiag("查看", ResourceUtil.readIcon("manage-pic.png"), this);
        d.addNInternalDiagListener(new NInternalDiagListener() {
            public void onClosing(NInternalDiag nid) {
                nid.hideDiag();
            }

            public void onClosed(NInternalDiag nid) {
            }

            public void onShowingDone(NInternalDiag nid) {
                resetImg();
            }
        });
        d.setCloseButtonVisible(true);
        d.showInternalDiag(parent, NInternalDiag.INIT_SIZE_MODE_MAX);
    }

    public void showMeOFCustomSize(JComponent parent, String title, BufferedImage image, int width, int height) {
        setViewImg(image);
        NInternalDiag d = new NInternalDiag(title, null, this, width, height);
        d.addNInternalDiagListener(new NInternalDiagListener() {
            public void onClosing(NInternalDiag nid) {
                nid.hideDiag();
            }

            public void onClosed(NInternalDiag nid) {
            }

            public void onShowingDone(NInternalDiag nid) {
                resetImg();
            }
        });
        d.setCloseButtonVisible(true);
        d.showInternalDiag(parent);
        
    }

    public static void main(String[] args) {
        NazcaLAFTool.applyNazcaLAF();
        JFrame frame = new JFrame();
        ScaleImagePane pane = new ScaleImagePane();
        pane.setViewImg(ResourceUtil.readImage("default-32.png"));
        frame.add(pane);
        frame.setTitle("测试");
        frame.pack();
        frame.setVisible(true);
        frame.setSize(416, 438);
        int windowWidth = frame.getWidth();                     //获得窗口宽
        int windowHeight = frame.getHeight();                   //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
        Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
        int screenWidth = screenSize.width;                     //获取屏幕的宽
        int screenHeight = screenSize.height;                   //获取屏幕的高
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
