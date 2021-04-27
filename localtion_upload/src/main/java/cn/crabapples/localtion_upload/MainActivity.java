package cn.crabapples.localtion_upload;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.hello);
        textView.setText("喜欢你");
        createServiceClick();
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation(fusedLocationClient);
        ClickListener clickListener = new ClickListener();
        textView.setOnClickListener(clickListener);
    }

    public void createServiceClick() {
        Intent intent = new Intent(this, MyService.class);
        //启动service服务
        startService(intent);
    }

    static class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextView textView = (TextView) view;
            textView.setText(Math.random() + ":嗯哼");
        }
    }

    public void getLocation(FusedLocationProviderClient fusedLocationClient) {
        Task<Location> location = fusedLocationClient.getLastLocation();
        System.err.println(location);
//        double longitude = location.getResult().getLongitude();
//        double latitude = location.getResult().getLatitude();
//        System.err.println(latitude);
//        System.err.println(longitude);
    }
}
