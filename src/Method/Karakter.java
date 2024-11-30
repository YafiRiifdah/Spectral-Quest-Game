package Method;

import java.util.Random;

public class Karakter extends Status {

    private String nama;
    private Integer blockMin;
    private Integer blockMax;
    private Integer healMin;
    private Integer healMax;
    private Random acak;

    public Karakter() {
        this.nama = nama;
        this.blockMin = 0;
        this.blockMax = 10;
        this.healMin = 0;
        this.healMax = 5;
        this.nyawa = 100;
        this.atkMin = 0;
        this.atkMax = 10;
        this.acak = new Random();
    }

    public Karakter(String nama) {
        this.nama = nama;
        this.blockMin = 0;
        this.blockMax = 10;
        this.healMin = 0;
        this.healMax = 5;
        this.nyawa = 100;
        this.atkMin = 0;
        this.atkMax = 10;
        this.acak = new Random();
    }

    public Karakter(Karakter karakter) {
        this.nama = nama;
        this.blockMin = blockMin;
        this.blockMax = blockMax;
        this.healMin = healMin;
        this.healMax = healMax;
        this.nyawa = nyawa;
        this.atkMin = atkMin;
        this.atkMax = atkMax;
        this.acak = new Random();
    }

    public int serang() {
        return acak.nextInt(atkMax - atkMin + 1) + atkMin;
    }

    public int heal() {
        int jumlahHeal = acak.nextInt(healMax - healMin + 1) + healMin;
        this.nyawa += jumlahHeal;
        return jumlahHeal;
    }

    public int block() {
        return acak.nextInt(blockMax - blockMin + 1) + blockMin;
    }

    public boolean masihHidup() {
        return getNyawa() > 0;
    }

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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
