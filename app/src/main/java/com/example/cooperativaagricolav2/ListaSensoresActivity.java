package com.example.cooperativaagricolav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cooperativaagricolav2.adapters.RecyclerViewSensoresAdapter;
import com.example.cooperativaagricolav2.models.Sensor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListaSensoresActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Sensor> sensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista_sensores);

            recyclerView = (RecyclerView) findViewById(R.id.recyclerViewListaSensores);
            recyclerView.setLayoutManager(new LinearLayoutManager(
                    this, RecyclerView.VERTICAL, false));

            sensores = new ArrayList<>();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("sensores").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                        @Override
                        public void onComplete (@NonNull Task <QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                // Generar una lista de contactos con cada documento
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    sensores.add(doc.toObject(Sensor.class));
                                    //adapter.notifyDataSetChanged();
                                }
                                RecyclerViewSensoresAdapter adapter = new RecyclerViewSensoresAdapter(sensores);
                                recyclerView.setAdapter(adapter);
                            } else {
                                Toast.makeText(ListaSensoresActivity.this,
                                        "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception e)  {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}