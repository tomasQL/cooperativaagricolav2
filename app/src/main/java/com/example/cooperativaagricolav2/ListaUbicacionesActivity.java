package com.example.cooperativaagricolav2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooperativaagricolav2.adapters.RecyclerViewUbicacionesAdapter;
import com.example.cooperativaagricolav2.models.Ubicacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListaUbicacionesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Ubicacion> ubicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista_ubicaciones);

            recyclerView = (RecyclerView) findViewById(R.id.recyclerViewListaUbicaciones);
            recyclerView.setLayoutManager(new LinearLayoutManager(
                    this, RecyclerView.VERTICAL, false));

            ubicaciones = new ArrayList<>();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("ubicaciones").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                        @Override
                        public void onComplete (@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                // Generar una lista de contactos con cada documento
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    ubicaciones.add(doc.toObject(Ubicacion.class));
                                }
                                RecyclerViewUbicacionesAdapter adapter = new RecyclerViewUbicacionesAdapter(ubicaciones);
                                recyclerView.setAdapter(adapter);
                            } else {
                                Toast.makeText(ListaUbicacionesActivity.this,
                                        "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception e)  {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}