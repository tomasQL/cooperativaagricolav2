package com.example.cooperativaagricolav2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooperativaagricolav2.R;
import com.example.cooperativaagricolav2.models.Sensor;

import java.util.List;

public class RecyclerViewSensoresAdapter extends RecyclerView.Adapter<RecyclerViewSensoresAdapter.ViewHolder> {

    private List<Sensor> data;

    public RecyclerViewSensoresAdapter(List data) {this.data = data;}

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewIdSensor;
        private final TextView textViewNombreSensor;
        private final TextView textViewDescripcionSensor;
        private final TextView textViewMarcaIdeal;
        private final TextView textViewUbicacionSensor;

        public ViewHolder(@NonNull View view) {
            super(view);
            textViewIdSensor = (TextView) view.findViewById(R.id.textViewIdUbicacion);
            textViewNombreSensor = (TextView) view.findViewById(R.id.textViewNombreUbicacion);
            textViewUbicacionSensor = (TextView) view.findViewById(R.id.textViewUbicacion);
            textViewDescripcionSensor = (TextView) view.findViewById(R.id.textViewDescripcionUbicacion);
            textViewMarcaIdeal = (TextView) view.findViewById(R.id.textViewMarcaIdeal);
        }

        public TextView getTextViewIdSensor() {
            return textViewIdSensor;
        }

        public TextView getTextViewNombre() {
            return textViewNombreSensor;
        }

        public TextView getTextViewDescripcionSensor() {
            return textViewDescripcionSensor;
        }

        public TextView getTextViewMarcaIdeal() {
            return textViewMarcaIdeal;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextViewIdSensor().setText(String.valueOf(data.get(position).getId()));
        holder.getTextViewNombre().setText(data.get(position).getNombre());
        holder.getTextViewDescripcionSensor().setText(data.get(position).getDescripcion());
        holder.getTextViewMarcaIdeal().setText(data.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}