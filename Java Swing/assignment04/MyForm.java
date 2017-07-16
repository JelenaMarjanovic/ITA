package assignment04;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

public class MyForm extends javax.swing.JFrame {

    boolean blink = false;
    Color color;
    int speed;
    JFrame colorFrame;
    Timer t;

    public MyForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroupChoice = new javax.swing.ButtonGroup();
        jChosenColor = new javax.swing.JLabel();
        jChooseColor = new javax.swing.JButton();
        jLabel = new javax.swing.JLabel();
        jSpeed = new javax.swing.JSlider();
        jExactTime = new javax.swing.JSpinner();
        jStartTime = new javax.swing.JSpinner();
        jStart = new javax.swing.JButton();
        jStop = new javax.swing.JButton();
        jOnTime = new javax.swing.JRadioButton();
        jCountdown = new javax.swing.JRadioButton();
        jSelectedSpeed = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jChosenColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jChosenColor.setText("No color selected");

        jChooseColor.setText("Choose color");
        jChooseColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChooseColorActionPerformed(evt);
            }
        });

        jLabel.setText("Speed (100ms - 3s):");

        jSpeed.setMajorTickSpacing(100);
        jSpeed.setMaximum(3000);
        jSpeed.setMinimum(100);
        jSpeed.setMinorTickSpacing(50);
        jSpeed.setPaintTicks(true);
        jSpeed.setSnapToTicks(true);
        jSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpeedStateChanged(evt);
            }
        });

        jExactTime.setModel(new javax.swing.SpinnerDateModel());
        jExactTime.setEditor(new JSpinner.DateEditor(jExactTime, "dd.MM.yyyy. HH:mm:ss"));

        jStartTime.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jStart.setText("Start");
        jStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStartActionPerformed(evt);
            }
        });

        jStop.setText("Stop");
        jStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStopActionPerformed(evt);
            }
        });

        rbGroupChoice.add(jOnTime);
        jOnTime.setSelected(true);
        jOnTime.setText("on time:");

        rbGroupChoice.add(jCountdown);
        jCountdown.setText("countdown (mins)");

        jSelectedSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jSelectedSpeed.setMinimumSize(new java.awt.Dimension(97, 14));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jStart, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jStop, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCountdown, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(jChooseColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jOnTime, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jExactTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jChosenColor, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jSelectedSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jExactTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jOnTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCountdown))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jChooseColor)
                    .addComponent(jChosenColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSelectedSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jStart)
                    .addComponent(jStop))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // odabir boje iz palete i prikaz u labeli
    private void jChooseColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChooseColorActionPerformed
        JColorChooser jColorChooser = new JColorChooser();
        JDialog colorDialog = JColorChooser.createDialog(null, "Choose color", true, jColorChooser, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = jColorChooser.getColor();
                jChosenColor.setText("");
                jChosenColor.setBackground(color);
                jChosenColor.setOpaque(true);
            }
        }, null);
        colorDialog.setVisible(true);
    }//GEN-LAST:event_jChooseColorActionPerformed

    // prikaz odabrane brzine blinkanja boje
    private void jSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpeedStateChanged
        jSelectedSpeed.setText(String.valueOf(jSpeed.getValue()) + " ms");
    }//GEN-LAST:event_jSpeedStateChanged

    // pokretanje timer aplikacije
    private void jStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStartActionPerformed
        speed = jSpeed.getValue();
        int startTime = ((int) jStartTime.getValue()) * 60 * 1000;

        jOnTime.setEnabled(false);
        jExactTime.setEnabled(false);
        jCountdown.setEnabled(false);
        jStartTime.setEnabled(false);
        jSpeed.setEnabled(false);
        jChooseColor.setEnabled(false);
        jStart.setEnabled(false);

        if (jOnTime.isSelected()) {

            Date exactTime = (Date) jExactTime.getValue();
            Date currentTime = new Date();

            int timeToElapse = (int) (exactTime.getTime() - currentTime.getTime());

            t = createTimer(timeToElapse);
            t.start();

        } else if (jCountdown.isSelected()) {

            t = createTimer(startTime);
            t.start();

        }

    }//GEN-LAST:event_jStartActionPerformed

    // zaustavljanje timer aplikacije
    private void jStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStopActionPerformed
        jOnTime.setEnabled(true);
        jExactTime.setValue(new Date());
        jExactTime.setEnabled(true);
        jCountdown.setEnabled(true);
        jStartTime.setValue(0);
        jStartTime.setEnabled(true);
        jSpeed.setValue(100);
        jSpeed.setEnabled(true);
        jChosenColor.setOpaque(false);
        jChosenColor.setText("No color selected");
        jChooseColor.setEnabled(true);
        jStart.setEnabled(true);

        blink = false;
        t.stop();

        colorFrame.dispose();
    }//GEN-LAST:event_jStopActionPerformed

    // promena boje u novom prozoru
    private void blinking() {

        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                colorFrame = new JFrame();
                colorFrame.setSize(300, 300);
                colorFrame.setTitle("Color blinking frame");
                colorFrame.setBackground(Color.WHITE);
                repaint();
                colorFrame.setLocationRelativeTo(null);
                colorFrame.setVisible(true);

                Thread.sleep(speed);

                while (blink) {
                    colorFrame.getContentPane().setBackground(color);
                    Thread.sleep(speed);
                    repaint();
                    colorFrame.getContentPane().setBackground(Color.WHITE);
                    repaint();
                    Thread.sleep(speed);
                }

                colorFrame.dispose();

                blink = false;

                return null;
            }
        };

        sw.execute();

    }

    // kreiranje i pokretanje Timer-a
    private Timer createTimer(int delay) {

        Timer t = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blink = true;
                blinking();
            }
        });

        t.setRepeats(false);

        return t;

    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jChooseColor;
    private javax.swing.JLabel jChosenColor;
    private javax.swing.JRadioButton jCountdown;
    private javax.swing.JSpinner jExactTime;
    private javax.swing.JLabel jLabel;
    private javax.swing.JRadioButton jOnTime;
    private javax.swing.JLabel jSelectedSpeed;
    private javax.swing.JSlider jSpeed;
    private javax.swing.JButton jStart;
    private javax.swing.JSpinner jStartTime;
    private javax.swing.JButton jStop;
    private javax.swing.ButtonGroup rbGroupChoice;
    // End of variables declaration//GEN-END:variables
}
