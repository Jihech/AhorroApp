package com.sise.ahorroapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sise.ahorroapp.R;
import com.sise.ahorroapp.model.Movimiento;

import java.util.List;

public class MovimientoAdapter extends RecyclerView.Adapter<MovimientoAdapter.MovimientoViewHolder> {

    private List<Movimiento> listaMovimientos;

    // 👉 Constructor que acepta la lista
    public MovimientoAdapter(List<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    // 👉 Método opcional para actualizar lista después (ya lo tenías)
    public void setListaMovimientos(List<Movimiento> movimientos) {
        this.listaMovimientos = movimientos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movimiento, parent, false);
        return new MovimientoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovimientoViewHolder holder, int position) {
        Movimiento movimiento = listaMovimientos.get(position);
        holder.textTipo.setText(movimiento.getTipo());
        holder.textMonto.setText("S/ " + movimiento.getMonto());
        holder.textDescripcion.setText(movimiento.getDescripcion());
        holder.textFecha.setText(movimiento.getFecha());
    }

    @Override
    public int getItemCount() {
        return listaMovimientos != null ? listaMovimientos.size() : 0;
    }

    public static class MovimientoViewHolder extends RecyclerView.ViewHolder {
        TextView textTipo, textMonto, textDescripcion, textFecha;

        public MovimientoViewHolder(@NonNull View itemView) {
            super(itemView);
            textTipo = itemView.findViewById(R.id.tvTipo);
            textMonto = itemView.findViewById(R.id.tvMonto);
            textDescripcion = itemView.findViewById(R.id.tvDescripcion);
            textFecha = itemView.findViewById(R.id.tvFecha);
        }
    }
}
