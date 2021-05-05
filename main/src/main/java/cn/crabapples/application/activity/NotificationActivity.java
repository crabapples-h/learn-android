package cn.crabapples.application.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import cn.crabapples.application.R;

public class NotificationActivity extends AppCompatActivity {
    private final String TAG = "NotificationActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

    }

    /**
     * 发送系统通知
     */
    public void send(View view) {
        //
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("crabapples", "测试通知", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(this, ImageActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 1);
        Notification notification = new NotificationCompat.Builder(NotificationActivity.this, "crabapples")
                .setContentTitle("标题")
                .setContentText("内容")
                .setSmallIcon(R.drawable.icon_menus)
                .setColor(Color.parseColor("#FF0000"))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.image))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(1, notification);
        printLog("测试通知");
    }

    public void cancel(View view) {
        printLog("");
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
    }

    public void back(View view) {
    }

    public void printLog(String content) {
        Log.i(TAG, content);
    }
}
