package vinh.np.weather.asm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vinh.np.weather.asm.Adapter.HomeAdapter;
import vinh.np.weather.asm.model.Weather;
import vinh.np.weather.asm.network.APIManager;

public class WeatherActivity extends AppCompatActivity {
    TextView tvlocation, tviconPhrase, tvtempNow;
    HomeAdapter adapter;
    RecyclerView recyclerView;
    List<Weather> weatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvlocation = findViewById(R.id.tvLocation);
        tviconPhrase = findViewById(R.id.tvIconPhrase);
        tvtempNow = findViewById(R.id.tvTempNow);

        getListData();


        weatherList = new ArrayList<>();
        adapter = new HomeAdapter(this,weatherList);

        //B3:
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        //B4:
        recyclerView = findViewById(R.id.rvWeather);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    private void getListData () {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);

        service.getListData().enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                Log.d("TEST", "onResponse: " + response);
                if (response.body() != null) {
                    weatherList = response.body();
                    Weather weatherNOW = response.body().get(0);
                    tvlocation.setText("Ha Noi");
                    tvtempNow.setText(response.body().get(0).getTemperature().getValue()+" °C");
                    tviconPhrase.setText(weatherNOW.getIconPhrase());
                    adapter.reloadData(weatherList);
                }
            }
            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Log.d("TEST", "onFailure: " + t);
            }
        });
    }
}

