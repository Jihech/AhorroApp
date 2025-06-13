package com.sise.ahorroapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sise.ahorroapp.R;
import com.sise.ahorroapp.adapter.MovimientoAdapter;
import com.sise.ahorroapp.model.Movimiento;
import com.sise.ahorroapp.network.MovimientoServicio;
import com.sise.ahorroapp.network.RetrofitClient;
import com.sise.ahorroapp.ui.RegistrarMovimientoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimientoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMovimientos;
    private Button btnAgregarMovimiento;
    private MovimientoAdapter movimientoAdapter;
    private List<Movimiento> listaMovimientos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        // Vincular vistas
        recyclerViewMovimientos = findViewById(R.id.recyclerViewMovimientos);
        btnAgregarMovimiento = findViewById(R.id.btnAgregarMovimiento);

        // Configurar RecyclerView
        recyclerViewMovimientos.setLayoutManager(new LinearLayoutManager(this));
        movimientoAdapter = new MovimientoAdapter(listaMovimientos);
        recyclerViewMovimientos.setAdapter(movimientoAdapter);

        // Botón para registrar movimiento
        btnAgregarMovimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovimientoActivity.this, RegistrarMovimientoActivity.class);
                startActivity(intent);
            }
        });

        // Llamar a la API
        obtenerMovimientosDesdeAPI();
    }

    private void obtenerMovimientosDesdeAPI() {
        MovimientoServicio movimientoServicio = RetrofitClient.getInstance().create(MovimientoServicio.class);
        Call<List<Movimiento>> call = movimientoServicio.obtenerMovimientos();

        call.enqueue(new Callback<List<Movimiento>>() {
            @Override
            public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaMovimientos.clear();
                    listaMovimientos.addAll(response.body());
                    movimientoAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MovimientoActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movimiento>> call, Throwable t) {
                Toast.makeText(MovimientoActivity.this, "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
