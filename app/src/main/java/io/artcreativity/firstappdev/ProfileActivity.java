package io.artcreativity.firstappdev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private String gender = "Homme";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        radioGroup = findViewById(R.id.group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.homme){
                    gender = "Homme";
                    Toast.makeText(ProfileActivity.this, "Vous etes un homme!", Toast.LENGTH_SHORT).show();
                }
                else if(checkedId == R.id.femme){
                    gender = "Femme";
                    Toast.makeText(ProfileActivity.this, "Vous etes un femme!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ProfileActivity.this, "Veuillez prier Dieu!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
