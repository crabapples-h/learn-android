package cn.crabapples.main.activity.demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;
import cn.crabapples.main.entity.User;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class XmlDemoActivity extends AppCompatActivity {
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_demo);
        initData();
    }

    public void initData() {
        users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.name = "user:" + i;
            user.sex = (byte) (i % 2);
            user.age = i;
            users.add(user);
        }
    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String content) {
        String TAG = "crabapples";
        Log.i(TAG, content);
    }

    public void back(View view) {
    }


    public void saveXml(View view) throws IOException {
        System.err.println(users);
        XmlSerializer xmlSerializer = Xml.newSerializer();
        xmlSerializer.setOutput(openFileOutput("user.xml", MODE_PRIVATE), "utf-8");
        xmlSerializer.startDocument("utf-8", true);
        xmlSerializer.startTag(null, "users");
        for (User user : users) {
            xmlSerializer.startTag(null, "user");

            xmlSerializer.startTag(null, "name");
            xmlSerializer.text(user.name);
            xmlSerializer.endTag(null, "name");

            xmlSerializer.startTag(null, "sex");
            xmlSerializer.text(String.valueOf(user.sex));
            xmlSerializer.endTag(null, "sex");

            xmlSerializer.startTag(null, "age");
            xmlSerializer.text(String.valueOf(user.age));
            xmlSerializer.endTag(null, "age");

            xmlSerializer.endTag(null, "user");
        }
        xmlSerializer.endDocument();
    }

    public void readXml(View view) throws IOException, XmlPullParserException {
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(openFileInput("user.xml"), "utf-8");
        ArrayList<User> users = null;
        User user = null;
        for (int eventType = xmlPullParser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = xmlPullParser.next()) {
            switch (eventType) {
                case XmlPullParser.START_TAG: {
                    if ("users".equals(xmlPullParser.getName())) {
                        users = new ArrayList<>();
                    } else if ("user".equals(xmlPullParser.getName())) {
                        user = new User();
                    } else if ("name".equals(xmlPullParser.getName())) {
                        user.name = xmlPullParser.nextText();
                    } else if ("sex".equals(xmlPullParser.getName())) {
                        user.sex = Byte.parseByte(xmlPullParser.nextText());
                    } else if ("age".equals(xmlPullParser.getName())) {
                        user.age = Integer.parseInt(xmlPullParser.nextText());
                    }
                    break;
                }
                case XmlPullParser.END_TAG: {
                    users.add(user);
                }
            }
        }
        printLog(users.toString());
    }
}
