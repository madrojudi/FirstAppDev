package io.artcreativity.firstappdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String USERNAME_KEY = "username_key";
    public static final String PASSWORD_KEY = "password_key";
    public static final String IS_LOGIN_KEY = "is_login";
    private EditText usernameText;
    private EditText passwordText;
    private TextView errorTextView;
    private Button loginBtn;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLogin = sharedPreferences.getBoolean(IS_LOGIN_KEY, false);
        if(isLogin){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
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
        if(!isValid(username)){
            usernameText.setError("Format invalide");
            return;
        }

        if(authentication(username, password)){
            Toast.makeText(this, "Welcome to our application!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_LOGIN_KEY, true);
            editor.apply();
            editor.putString(USERNAME_KEY, username);
            editor.apply();
            editor.putString(PASSWORD_KEY, password);
            editor.apply();
            editor.commit();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
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

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
