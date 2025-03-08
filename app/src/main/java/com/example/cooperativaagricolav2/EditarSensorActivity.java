package com.example.cooperativaagricolav2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cooperativaagricolav2.models.TipoSensor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.cooperativaagricolav2.models.Sensor;

public class EditarSensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_sensor);
    }
    public void buscarSensor(View view) {
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            EditText editTextId = (EditText) findViewById(R.id.editTextNuevoIdSensor);
            EditText editTextNombre = (EditText) findViewById(R.id.editTextNombreSensor);
            EditText editTextIdeal = (EditText) findViewById(R.id.editTextMarcaIdeal);
            EditText editTextDesc = (EditText) findViewById(R.id.editTextDescSensor);

            db.collection("sensores").document(editTextId.getText().toString())
                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Sensor sensor = documentSnapshot.toObject(Sensor.class);
                            editTextId.setText(sensor.getId());
                            editTextNombre.setText(sensor.getNombre());
                            editTextIdeal.setText((int) sensor.getIdeal());
                            editTextDesc.setText(sensor.getDescripcion());

                        }
                    });
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void guardarSensor(View view) {
        try {
            // Obtener acceso a los campos de texto EditText
            EditText editTextId = (EditText) findViewById(R.id.editTextNuevoIdSensor);
            EditText editTextNombre = (EditText) findViewById(R.id.editTextNombreSensor);
            EditText editTextDescripcion = (EditText) findViewById(R.id.editTextDescSensor);
            EditText editTextIdeal = (EditText) findViewById(R.id.editTextMarcaIdeal);
            Spinner spinnerTipoSensor = (Spinner) findViewById(R.id.spinnerTipoSensor);

            // Conjunto de datos a insertar como documento a la colección
            Sensor nuevo = new Sensor(
                    Integer.parseInt(editTextId.getText().toString()),
                    editTextNombre.getText().toString(),
                    editTextDescripcion.getText().toString(),
                    Float.parseFloat(editTextIdeal.getText().toString()));

            String text = spinnerTipoSensor.getSelectedItem().toString();
            TipoSensor nuevoTipo = new TipoSensor(
                    Integer.parseInt(editTextId.getText().toString()),
                    text);


            // Crear objeto de conexión a Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("sensores").document(String.valueOf(nuevo.getId()))
                    .set(nuevo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            // En caso de éxito
                            Toast.makeText(EditarSensorActivity.this, "Sensor ingresado correctamente", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // En caso de fallo
                            Toast.makeText(EditarSensorActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarSensor(View view){
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            EditText editTextId = (EditText) findViewById(R.id.editTextNuevoIdSensor);
            db.collection("sensores").document(editTextId.getText().toString())
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(EditarSensorActivity.this, "El sensor ha sido eliminado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditarSensorActivity.this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error al eliminar el documento", e);
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

