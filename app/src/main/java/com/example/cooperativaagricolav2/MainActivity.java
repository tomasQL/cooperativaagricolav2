package com.example.cooperativaagricolav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonInvernaderosClick (View view){
        Intent intent = new Intent(this, ListaUbicacionesActivity.class);
        startActivity(intent);
    }

    public void buttonSensoresClick (View view){
        Intent intent = new Intent (this, ListaSensoresActivity.class);
        startActivity(intent);
    }

    public void buttonAddUbicacionClick (View view){
        Intent intent = new Intent(this, EditarUbicacionActivity.class);
        startActivity(intent);
    }

    public void buttonAddSensorClick (View view){
        Intent intent = new Intent(this, EditarSensorActivity.class);
        startActivity(intent);
    }
}

/*  Referencia de diseño: https://miro.com/app/board/uXjVPQNl4eM=/?share_link_id=257252969225
 *   Creación propia  */