package com.example.agendaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendaapp.models.Cita;

import java.util.List;

public class CitasDiaAdapter extends RecyclerView.Adapter<CitasDiaAdapter.ViewHolder> {

    List<Cita> listaDia;

    public CitasDiaAdapter(List<Cita> listaDia) {
        this.listaDia = listaDia;
    }

    @NonNull
    @Override
    public CitasDiaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_citas_dia, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CitasDiaAdapter.ViewHolder holder, int position) {
        Cita cita = listaDia.get(position);

        holder.tvNombre.setText(cita.nomCliente);
        holder.tvHora.setText(cita.horaCita);
        holder.tvTelefono.setText(cita.telCliente);
        holder.tvMotivo.setText(cita.asuntoCita);

    }

    @Override
    public int getItemCount() {
        return listaDia.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre, tvHora, tvTelefono, tvMotivo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvHora = itemView.findViewById(R.id.tvHora);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvMotivo = itemView.findViewById(R.id.tvMotivo);
        }
    }
}
