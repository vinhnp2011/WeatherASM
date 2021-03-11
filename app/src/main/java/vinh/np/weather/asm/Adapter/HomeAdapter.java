package vinh.np.weather.asm.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vinh.np.weather.asm.R;
import vinh.np.weather.asm.model.ConvertDateTime;
import vinh.np.weather.asm.model.ConvertIconImage;
import vinh.np.weather.asm.model.Weather;

public class HomeAdapter extends RecyclerView.Adapter{

    Activity activity;
    List<Weather> weatherList;
    ConvertDateTime cv = new ConvertDateTime();
    ConvertIconImage cvim = new ConvertIconImage();

    public HomeAdapter(Activity activity, List<Weather> weatherList) {
        this.activity = activity;
        this.weatherList = weatherList;
    }

    public  void reloadData(List<Weather> weatherList){
        this.weatherList = weatherList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.activity_item,parent,false);
        HomeHolder hd = new HomeHolder(itemView);
        return hd;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeHolder hd = (HomeHolder) holder;
        Weather model = weatherList.get(position);
        hd.tvTime.setText(cv.convertTime(model.getDateTime()));
        hd.tvTemperature.setText(model.getTemperature().getValue()+" C");
        Glide.with(activity).load(cvim.convertIcon(model.getWeatherIcon())).into(hd.ivIcon);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
    public class HomeHolder extends RecyclerView.ViewHolder {
        TextView tvTime,tvTemperature;
        ImageView ivIcon;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}
