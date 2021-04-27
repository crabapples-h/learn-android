//package com.example.admin.myapplication;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import java.io.BufferedInputStream;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class GetDocUrl {
//
//    public void getDocUrl(){
//        try {
//            EditText editText = findViewById(R.id.docUrl);
//            String docUrl = editText.getText().toString();
//            URL url = new URL("http://www.miss-x.cn:61760/download?txtUrl="+docUrl);
//            URLConnection conn = url.openConnection();
//            conn.setDoInput(true);
//            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
//            byte [] data = new byte[1024];
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0 ; i !=-1;i = in.read(data)){
//                sb.append(new String(data,0,i));
//            }
//            System.out.println(data.toString());
//            TextView textView = findViewById(R.id.downloadUrl);
//            textView.setText(sb.toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//}
