package cn.crabapples.localtion_upload;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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
    }

    public void createServiceClick() {
        Intent intent = new Intent(this, MyService.class);
        //启动service服务
        startService(intent);
    }

    public void getLocation(FusedLocationProviderClient fusedLocationClient) {
        Task<Location> location = fusedLocationClient.getLastLocation();
        double latitude = location.getResult().getLatitude();
        double longitude = location.getResult().getLongitude();
        System.err.println(latitude);
        System.err.println(longitude);
    }
}
