package io.artcreativity.firstappdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import io.artcreativity.firstappdev.adapters.PersonAdapter;
import io.artcreativity.firstappdev.dao.PersonDao;
import io.artcreativity.firstappdev.entities.Person;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private RecyclerView listPerson;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listPerson = findViewById(R.id.list_person);
        listPerson.setLayoutManager(new LinearLayoutManager(this));
        PersonDao personDao = new PersonDao(this);
        adapter = new PersonAdapter(personDao.getPersons());
        listPerson.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(HomeActivity.this, "Je suis un toast", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
//                        .show();
                Intent intent = new Intent(HomeActivity.this, CreatePersonActivity.class);
                startActivityForResult(intent, 15);
            }
        });

//        String username = sharedPreferences.getString(MainActivity.USERNAME_KEY, getResources().getString(R.string.app_name));
        setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.profile_action) {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 15){
                Person person = (Person) data.getSerializableExtra("person");
                adapter.addPerson(person);
            }
        }
    }
}
