package com.example.cooperativaagricolav2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooperativaagricolav2.models.Ubicacion;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class EditarUbicacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ubicacion);
    }

    public void agregar(View view) {
        try {
            // Obtener acceso a los campos de texto EditText
            EditText editTextId = findViewById(R.id.editTextNuevaUbiId);
            EditText editTextNombre = findViewById(R.id.editTextNombreUbi);
            EditText editTextDescripcion = findViewById(R.id.editTextDescUbi);

            Ubicacion nuevo = new Ubicacion(
                    Integer.parseInt(editTextId.getText().toString()),
                    editTextNombre.getText().toString(),
                    editTextDescripcion.getText().toString()
            );

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("ubicaciones").document(String.valueOf(nuevo.getId()))
                    .set(nuevo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(EditarUbicacionActivity.this, "Ubicacion ingresada en el sistema", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditarUbicacionActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarUbi(View view){
        try{
            //TextView textViewIdUbi = (TextView) findViewById(R.id.textViewIdUbicacion);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            EditText editTextUbi = (EditText) findViewById(R.id.editTextNuevaUbiId);
            db.collection("ubicaciones" ).document(editTextUbi.getText().toString())
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>(){
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(EditarUbicacionActivity.this, "La ubicacion ha sido eliminada", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditarUbicacionActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error al eliminar la ubicacion");
                        }
                    });
        }  catch (Exception e) {
            Toast.makeText(EditarUbicacionActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}