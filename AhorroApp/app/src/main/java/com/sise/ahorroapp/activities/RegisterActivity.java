package com.sise.ahorroapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sise.ahorroapp.R;
import com.sise.ahorroapp.model.Usuario;
import com.sise.ahorroapp.network.ApiService;
import com.sise.ahorroapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText editNombre, editCorreo, editClave;
    Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // 👈 Asegúrate de tener este layout

        editNombre = findViewById(R.id.editNombre);
        editCorreo = findViewById(R.id.editCorreo);
        editClave = findViewById(R.id.editClave);
        btnRegistrarse = findViewById(R.id.btnRegistrar);

        btnRegistrarse.setOnClickListener(v -> {
            String nombre = editNombre.getText().toString().trim();
            String correo = editCorreo.getText().toString().trim();
            String clave = editClave.getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Usuario nuevoUsuario = new Usuario(); // 👈 NO uses usuarioId aquí
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setClave(clave);
            nuevoUsuario.setActivo(true);

            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            apiService.registrarUsuario(nuevoUsuario).enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registrado con éxito", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Fallo: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

    }
}
