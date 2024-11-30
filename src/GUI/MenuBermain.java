/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Method.Karakter;
import Method.Musuh;
import Method.Teman;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author UsEr
 */
public class MenuBermain extends javax.swing.JFrame {

    private boolean endGame = false;
    private Karakter karakter;
    private Musuh musuh;
    private List<Teman> temanList;
    private int musuhKalah;
    private Random random;
    private int block;
    private static Set<String> namaTemanAda = new HashSet<>();
    private static final List<String> allPossibleNames = List.of("Ale", "Hana", "Yafi", "Hawa", "Arva", "Zada", 
            "Ragil", "Wildan", "Eko", "Dhiya", "Daffa", "Awang", "Maula", "Calvin", "Dio", "Fikri", "Fahril", 
            "Vonda", "Ghaly", "Fauzan", "Adrian", "Arief", "Isa", "Hazel", "Achmad", "Fani", "Kanisius", "Ayu", 
            "Angel", "Rafif");

    /**
     * Creates new form MenuBermain
     */
    public MenuBermain() {
        initComponents();

    }

    public MenuBermain(Karakter karakter) {
        initComponents();
        this.karakter = karakter;
        this.musuh = new Musuh("Tuyul");
        this.temanList = new ArrayList<>();
        random = new Random();
        updateStatusTabel();
        updateMusuhStatus();
        tampilanText.setText(musuh.getTipe() + " telah muncul\n");
    }

    public MenuBermain(Karakter karakter, List<Teman> temanList, boolean endGame, int musuhKalah) {
        initComponents();
        this.karakter = karakter;
        this.temanList = temanList;
        this.endGame = endGame;
        this.musuhKalah = musuhKalah;
        this.musuh = new Musuh();
        random = new Random();
        updateStatusTabel();
        updateMusuhStatus();
        tampilanText.setText(musuh.getTipe() + " telah muncul\n");
    }

