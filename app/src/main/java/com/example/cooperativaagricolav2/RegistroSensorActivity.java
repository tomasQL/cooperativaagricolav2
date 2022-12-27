package com.example.cooperativaagricolav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.cooperativaagricolav2.models.Sensor;

public class RegistroSensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_sensor);
    }

    public void guardarSensor(View view) {
        try {
            // Obtener acceso a los campos de texto EditText
            EditText editTextId = findViewById(R.id.editTextNuevoId);
            EditText editTextNombre = findViewById(R.id.editTextNuevoNombre);
            EditText editTextDescripcion = findViewById(R.id.editTextNuevoDescripcion);
            EditText editTextIdeal = findViewById(R.id.editTextNuevoIdeal);

            // Conjunto de datos a insertar como documento a la colección
            Sensor nuevo = new Sensor(
                    Integer.parseInt(editTextId.getText().toString()),
                    editTextNombre.getText().toString(),
                    editTextDescripcion.getText().toString(),
                    Float.parseFloat(editTextIdeal.getText().toString()));

            // Crear objeto de conexión a Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("sensores").document(String.valueOf(nuevo.getId()))
                    .set(nuevo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            // En caso de éxito
                            Toast.makeText(RegistroSensorActivity.this, "Sensor ingresado correctamente", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // En caso de fallo
                            Toast.makeText(RegistroSensorActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

