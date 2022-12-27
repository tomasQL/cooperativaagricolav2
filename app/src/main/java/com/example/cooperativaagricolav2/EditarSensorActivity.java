package com.example.cooperativaagricolav2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EditarSensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_sensor);
    }

    public void buttonGuardarOnClick(View view) {
        Toast.makeText(this, "Sensor guardado", Toast.LENGTH_SHORT).show();
    }

    public void buttonEliminarOnClick(View view) {
        Toast.makeText(this, "Sensor eliminado", Toast.LENGTH_SHORT).show();
    }
}