    private void updateStatusTabel() {
        DefaultTableModel model = (DefaultTableModel) tabelStatus.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{
            karakter.getNama(),
            karakter.getNyawa(),
            karakter.getAtkMin() + " - " + karakter.getAtkMax()
        });
        updateTemanStatus();
    }

    private void updateMusuhStatus() {
        namaMusuh.setText("Tipe: " + musuh.getTipe());
        if (musuh.getNyawa() <= 0) {
            musuh.setNyawa(0);
            jumlahNyawa.setText("Nyawa: " + String.valueOf(musuh.getNyawa()));
        } else {
            jumlahNyawa.setText("Nyawa: " + String.valueOf(musuh.getNyawa()));
        }
        jumlahAtk.setText("Range ATK: " + musuh.getAtkMin() + " - " + musuh.getAtkMax());
    }

    private void updateTemanStatus() {
        DefaultTableModel model = (DefaultTableModel) tabelStatus.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{
            karakter.getNama(),
            karakter.getNyawa(),
            karakter.getAtkMin() + " - " + karakter.getAtkMax()
        });

        for (Teman t : temanList) {
            model.addRow(new Object[]{
                t.getNama(),
                t.getNyawa(),
                t.getAtkMin() + " - " + t.getAtkMax()
            });
        }
    }

    private void musuhBaru() {
   musuhKalah++;
   if (musuhKalah >= 11 && endGame) {
       musuh = new Musuh(true);
       musuh.upgradeSemuaMusuh();
   } else {
       switch (musuhKalah) {
           case 5:
               musuh = new Musuh("Genderuwo");
               break;
           case 10:
               musuh = new Musuh("Buto Ijo");
               break;
           case 11:
               MenuMenang f2 = new MenuMenang(musuhKalah, karakter, temanList);
               f2.setVisible(true);
               this.dispose();
               break;
           default:
                   musuh = new Musuh();
               break;
       }
   }
   updateMusuhStatus();
}

    private void musuhMati() {
        String[] options = {"Upgrade", "Rekrut Teman", "Skip"};
        int choice = JOptionPane.showOptionDialog(this, "Pilih opsi anda:", "Opsi",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                karakter.setAtkMin(karakter.getAtkMin() + 5);
                karakter.setAtkMax(karakter.getAtkMax() + 5);
                karakter.setBlockMin(karakter.getBlockMin() + 5);
                karakter.setBlockMax(karakter.getBlockMax() + 5);
                karakter.setHealMin(karakter.getHealMin() + 5);
                karakter.setHealMax(karakter.getHealMax() + 5);
                if (!temanList.isEmpty()) {
                    for (Teman t : temanList) {
                        t.setAtkMin(t.getAtkMin() + 5);
                        t.setAtkMax(t.getAtkMax() + 5);
                        t.setBlockMin(t.getBlockMin() + 5);
                        t.setBlockMax(t.getBlockMax() + 5);
                        t.setHealMin(t.getHealMin() + 5);
                        t.setHealMax(t.getHealMax() + 5);
                    }
                }
                break;
            case 1:
                if (namaTemanAda.size() >= allPossibleNames.size()) {
                    JOptionPane.showMessageDialog(this, "Teman Anda sudah lengkap.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    musuhMati();
                    return;
                }

                Teman newTeman;
                String namaTeman;

                do {
                    newTeman = new Teman();
                    namaTeman = newTeman.getNama();
                } while (namaTemanAda.contains(namaTeman));

                temanList.add(newTeman);
                namaTemanAda.add(namaTeman);

                updateTemanStatus();
                tampilanText.append(karakter.getNama() + " telah merekrut teman baru: " + namaTeman + "\n");
                break;
            case 2:
                break;
            default:
                return;
        }

        musuhBaru();
    }

    private boolean namaTemanUnik(String namaTeman) {
        return !namaTemanAda.contains(namaTeman);
    }

    private void temanAction() {
        for (Teman t : temanList) {
            if (t.getNyawa() > 0 && musuh.getNyawa() > 0) {
                int action = random.nextInt(3);
                switch (action) {
                    case 0:
                        int damage = t.serang();
                        musuh.setNyawa(musuh.getNyawa() - damage);
                        tampilanText.append(t.getNama() + " menyerang " + musuh.getTipe() + " sebanyak " + damage + " damage.\n");
                        break;

                    case 1:
                        Teman temanNyawaRendah = null;
                        for (Teman potensiTargetHeal : temanList) {
                            if (potensiTargetHeal.getNyawa() > 0 && (temanNyawaRendah == null || potensiTargetHeal.getNyawa() < temanNyawaRendah.getNyawa())) {
                                temanNyawaRendah = potensiTargetHeal;
                            }
                        }

                        if (temanNyawaRendah != null && temanNyawaRendah.getNyawa() < 100) {
                            int jumlahHeal = t.heal(temanNyawaRendah);
                            tampilanText.append(t.getNama() + " menyembuhkan " + temanNyawaRendah.getNama() + " sebanyak " + jumlahHeal + " nyawa.\n");
                        } else if (karakter.getNyawa() < 100) {
                            int jumlahHeal = t.heal(karakter);
                            tampilanText.append(t.getNama() + " menyembuhkan " + karakter.getNama() + " sebanyak " + jumlahHeal + " nyawa.\n");
                        }
                        break;

                    case 2:
                        int blockAmount = t.block();
                        tampilanText.append(t.getNama() + " telah memblokir damage sebanyak " + blockAmount + " damage!\n");
                        break;
                }

                if (t.getNyawa() <= 0) {
                    tampilanText.append(t.getNama() + " telah tewas dalam pertempuran.\n");
                    temanList.remove(t);
                    break;
                }
            }
        }
    }

    private void musuhAttack() {
        if (musuh.getNyawa() > 0) {
            int target = 0;
            int damage = musuh.serang();
            damage = damage - block;
            if (damage < 0) {
                damage = 0;
            }
            if (temanList.size() > 0) {
                target = random.nextInt(2);
                if (target == 0) {
                    karakter.setNyawa(karakter.getNyawa() - damage);
                    tampilanText.append(musuh.getTipe() + " menyerang " + karakter.getNama() + " sebanyak " + damage + " damage.\n");
                } else {
                    int temanIndex = random.nextInt(temanList.size());
                    Teman targetTeman = temanList.get(temanIndex);
                    targetTeman.setNyawa(targetTeman.getNyawa() - damage);
                    tampilanText.append(musuh.getTipe() + " menyerang " + targetTeman.getNama() + " sebanyak " + damage + " damage.\n");

                    if (targetTeman.getNyawa() <= 0) {
                        tampilanText.append(targetTeman.getNama() + " telah kalah dan tidak dapat bertarung lagi.\n");
                        temanList.remove(temanIndex);
                    }
                }
            } else if (target == 0) {
                karakter.setNyawa(karakter.getNyawa() - damage);
                tampilanText.append(musuh.getTipe() + " menyerang " + karakter.getNama() + " sebanyak " + damage + " damage.\n");
            }
        }
    }

    public void setTemanList(List<Teman> temanList) {
        this.temanList = temanList;
    }

    public void setKarakter(Karakter karakter) {
        this.karakter = karakter;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public void setMusuhKalah(int musuhKalah) {
        this.musuhKalah = musuhKalah;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tampilanText = new javax.swing.JTextArea();
        btnSatu = new javax.swing.JButton();
        btnDua = new javax.swing.JButton();
        btnTiga = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelStatus = new javax.swing.JTable();
        namaMusuh = new javax.swing.JLabel();
        jumlahAtk = new javax.swing.JLabel();
        jumlahNyawa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tampilanText.setBackground(new java.awt.Color(102, 102, 102));
        tampilanText.setColumns(20);
        tampilanText.setRows(5);
        tampilanText.setEnabled(false);
        jScrollPane1.setViewportView(tampilanText);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 440, 135));

        btnSatu.setBackground(new java.awt.Color(102, 0, 0));
        btnSatu.setForeground(new java.awt.Color(204, 204, 204));
        btnSatu.setText("Serang");
        btnSatu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSatuActionPerformed(evt);
            }
        });
        jPanel1.add(btnSatu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 102, -1));

        btnDua.setBackground(new java.awt.Color(102, 0, 0));
        btnDua.setForeground(new java.awt.Color(204, 204, 204));
        btnDua.setText("Heal");
        btnDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 102, -1));

        btnTiga.setBackground(new java.awt.Color(102, 0, 0));
        btnTiga.setForeground(new java.awt.Color(204, 204, 204));
        btnTiga.setText("Block");
        btnTiga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTigaActionPerformed(evt);
            }
        });
        jPanel1.add(btnTiga, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 102, -1));

        tabelStatus.setBackground(new java.awt.Color(102, 0, 0));
        tabelStatus.setForeground(new java.awt.Color(255, 255, 255));
        tabelStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Nyawa", "ATK / Heal / Block"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelStatus.setSelectionBackground(new java.awt.Color(204, 0, 0));
        tabelStatus.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabelStatus.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelStatus);
        if (tabelStatus.getColumnModel().getColumnCount() > 0) {
            tabelStatus.getColumnModel().getColumn(2).setPreferredWidth(120);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 268, 105));

        namaMusuh.setForeground(new java.awt.Color(204, 204, 204));
        namaMusuh.setText("Tipe: ...");
        jPanel1.add(namaMusuh, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jumlahAtk.setForeground(new java.awt.Color(204, 204, 204));
        jumlahAtk.setText("Range ATK: 00 - 00");
        jPanel1.add(jumlahAtk, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jumlahNyawa.setForeground(new java.awt.Color(204, 204, 204));
        jumlahNyawa.setText("Nyawa: ...");
        jPanel1.add(jumlahNyawa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/bg3.jpeg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 400));

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

    private void btnSatuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSatuActionPerformed
        // TODO add your handling code here:
        if (musuh.getNyawa() > 0) {
            int damage = karakter.serang();
            musuh.setNyawa(musuh.getNyawa() - damage);
            tampilanText.append(karakter.getNama() + " menyerang " + musuh.getTipe() + " sebanyak " + damage + " damage.\n");
        }

        if (musuh.getNyawa() > 0) {
            if (temanList.size() > 0) {
                temanAction();
            }
            musuhAttack();
            updateStatusTabel();
            block = 0;
        }
        tampilanText.append("\n");
        if (karakter.getNyawa() <= 0) {
            new MenuKalah(musuhKalah, karakter.getNama()).setVisible(true);
            this.dispose();
        }
        updateMusuhStatus();
        if (musuh.getNyawa() <= 0) {
            musuhMati();
            updateStatusTabel();
        }
    }//GEN-LAST:event_btnSatuActionPerformed

    private void btnDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuaActionPerformed
        // TODO add your handling code here:
        if (musuh.getNyawa() > 0) {
            karakter.heal();
            tampilanText.append(karakter.getNama() + " menyembuhkan diri sendiri sebanyak " + karakter.heal() + " nyawa.\n");
            updateStatusTabel();
        }

        if (musuh.getNyawa() > 0) {
            if (temanList.size() > 0) {
                temanAction();
            }
            musuhAttack();
            if (karakter.getNyawa() >= 100) {
                karakter.setNyawa(100);
            }
            updateStatusTabel();
            block = 0;
        }
        tampilanText.append("\n");
        if (karakter.getNyawa() <= 0) {
            new MenuKalah(musuhKalah, karakter.getNama()).setVisible(true);
            this.dispose();
        }
        updateMusuhStatus();
        if (musuh.getNyawa() <= 0) {
            musuhMati();
            updateStatusTabel();
        }
    }//GEN-LAST:event_btnDuaActionPerformed

    private void btnTigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTigaActionPerformed
        // TODO add your handling code here:
        if (musuh.getNyawa() > 0) {
            block = +karakter.block();
            tampilanText.append(karakter.getNama() + " telah memblokir serangan sebanyak " + karakter.block() + " damage!\n");
        }

        if (musuh.getNyawa() > 0) {
            if (temanList.size() > 0) {
                temanAction();
            }
            musuhAttack();
            updateStatusTabel();
            block = 0;
        }
        tampilanText.append("\n");
        if (karakter.getNyawa() <= 0) {
            new MenuKalah(musuhKalah, karakter.getNama()).setVisible(true);
            this.dispose();
        }
        updateMusuhStatus();
        if (musuh.getNyawa() <= 0) {
            musuhMati();
            updateStatusTabel();
        }
    }//GEN-LAST:event_btnTigaActionPerformed

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
            java.util.logging.Logger.getLogger(MenuBermain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuBermain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuBermain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuBermain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuBermain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDua;
    private javax.swing.JButton btnSatu;
    private javax.swing.JButton btnTiga;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jumlahAtk;
    private javax.swing.JLabel jumlahNyawa;
    private javax.swing.JLabel namaMusuh;
    private javax.swing.JTable tabelStatus;
    private javax.swing.JTextArea tampilanText;
    // End of variables declaration//GEN-END:variables
}
