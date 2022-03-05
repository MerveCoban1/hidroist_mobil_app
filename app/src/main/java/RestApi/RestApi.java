package RestApi;

import java.util.List;

import Models.type1Pojo;
import Models.type2Pojo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("/mobileBataryaSicakligiYolla.php")
    Call<List<type1Pojo>> listeleBataryaSicakligi();

    @GET("/mobileHizYolla.php")
    Call<List<type1Pojo>> listeleHiz();

    @GET("/mobileHucreGerilimYolla.php")
    Call<List<type2Pojo>> listeleHucreGerilimi();

    @GET("/mobileKalanEnerjiMiktariYolla.php")
    Call<List<type1Pojo>> listeleKalanEnerjiMiktari();

    @GET("/mobileMotorGerilimiYolla.php")
    Call<List<type2Pojo>> listeleMotorGerilimi();

    @GET("/mobileMotorSicakligiYolla.php")
    Call<List<type2Pojo>> listeleMotorSicakligi();

    @GET("/mobileSocYolla.php")
    Call<List<type1Pojo>> listeleSoc();

}
