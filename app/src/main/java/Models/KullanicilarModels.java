package Models;

public class KullanicilarModels {
    private String isim, resim, departman;

    public KullanicilarModels() {

    }

    public KullanicilarModels(String isim, String resim, String departman) {
        this.isim = isim;
        this.resim = resim;
        this.departman = departman;
    }


    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    @Override
    public String toString() {
        return "KullanicilarModels{" +
                "isim='" + isim + '\'' +
                ", resim='" + resim + '\'' +
                ", departman='" + departman + '\'' +
                '}';
    }
}
