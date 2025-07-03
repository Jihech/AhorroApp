package com.sise.ahorroapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sise.ahorroapp.R;
import com.sise.ahorroapp.model.Movimiento;
import com.sise.ahorroapp.network.MovimientoServicio;
import com.sise.ahorroapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarMovimientoActivity extends AppCompatActivity {


    private MovimientoServicio movimientoService;

    EditText editTextMonto, editTextDescripcion, editTextFecha;
    Spinner spinnerTipo;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_movimiento);
        movimientoService = RetrofitClient.getInstance().create(MovimientoServicio.class); // ✅ Correcto


        editTextMonto = findViewById(R.id.etMonto);
        editTextDescripcion = findViewById(R.id.etDescripcion);
        //editTextFecha = findViewById(R.id.editTextFecha);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        btnRegistrar = findViewById(R.id.btnRegistrarMovimiento);

        // Cargar datos al Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipo_movimiento_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        // Acción del botón
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarMovimiento();
            }
        });
    }


    private void registrarMovimiento() {
        String tipo = spinnerTipo.getSelectedItem().toString();
        double monto = Double.parseDouble(editTextMonto.getText().toString());
        String descripcion = editTextDescripcion.getText().toString();
        //String fecha = editTextFecha.getText().toString();

        Movimiento nuevoMovimiento = new Movimiento(tipo, monto, descripcion);

        movimientoService.guardarMovimiento(nuevoMovimiento).enqueue(new Callback<Movimiento>() {
            @Override
            public void onResponse(Call<Movimiento> call, Response<Movimiento> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistrarMovimientoActivity.this, "Movimiento registrado correctamente", Toast.LENGTH_SHORT).show();
                    finish(); // Vuelve atrás
                } else {
                    Toast.makeText(RegistrarMovimientoActivity.this, "Error en la respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Movimiento> call, Throwable t) {
                Log.e("RETROFIT_ERROR", "Fallo al conectar: " + t.getMessage());
                t.printStackTrace(); // 👈 Ver traza completa en Logcat
                Toast.makeText(RegistrarMovimientoActivity.this, "Fallo: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
}
}