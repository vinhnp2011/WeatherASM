package vinh.np.weather.asm.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vinh.np.weather.asm.model.Weather;

public interface APIManager {
    String SERVER = "http://dataservice.accuweather.com/";
    String Key ="93Qg780lHwYM4SO58n7DFPLqHg4oKADn";
    String URI = "forecasts/v1/hourly/12hour/353412?apikey="+Key+"&language=vi-vn&metric=true";

    @GET(URI)
    Call<List<Weather>> getListData();

}
