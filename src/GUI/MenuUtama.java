/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import Method.Karakter;
/**
 *
 * @author UsEr
 */
public class MenuUtama extends javax.swing.JFrame {
    private Karakter karakter;
    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        namaKarakter = new javax.swing.JTextField();
        btnMulai = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/genderuwo.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-80, 110, 220, 320));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tuyul.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 190, 290));

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("SPECTRAL QUEST");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 36, -1, -1));
        jPanel1.add(namaKarakter, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 172, 204, -1));

        btnMulai.setBackground(new java.awt.Color(102, 0, 0));
        btnMulai.setForeground(new java.awt.Color(204, 204, 204));
        btnMulai.setText("Mulai");
        btnMulai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMulaiActionPerformed(evt);
            }
        });
        jPanel1.add(btnMulai, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 247, 96, -1));

        btnKeluar.setBackground(new java.awt.Color(102, 0, 0));
        btnKeluar.setForeground(new java.awt.Color(204, 204, 204));
        btnKeluar.setText("Keluar Game");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        jPanel1.add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 120, -1));

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Nama Karakter");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 150, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/bg3.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(556, 408));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMulaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMulaiActionPerformed
        // TODO add your handling code here:
        String nama = namaKarakter.getText();
        Karakter karakter = new Karakter(nama);

        MenuBermain f2 = new MenuBermain(karakter);
        f2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMulaiActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnMulai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField namaKarakter;
    // End of variables declaration//GEN-END:variables
}