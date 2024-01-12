package com.example.myplanets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class PlanetList extends AppCompatActivity {

    AppDataBase appDataBase;
    PlanetDao planetDao;
    ListView planetsListView;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_list);
        planetsListView = findViewById(R.id.planetsList);

        new Thread(
                () -> {
                    accessDataBase();
                    Cursor list = planetDao.getAllPlanets();
                    runOnUiThread(
                            () -> {
                                SimpleCursorAdapter adapter = new SimpleCursorAdapter(PlanetList.this, R.layout.planet, list,
                                        new String[]{list.getColumnName(0), list.getColumnName(1)},
                                        new int[]{R.id.id, R.id.planetName}, 1);
                                planetsListView.setAdapter(adapter);
                                planetsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        TextView id = view.findViewById(R.id.id);
                                        TextView name = view.findViewById(R.id.planetName);
                                        Intent detailsIntent = new Intent(getApplicationContext(), PlanetDetails.class);
                                        detailsIntent.putExtra("name", name.getText().toString());
                                        detailsIntent.putExtra("id", id.getText().toString());
                                        startActivity(detailsIntent);

                                    }
                                });
                            }
                    );
                }
        ).start();

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(
                        () -> {
                            Intent addActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(addActivity);
                        }
                ).start();

            }
        });
    }
    public void accessDataBase(){
        appDataBase = AppDataBase.getAppDataBase(PlanetList.this);
        planetDao = appDataBase.planetDao();
    }
}