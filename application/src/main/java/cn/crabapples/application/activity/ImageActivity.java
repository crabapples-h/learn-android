package cn.crabapples.application.activity;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view);
    }

    public void showToast() {
        Toast.makeText(ImageActivity.this, "喜欢你", Toast.LENGTH_SHORT).show();
    }


}
