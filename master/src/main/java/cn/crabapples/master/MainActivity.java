package cn.crabapples.master;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button submit = findViewById(R.id.submit);
        Button cancel = findViewById(R.id.cancel);
        SubmitClick submitClick = new SubmitClick();
        CancelClick cancelClick = new CancelClick();
        submit.setOnClickListener(submitClick);
        cancel.setOnClickListener(cancelClick);
    }

    private void showToast(String message, int time) {
        Toast toast = Toast.makeText(MainActivity.this, message, time);
        toast.show();
    }

    class SubmitClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText usernameEdit = findViewById(R.id.username);
            EditText passwordEdit = findViewById(R.id.password);
            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            String message = "username:" + username + "password:" + password;
            Log.i("submit", message);
            showToast(message, 5000);
        }
    }

    class CancelClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText usernameEdit = findViewById(R.id.username);
            EditText passwordEdit = findViewById(R.id.password);
            usernameEdit.setText("");
            passwordEdit.setText("");
            Log.i("cancel", "resetEdit");
        }
    }
}
