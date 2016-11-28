/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazca.test.common;

import com.nazca.ui.GraphicsTool;
import com.nazca.ui.laf.NazcaLAFTool;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.core.animation.timing.interpolators.SplineInterpolator;

/**
 *
 * @author kain15
 */
public class GeneralMessagePanel extends JPanel {

    public GeneralMessagePanel() {
        setOpaque(false);
        anim = new Animator.Builder(GraphicsTool.defaultAnimatorTimingSource())
                .setDuration(1000, TimeUnit.MILLISECONDS)
                .setInterpolator(new SplineInterpolator(0, 1, 0, 1))
                .build();
        anim.addTarget(new TimingTargetAdapter() {
            @Override
            public void timingEvent(Animator source, double fraction) {
                frac = fraction;
                repaint();
            }

        });
    }

    public void showMessage() {
        anim.restart();
    }

    private String text = "暂时没有检索的数据";

    private Font font = UIManager.getFont("Label.font").deriveFont(20f);
    private Animator anim = null;
    private double frac = 0d;
    private Insets is = new Insets(15, 20, 15, 20);

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        GraphicsTool.setQuanlifiedGraphics(g2d);
        TextLayout tly = new TextLayout(getText(), font, g2d.getFontRenderContext());
        Rectangle2D r2 = tly.getBounds();
        float x = (float) ((getWidth() - r2.getWidth()) / 2);
//        float y = (float) ((getHeight() + r2.getHeight()) / 2 * 0.3f);
        float y = (float) ((getHeight() - r2.getHeight()) / 2);
//        g2d.drawRoundRect(WIDTH, WIDTH, WIDTH, HEIGHT, WIDTH, HEIGHT);
        Composite orgc = g2d.getComposite();
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (frac * 0.23));
        g2d.setComposite(ac);
        g2d.setColor(Color.black);
        g2d.fillRoundRect((int) (x - is.left), (int) (y - is.bottom - is.top), (int) (r2.getWidth() + is.left + is.right), (int) (r2.getHeight() + is.bottom + is.top), 10, 10);
//        g2d.setComposite(orgc);
        AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (frac * 1f));
        g2d.setComposite(ac2);
        g2d.setColor(Color.white);
        tly.draw(g2d, (float) x, (float) y);
    }

    public static void main(String[] args) {
        NazcaLAFTool.applyNazcaLAF();
        JFrame jf = new JFrame();
        final GeneralMessagePanel generalMessagePanel = new GeneralMessagePanel();
        jf.add(generalMessagePanel);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(500, 500);
        JButton jb = new JButton("sdsd");
        jf.add(jb, BorderLayout.NORTH);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generalMessagePanel.showMessage();
            }
        });
        jf.setVisible(true);

    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
