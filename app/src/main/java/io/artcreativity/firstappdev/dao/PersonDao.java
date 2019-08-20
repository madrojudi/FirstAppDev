package io.artcreativity.firstappdev.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import io.artcreativity.firstappdev.entities.Person;

public class PersonDao {
    DatabaseAccess databaseAccess;

    public PersonDao(Context context) {
        databaseAccess = DatabaseAccess.getInstance(context);
    }

    public Person insertPerson(Person person){
        ContentValues values = new ContentValues();
        values.put("first_name", person.firstName);
        values.put("last_name", person.lastName);
        values.put("email", person.email);
        values.put("phone", person.phone);
        values.put("facebook", person.facebook);
        values.put("whatsapp", person.whatsapp);
        long id = databaseAccess.getWritableDatabase().insert(DatabaseAccess.PERSON_TABLE, null, values );
        if(id == -1){

        }

        person.id = id;
        return person;
    }

    public List<Person> getPersons(){
        Cursor cursor = databaseAccess.getReadableDatabase().query(DatabaseAccess.PERSON_TABLE, null, null, null, null, null, null );
        List<Person> persons = new ArrayList<>();
        while (cursor.moveToNext()){
            Person person = new Person();
            person.id = cursor.getLong(cursor.getColumnIndex("id"));
            person.firstName = cursor.getString(cursor.getColumnIndex("first_name"));
            person.lastName = cursor.getString(cursor.getColumnIndex("last_name"));
            person.email = cursor.getString(cursor.getColumnIndex("email"));
            person.phone = cursor.getString(cursor.getColumnIndex("phone"));
            person.facebook = cursor.getString(cursor.getColumnIndex("facebook"));
            person.whatsapp = cursor.getString(cursor.getColumnIndex("whatsapp"));

            persons.add(person);
        }

        return persons;
    }
}
