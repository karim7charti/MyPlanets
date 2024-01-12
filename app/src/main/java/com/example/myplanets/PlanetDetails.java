package com.example.myplanets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlanetDetails extends AppCompatActivity {
    EditText planetName;
    Button editButton;
    Button deleteButton;
    AppDataBase appDataBase;
    PlanetDao planetDao;
    String id;
    String name;
    Planet currentPlanet = new Planet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_details);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        planetName = findViewById(R.id.planetName);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        new Thread(
                () -> {
                    accessDataBase();
                    currentPlanet = planetDao.getPlanetById(Integer.parseInt(id));
                    runOnUiThread(() -> {
                      planetName.setText(currentPlanet.getName());
                    });
                }
        ).start();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                accessDataBase();
                Planet planet = currentPlanet;
                planet.set_id(Integer.parseInt(id));
                planet.setName(planetName.getText().toString());
                planetDao.updatePlanet(planet);
                Intent intent = new Intent(getApplicationContext(), PlanetList.class);
                startActivity(intent);
                }).start();
                Toast.makeText(PlanetDetails.this, "Planet updated", Toast.LENGTH_SHORT).show();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    accessDataBase();
                    planetDao.deletePlanet(currentPlanet);
                    Intent intent = new Intent(getApplicationContext(), PlanetList.class);
                    startActivity(intent);
                }).start();
                Toast.makeText(PlanetDetails.this, "Planet deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void accessDataBase(){
        appDataBase = AppDataBase.getAppDataBase(PlanetDetails.this);
        planetDao = appDataBase.planetDao();
    }
}