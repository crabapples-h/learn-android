package cn.crabapples.receiver_demo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;

public class ReceiverActivity extends BroadcastReceiver {
    private final String TAG = "ReceiverActivity";

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("number", context.MODE_PRIVATE);
        String number = sharedPreferences.getString("number", "") + getResultData();
        Log.v(TAG, "拨打电话:" + number);
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("crabapples", "测试通知", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, 1);
        Notification notification = new NotificationCompat.Builder(context, "crabapples")
                .setContentTitle("标题")
                .setContentText("拨打电话:" + number)
                .setSmallIcon(R.drawable.icon_menus)
                .setColor(Color.parseColor("#FF0000"))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(1, notification);
        setResultData(number);
    }
}