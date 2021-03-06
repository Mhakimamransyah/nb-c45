/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Main;
import java.util.HashMap;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Home extends javax.swing.JFrame {

    private Main main;
    
    public Home(Main main) {
        initComponents();
        this.matrix_conf_NB.getTableHeader().setUI(null);
        this.matrix_conf_C45.getTableHeader().setUI(null);
        this.main = main;
    }

    private Home() {
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        validation_type = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Home = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Dashboard = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pengaturan = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        data = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        atur_Data = new javax.swing.JButton();
        split = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        split_validation = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        split_ratio = new javax.swing.JSpinner();
        cross = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        cross_validation = new javax.swing.JRadioButton();
        jPanel23 = new javax.swing.JPanel();
        kfold = new javax.swing.JSpinner();
        data1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        NB = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        MC = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        matrix_conf_NB = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        scroll_nb = new javax.swing.JScrollPane();
        panel_nb = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        do_naive_bayes = new javax.swing.JButton();
        C45 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        MC1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        matrix_conf_C45 = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        panel_c45 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        doC45 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tugas Akhir Data Mining");

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 50)); // NOI18N
        jLabel3.setText("Data Mining");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setText("Perbandingan metode klasifikasi Naive Bayes dan C4.5");

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(183, 183, 183)))
                .addContainerGap(456, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Home", Home);

        Dashboard.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        pengaturan.setPreferredSize(new java.awt.Dimension(2, 561));
        pengaturan.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 327));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/setting.png"))); // NOI18N
        jLabel1.setText("Pengaturan ");
        jPanel6.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel6);

        jPanel5.setPreferredSize(new java.awt.Dimension(266, 300));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.PAGE_AXIS));

        data.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        data.setLayout(new javax.swing.BoxLayout(data, javax.swing.BoxLayout.LINE_AXIS));

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setPreferredSize(new java.awt.Dimension(50, 468));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Data");
        jPanel9.add(jLabel2, java.awt.BorderLayout.CENTER);

        data.add(jPanel9);

        jPanel8.setPreferredSize(new java.awt.Dimension(50, 474));
        jPanel8.setLayout(new java.awt.BorderLayout());

        atur_Data.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        atur_Data.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/data_icon.png"))); // NOI18N
        atur_Data.setText("Atur Data");
        atur_Data.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atur_Data.setFocusPainted(false);
        atur_Data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atur_DataActionPerformed(evt);
            }
        });
        jPanel8.add(atur_Data, java.awt.BorderLayout.CENTER);

        data.add(jPanel8);

        jPanel7.add(data);

        split.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        split.setLayout(new javax.swing.BoxLayout(split, javax.swing.BoxLayout.LINE_AXIS));

        jPanel10.setPreferredSize(new java.awt.Dimension(50, 468));
        jPanel10.setLayout(new java.awt.BorderLayout());

        validation_type.add(split_validation);
        split_validation.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        split_validation.setSelected(true);
        split_validation.setText("Split Validation");
        split_validation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        split_validation.setFocusPainted(false);
        split_validation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel10.add(split_validation, java.awt.BorderLayout.CENTER);

        split.add(jPanel10);

        jPanel11.setPreferredSize(new java.awt.Dimension(50, 474));
        jPanel11.setLayout(new java.awt.BorderLayout());

        split_ratio.setToolTipText("Split Ratio (%)");
        split_ratio.setValue(70);
        jPanel11.add(split_ratio, java.awt.BorderLayout.CENTER);

        split.add(jPanel11);

        jPanel7.add(split);

        cross.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cross.setLayout(new javax.swing.BoxLayout(cross, javax.swing.BoxLayout.LINE_AXIS));

        jPanel22.setPreferredSize(new java.awt.Dimension(50, 468));
        jPanel22.setLayout(new java.awt.BorderLayout());

        validation_type.add(cross_validation);
        cross_validation.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cross_validation.setText("Cross Validation");
        cross_validation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cross_validation.setFocusPainted(false);
        cross_validation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel22.add(cross_validation, java.awt.BorderLayout.CENTER);

        cross.add(jPanel22);

        jPanel23.setPreferredSize(new java.awt.Dimension(50, 474));
        jPanel23.setLayout(new java.awt.BorderLayout());

        kfold.setToolTipText("K-fold");
        kfold.setValue(10);
        jPanel23.add(kfold, java.awt.BorderLayout.CENTER);

        cross.add(jPanel23);

        jPanel7.add(cross);

        data1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        data1.setLayout(new javax.swing.BoxLayout(data1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel13.setPreferredSize(new java.awt.Dimension(50, 474));
        jPanel13.setLayout(new java.awt.BorderLayout());
        data1.add(jPanel13);

        jPanel7.add(data1);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5);

        pengaturan.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(pengaturan);

        NB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        NB.setPreferredSize(new java.awt.Dimension(70, 561));
        NB.setLayout(new java.awt.BorderLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel17.setPreferredSize(new java.awt.Dimension(282, 5));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Naive Bayes");
        jPanel17.add(jLabel4, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel17);

        MC.setBackground(new java.awt.Color(255, 255, 255));
        MC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Matriks Confussion Naive Bayes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        MC.setPreferredSize(new java.awt.Dimension(282, 150));
        MC.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setEnabled(false);

        matrix_conf_NB.setBackground(new java.awt.Color(0, 0, 0));
        matrix_conf_NB.setForeground(new java.awt.Color(255, 255, 255));
        matrix_conf_NB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        matrix_conf_NB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        matrix_conf_NB.setAutoscrolls(false);
        matrix_conf_NB.setMinimumSize(new java.awt.Dimension(60, 20));
        matrix_conf_NB.setRowHeight(20);
        jScrollPane2.setViewportView(matrix_conf_NB);
        if (matrix_conf_NB.getColumnModel().getColumnCount() > 0) {
            matrix_conf_NB.getColumnModel().getColumn(0).setResizable(false);
            matrix_conf_NB.getColumnModel().getColumn(1).setResizable(false);
            matrix_conf_NB.getColumnModel().getColumn(2).setResizable(false);
            matrix_conf_NB.getColumnModel().getColumn(3).setResizable(false);
        }

        MC.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel14.add(MC);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Log Proses Naive Bayes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel15.setPreferredSize(new java.awt.Dimension(282, 130));
        jPanel15.setLayout(new java.awt.BorderLayout());

        scroll_nb.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_nb.setFocusCycleRoot(true);

        panel_nb.setBackground(new java.awt.Color(255, 255, 255));
        panel_nb.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        panel_nb.setLayout(new javax.swing.BoxLayout(panel_nb, javax.swing.BoxLayout.PAGE_AXIS));
        scroll_nb.setViewportView(panel_nb);

        jPanel15.add(scroll_nb, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel15);

        jPanel16.setPreferredSize(new java.awt.Dimension(282, 5));
        jPanel16.setLayout(new java.awt.BorderLayout());

        do_naive_bayes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/start.png"))); // NOI18N
        do_naive_bayes.setText("Naive Bayes");
        do_naive_bayes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        do_naive_bayes.setFocusPainted(false);
        do_naive_bayes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                do_naive_bayesActionPerformed(evt);
            }
        });
        jPanel16.add(do_naive_bayes, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel16);

        NB.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel1.add(NB);

        C45.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        C45.setPreferredSize(new java.awt.Dimension(70, 561));
        C45.setLayout(new java.awt.BorderLayout());

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel18.setLayout(new javax.swing.BoxLayout(jPanel18, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel20.setPreferredSize(new java.awt.Dimension(282, 5));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("C4.5");
        jPanel20.add(jLabel5, java.awt.BorderLayout.CENTER);

        jPanel18.add(jPanel20);

        MC1.setBackground(new java.awt.Color(255, 255, 255));
        MC1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Matriks Confussion C4.5", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        MC1.setPreferredSize(new java.awt.Dimension(282, 150));
        MC1.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setEnabled(false);

        matrix_conf_C45.setBackground(new java.awt.Color(0, 0, 0));
        matrix_conf_C45.setForeground(new java.awt.Color(255, 255, 255));
        matrix_conf_C45.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        matrix_conf_C45.setAlignmentX(1.0F);
        matrix_conf_C45.setAlignmentY(1.0F);
        matrix_conf_C45.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        matrix_conf_C45.setRowHeight(20);
        jScrollPane3.setViewportView(matrix_conf_C45);

        MC1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel18.add(MC1);

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Log Proses C4.5", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel21.setPreferredSize(new java.awt.Dimension(282, 130));
        jPanel21.setLayout(new java.awt.BorderLayout());

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel_c45.setBackground(new java.awt.Color(255, 255, 255));
        panel_c45.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        panel_c45.setFocusable(false);
        panel_c45.setLayout(new javax.swing.BoxLayout(panel_c45, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane4.setViewportView(panel_c45);

        jPanel21.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel18.add(jPanel21);

        jPanel25.setPreferredSize(new java.awt.Dimension(282, 5));
        jPanel25.setLayout(new java.awt.BorderLayout());

        doC45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/start.png"))); // NOI18N
        doC45.setText("C4.5");
        doC45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        doC45.setFocusPainted(false);
        doC45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doC45ActionPerformed(evt);
            }
        });
        jPanel25.add(doC45, java.awt.BorderLayout.CENTER);

        jPanel18.add(jPanel25);

        C45.add(jPanel18, java.awt.BorderLayout.CENTER);

        jPanel1.add(C45);

        Dashboard.add(jPanel1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Dashboard", Dashboard);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void atur_DataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atur_DataActionPerformed
       this.main.frameData("Data");
    }//GEN-LAST:event_atur_DataActionPerformed

    private void do_naive_bayesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_do_naive_bayesActionPerformed
        HashMap<String, String> konfig = this.getValidationType();
        konfig.put("Algoritma", "Naive Bayes");
        this.main.do_validation(konfig, this.do_naive_bayes, this.panel_nb, this.matrix_conf_NB);
    }//GEN-LAST:event_do_naive_bayesActionPerformed

    private void doC45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doC45ActionPerformed
        HashMap<String, String> konfig = this.getValidationType();
        konfig.put("Algoritma", "C4.5");
        this.main.do_validation(konfig, this.doC45, this.panel_c45, this.matrix_conf_C45);
    }//GEN-LAST:event_doC45ActionPerformed
    
    private HashMap<String, String> getValidationType(){
        HashMap<String, String> konfig = new HashMap<String, String>();
        if(this.split_validation.isSelected()){
            konfig.put("Tipe", "Split");
            konfig.put("Ratio", this.split_ratio.getValue().toString());
        }else if(this.cross_validation.isSelected()){
            konfig.put("Tipe", "Cross");
            konfig.put("Kfold", this.kfold.getValue().toString());
        }
        return konfig;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel C45;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel MC;
    private javax.swing.JPanel MC1;
    private javax.swing.JPanel NB;
    private javax.swing.JButton atur_Data;
    private javax.swing.JPanel cross;
    private javax.swing.JRadioButton cross_validation;
    private javax.swing.JPanel data;
    private javax.swing.JPanel data1;
    private javax.swing.JButton doC45;
    private javax.swing.JButton do_naive_bayes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSpinner kfold;
    private javax.swing.JTable matrix_conf_C45;
    private javax.swing.JTable matrix_conf_NB;
    private javax.swing.JPanel panel_c45;
    private javax.swing.JPanel panel_nb;
    private javax.swing.JPanel pengaturan;
    private javax.swing.JScrollPane scroll_nb;
    private javax.swing.JPanel split;
    private javax.swing.JSpinner split_ratio;
    private javax.swing.JRadioButton split_validation;
    private javax.swing.ButtonGroup validation_type;
    // End of variables declaration//GEN-END:variables
}
