package io.artcreativity.firstappdev.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import io.artcreativity.firstappdev.R;
import io.artcreativity.firstappdev.entities.Person;

public class PersonAdapter extends RecyclerView.Adapter<PersonView> {
    final List<Person> persons;

    public PersonAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public PersonView onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LinearLayout v = (LinearLayout)LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.person, viewGroup, false);
        return new PersonView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonView personView, int i) {
        Person person = persons.get(i);
        personView.lastNameView.setText(person.lastName);
        personView.firstNameView.setText(person.firstName);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public void addPerson(Person person) {
        persons.add(person);
        notifyItemInserted(persons.size() - 1);
    }
}

class PersonView extends RecyclerView.ViewHolder{

    public final TextView firstNameView;
    public final TextView lastNameView;
    public PersonView(@NonNull View itemView) {
        super(itemView);
        firstNameView = itemView.findViewById(R.id.first_name);
        lastNameView = itemView.findViewById(R.id.last_name);
    }
}
