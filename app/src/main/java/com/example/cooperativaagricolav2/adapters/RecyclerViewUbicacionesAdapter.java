package com.example.cooperativaagricolav2.adapters;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooperativaagricolav2.ListaUbicacionesActivity;
import com.example.cooperativaagricolav2.R;
import com.example.cooperativaagricolav2.models.Ubicacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RecyclerViewUbicacionesAdapter extends RecyclerView.Adapter<RecyclerViewUbicacionesAdapter.ViewHolder> {

    private List<Ubicacion> data;

    public RecyclerViewUbicacionesAdapter(List data) {
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewIdUbicacion;
        private final TextView textViewNombreUbicacion;
        private final TextView textViewDescripcionUbicacion;

        public ViewHolder(@NonNull View view) {
            super(view);
            textViewIdUbicacion = (TextView) view.findViewById(R.id.textViewIdUbicacion);
            textViewNombreUbicacion = (TextView) view.findViewById(R.id.textViewNombreUbicacion);
            textViewDescripcionUbicacion = (TextView) view.findViewById(R.id.textViewDescripcionUbicacion);
        }

        public TextView getTextViewIdUbicacion() {return textViewIdUbicacion;}

        public TextView getTextViewNombreUbicacion() {
            return textViewNombreUbicacion;
        }

        public TextView getTextViewDescripcionUbicacion() {
            return textViewDescripcionUbicacion;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_2, null, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextViewIdUbicacion().setText(String.valueOf(data.get(position).getId()));
        holder.getTextViewNombreUbicacion().setText(data.get(position).getNombre());
        holder.getTextViewDescripcionUbicacion().setText(data.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}