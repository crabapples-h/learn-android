package cn.crabapples.network;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.crabapples.admin.myapplication.R;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.but);
        button.setText("按钮");
        EditText editText = findViewById(R.id.docUrl);
//        editText.setText("https://wenku.baidu.com/view/347160755627a5e9856a561252d380eb629423d6");
        editText.setText("test");
        button.setOnClickListener(new ButtonClick());
    }
    public String getDocUrl(String docUrl){
        try {
            URL url = new URL("http://www.miss-x.cn/rest/bd0001/download?txtUrl="+docUrl);
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
            byte [] data = new byte[1024];
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i !=-1;i = in.read(data)){
                sb.append(new String(data,0,i));
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "空";
    }
    String downloadUrl = "";
    class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            button.setText("测试");
            EditText editText = findViewById(R.id.docUrl);
            final TextView textView = findViewById(R.id.downloadUrl);
            final String docUrl = editText.getText().toString();
            System.out.println("文档地址:"+docUrl);
            Thread thread = new Thread (new Runnable() {
                @Override
                public void run() {
                    downloadUrl = getDocUrl(docUrl);
                }
            });
            thread.start();
            System.out.println("下载地址:"+downloadUrl);
            textView.setText(downloadUrl);
        }
    }
}
