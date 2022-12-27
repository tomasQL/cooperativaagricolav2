package com.example.cooperativaagricolav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
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

    // Inicializar variables
    Button buttonLocation;
    TextView textViewUbicacionActual;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ubicacion);


        // asignar variables
        buttonLocation = findViewById(R.id.buttonLocation);
        textViewUbicacionActual = findViewById(R.id.textViewUbicacionActual);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(EditarUbicacionActivity.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // Cuando el permiso es garantizado
                    getLocation();
                } else {
                    // Cuando el permiso es denegado
                    ActivityCompat.requestPermissions(EditarUbicacionActivity.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                // Iniciar Ubicacion
                Location location = task.getResult();
                if (location != null) {
                    try {
                        // Iniciar geoCoder
                        Geocoder geocoder = new Geocoder(EditarUbicacionActivity.this,
                                Locale.getDefault());

                        // Iniciar lista de ubicacion
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        textViewUbicacionActual.setText((addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
            Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}