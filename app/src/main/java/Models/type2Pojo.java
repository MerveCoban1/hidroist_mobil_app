package Models;

public class type2Pojo {
    private String isim;
    private String deger;
    private String tarih;

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

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
        return "type2Pojo{" +
                "isim='" + isim + '\'' +
                ", deger='" + deger + '\'' +
                ", tarih='" + tarih + '\'' +
                '}';
    }
}
