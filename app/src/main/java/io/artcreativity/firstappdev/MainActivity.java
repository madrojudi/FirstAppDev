package io.artcreativity.firstappdev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String USERNAME_KEY = "username_key";
    private EditText usernameText;
    private EditText passwordText;
    private TextView errorTextView;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        errorTextView = findViewById(R.id.error_view);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(this);
    }

    public void btnOnClick(View view){
        System.out.println("button clicked!");
        Log.i("TAG", "btnOnClick: button clicked!");
        Toast.makeText(this, "button clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        errorTextView.setVisibility(View.GONE);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if(TextUtils.isEmpty(username)){
            usernameText.setError("Ce champ est require");
        }
        if(TextUtils.isEmpty(password)){
            passwordText.setError("Ce champ est require");
            return;
        }

        if(authentication(username, password)){
            Toast.makeText(this, "Welcome to our application!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra(USERNAME_KEY, username);
            startActivity(intent);
        }else{
            errorTextView.setText(R.string.login_error);
            errorTextView.setVisibility(View.VISIBLE);
        }
    }

    public boolean authentication(String username, String password){
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(username.equals("email@gmail.com")){
            return password.equals("12345678");
        }
        return false;
    }
}
