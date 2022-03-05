package Models;

public class type1Pojo {
    private String deger;
    private String tarih;

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    @Override
    public String toString() {
        return "type1Pojo{" +
                "deger='" + deger + '\'' +
                ", tarih='" + tarih + '\'' +
                '}';
    }
}
