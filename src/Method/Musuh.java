package Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Musuh extends Status {

    private static List<Musuh> semuaMusuh = new ArrayList<>();
    private Random acak;
    private String tipe;
    private static final int randNyawa = 20;
    private static final int randAtkMin = 3;
    private static final int randAtkMax = 6;

    public Musuh() {
        acak = new Random();
        this.tipe = getTipeAcak();
        inisialisasiMusuh(false);
    }

    public Musuh(boolean endGame) {
        acak = new Random();
        this.tipe = getTipeAcak();
        inisialisasiMusuh(endGame);
        semuaMusuh.add(this);
    }

    public Musuh(String tipe) {
        acak = new Random();
        this.tipe = tipe;
        inisialisasiMusuh(false);
    }

    private void inisialisasiMusuh(boolean endGame) {
        this.nyawa = getNyawa(tipe);
        this.atkMin = getAtkMin(tipe);
        this.atkMax = getAtkMax(tipe);
        if (endGame) {
            int randomNyawaIncrement = acak.nextInt(randNyawa) + 1;
            int randomAtkMinIncrement = acak.nextInt(randAtkMin) + 1;
            int randomAtkMaxIncrement = acak.nextInt(randAtkMax - 3) + 4;

            this.nyawa += randomNyawaIncrement * (semuaMusuh.size() - 1);
            this.atkMin += randomAtkMinIncrement * (semuaMusuh.size() - 1);
            this.atkMax += randomAtkMaxIncrement * (semuaMusuh.size() - 1);
        }
    }

    public int serang() {
        return acak.nextInt(atkMax - atkMin + 1) + atkMin;
    }

    public String getTipe() {
        return tipe;
    }

    private String getTipeAcak() {
        String[] tipeMusuh = {"Jenglot", "Tuyul", "Kuntilanak", "Pocong"};
        return tipeMusuh[acak.nextInt(tipeMusuh.length)];
    }

    public static List<Musuh> getSemuaMusuh() {
        return semuaMusuh;
    }

    public static String[] getSemuaTipeMusuh() {
        return new String[]{"Jenglot", "Tuyul", "Kuntilanak", "Pocong", "Genderuwo", "Buto Ijo"};
    }

    private int getNyawa(String tipe) {
        switch (tipe) {
            case "Jenglot":
                return 5;
            case "Tuyul":
                return 10;
            case "Kuntilanak":
                return 50;
            case "Pocong":
                return 75;
            case "Genderuwo":
                return 200;
            case "Buto Ijo":
                return 600;
            default:
                return 0;
        }
    }

    private int getAtkMin(String tipe) {
        switch (tipe) {
            case "Jenglot":
                return 5;
            case "Tuyul":
                return 1;
            case "Kuntilanak":
                return 5;
            case "Pocong":
                return 5;
            case "Genderuwo":
                return 10;
            case "Buto Ijo":
                return 20;
            default:
                return 0;
        }
    }

    private int getAtkMax(String tipe) {
        switch (tipe) {
            case "Jenglot":
                return 10;
            case "Tuyul":
                return 5;
            case "Kuntilanak":
                return 15;
            case "Pocong":
                return 15;
            case "Genderuwo":
                return 30;
            case "Buto Ijo":
                return 80;
            default:
                return 0;
        }
    }

    public static void upgradeSemuaMusuh() {
        for (Musuh m : semuaMusuh) {
            m.nyawa += new Random().nextInt(randNyawa) + 1;
            m.atkMin += new Random().nextInt(randAtkMin) + 1;
            m.atkMax += new Random().nextInt(randAtkMax - 3) + 4;
        }
    }
}
