package Method;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

public class Teman extends Status {

    private Random acak;
    private String nama;
    private static final String[] NAMA = {"Ale", "Hana", "Yafi", "Hawa", "Arva", "Zada", "Ragil", "Wildan", "Eko",
        "Dhiya", "Daffa", "Awang", "Maula", "Calvin", "Dio", "Fikri", "Fahril", "Vonda", "Ghaly", "Fauzan", 
        "Adrian", "Arief", "Isa", "Hazel", "Achmad", "Fani", "Kanisius", "Ayu", "Angel", "Rafif"};
    private Integer blockMin;
    private Integer blockMax;
    private Integer healMin;
    private Integer healMax;

    private static Set<String> namaTemanAda = new HashSet<>();

    public Teman() {
        acak = new Random();
        this.nama = NAMA[acak.nextInt(NAMA.length)];
        while (namaTemanAda.contains(nama)) {
            this.nama = NAMA[acak.nextInt(NAMA.length)];
        }
        namaTemanAda.add(this.nama);
        this.blockMin = 0;
        this.blockMax = 10;
        this.healMin = 5;
        this.healMax = 15;
        this.nyawa = 100;
        this.atkMin = 0;
        this.atkMax = 10;
    }

    public int serang() {
        return acak.nextInt(getAtkMax() - getAtkMin() + 1) + getAtkMin();
    }

    public boolean masihHidup() {
        return getNyawa() > 0;
    }

    public int heal(Status target) {
        int jumlahHeal = acak.nextInt(healMax - healMin + 1) + healMin;
        target.setNyawa(Math.min(target.getNyawa() + jumlahHeal, 100));
        return jumlahHeal;
    }

    public int block() {
        return acak.nextInt(blockMax - blockMin + 1) + blockMin;
    }

    public String getNama() {
        return nama;
    }

    // Getter dan setter untuk atribut lainnya
    public Integer getBlockMin() {
        return blockMin;
    }

    public void setBlockMin(Integer blockMin) {
        this.blockMin = blockMin;
    }

    public Integer getBlockMax() {
        return blockMax;
    }

    public void setBlockMax(Integer blockMax) {
        this.blockMax = blockMax;
    }

    public Integer getHealMin() {
        return healMin;
    }

    public void setHealMin(Integer healMin) {
        this.healMin = healMin;
    }

    public Integer getHealMax() {
        return healMax;
    }

    public void setHealMax(Integer healMax) {
        this.healMax = healMax;
    }

    public Integer getAtkMin() {
        return atkMin;
    }

    public void setAtkMin(Integer atkMin) {
        this.atkMin = atkMin;
    }

    public Integer getAtkMax() {
        return atkMax;
    }

    public void setAtkMax(Integer atkMax) {
        this.atkMax = atkMax;
    }
    
        public void updateTemanStatus(DefaultTableModel model) {
        model.addRow(new Object[]{
            getNama(),
            getNyawa(),
            getAtkMin() + " - " + getAtkMax()
        });
    }
}   
