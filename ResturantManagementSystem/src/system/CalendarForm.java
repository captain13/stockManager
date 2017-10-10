/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import com.toedter.calendar.IDateEvaluator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

/**
 *
 * @author Andrew
 */
public class CalendarForm extends javax.swing.JFrame {

    LineBorder border = new LineBorder(Color.yellow);

    public CalendarForm() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.put("Button.setEnable", true);
            UIManager.put("Button.border", border);
            UIManager.put("Button.background", Color.black);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CalendarForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        HighlightEvaluator evaluator = new HighlightEvaluator();
        evaluator.add(createDate(14));
        evaluator.add(createDate(15));

        jCalendar.getDayChooser().addDateEvaluator(evaluator);
        jCalendar.setCalendar(jCalendar.getCalendar());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class HighlightEvaluator implements IDateEvaluator {

        private final List<Date> list = new ArrayList<>();

        public void add(Date date) {
            list.add(date);
        }

        @Override
        public boolean isSpecial(Date date) {
            return list.contains(date);
        }

        @Override
        public Color getSpecialForegroundColor() {
            return new Color(0,138,241);
        }

        @Override
        public Color getSpecialBackroundColor() {
            return Color.blue;
        }

        @Override
        public String getSpecialTooltip() {
            return "Highlighted event.";
        }

        @Override
        public boolean isInvalid(Date date) {
            return false;
        }

        @Override
        public Color getInvalidForegroundColor() {
            return null;
        }

        @Override
        public Color getInvalidBackroundColor() {
            return null;
        }

        @Override
        public String getInvalidTooltip() {
            return null;
        }
    }

    private Date createDate(int d) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (c.getTime());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        jCalendar = new com.toedter.calendar.JCalendar();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonClose.setBackground(new java.awt.Color(75, 75, 75));
        buttonClose.setForeground(new java.awt.Color(255, 255, 255));
        buttonClose.setText("Close");
        buttonClose.setContentAreaFilled(false);
        buttonClose.setOpaque(true);
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        jCalendar.setDecorationBackgroundColor(new java.awt.Color(0, 138, 231));
        jCalendar.setMinSelectableDate(new java.util.Date(978303708000L));
        jCalendar.setNullDateButtonText("");
        jCalendar.setTodayButtonText("");
        jCalendar.setWeekOfYearVisible(false);
        jCalendar.setWeekdayForeground(new java.awt.Color(0, 0, 0));

        lblTitle.setText("Calendar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblTitle)
                            .addGap(0, 371, Short.MAX_VALUE))
                        .addComponent(jCalendar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTitle)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private com.toedter.calendar.JCalendar jCalendar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
