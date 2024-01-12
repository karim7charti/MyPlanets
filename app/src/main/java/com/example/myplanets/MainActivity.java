package com.example.myplanets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText planetName;
    Button addButton;
    Button listButton;

    AppDataBase appDataBase;
    PlanetDao planetDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        planetName = findViewById(R.id.planetName);
        addButton = findViewById(R.id.addButton);
        listButton = findViewById(R.id.listButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(
                        () -> {
                            accessDataBase();
                            Planet planet = new Planet();
                            planet.setName(planetName.getText().toString());
                            planetDao.insertPlanet(planet);
                            Intent listActivity = new Intent(getApplicationContext(), PlanetList.class);
                            startActivity(listActivity);
                        }
                ).start();
                Toast.makeText(MainActivity.this, "Planet added", Toast.LENGTH_SHORT).show();
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(
                        () -> {
                            Intent listActivity = new Intent(getApplicationContext(), PlanetList.class);
                            startActivity(listActivity);
                        }
                ).start();

            }
        });
    }

    public void accessDataBase(){
        appDataBase = AppDataBase.getAppDataBase(MainActivity.this);
        planetDao = appDataBase.planetDao();
    }
}
