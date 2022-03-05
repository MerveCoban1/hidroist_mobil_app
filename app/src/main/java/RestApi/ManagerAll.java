package RestApi;


import java.util.List;

import Models.type1Pojo;
import Models.type2Pojo;
import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<List<type1Pojo>> gosterBataryaSicaklik() {
        Call<List<type1Pojo>> a = getRestApi().listeleBataryaSicakligi();
        return a;
    }

    public Call<List<type1Pojo>> gosterHiz() {
        Call<List<type1Pojo>> b = getRestApi().listeleHiz();
        return b;
    }

    public Call<List<type2Pojo>> gosterHucreGerilim() {
        Call<List<type2Pojo>> c = getRestApi().listeleHucreGerilimi();
        return c;
    }

    public Call<List<type1Pojo>> gosterKalanEnerjiMiktari() {
        Call<List<type1Pojo>> a = getRestApi().listeleKalanEnerjiMiktari();
        return a;
    }

    public Call<List<type2Pojo>> gosterMotorGerilim() {
        Call<List<type2Pojo>> b = getRestApi().listeleMotorGerilimi();
        return b;
    }

    public Call<List<type2Pojo>> gosterMotorSicaklik() {
        Call<List<type2Pojo>> c = getRestApi().listeleMotorSicakligi();
        return c;
    }

    public Call<List<type1Pojo>> gosterSoc() {
        Call<List<type1Pojo>> c = getRestApi().listeleSoc();
        return c;
    }
}
