package io.artcreativity.firstappdev;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import io.artcreativity.firstappdev.dao.PersonDao;
import io.artcreativity.firstappdev.entities.Person;

public class CreatePersonActivity extends AppCompatActivity {

    private TextInputEditText firstNameText;
    private TextInputEditText lastNameText;
    private TextInputEditText emailText;
    private TextInputEditText phoneText;
    private TextInputEditText facebookText;
    private TextInputEditText whatsappText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_person);
        Toolbar toolbar = findViewById(R.id.toolbar);
        firstNameText = findViewById(R.id.first_name);
        lastNameText = findViewById(R.id.last_name);
        emailText = findViewById(R.id.email);
        phoneText = findViewById(R.id.phone);
        facebookText = findViewById(R.id.facebook);
        whatsappText = findViewById(R.id.whatsapp);

        setSupportActionBar(toolbar);

    }

    public void createPersonAction(View view){
        Person person = new Person(firstNameText.getText().toString(),
                lastNameText.getText().toString(),
                emailText.getText().toString(),
                phoneText.getText().toString(),
                facebookText.getText().toString(),
                whatsappText.getText().toString());
        PersonDao personDao = new PersonDao(this);
        person = personDao.insertPerson(person);
        Log.i("TAG", "createPersonAction: " + person);
        Intent intent = new Intent();
        intent.putExtra("person", person);
//        setIntent(intent);
        setResult(RESULT_OK, intent);
        finish();
    }
}
