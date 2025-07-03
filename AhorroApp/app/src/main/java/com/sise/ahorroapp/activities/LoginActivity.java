package com.sise.ahorroapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sise.ahorroapp.R;
import com.sise.ahorroapp.model.LoginRequest;
import com.sise.ahorroapp.model.UsuarioResponse;
import com.sise.ahorroapp.network.ApiService;
import com.sise.ahorroapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editCorreo, editClave;
    Button btnIniciarSesion, btnRegistrar;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editCorreo = findViewById(R.id.editCorreo);
        editClave = findViewById(R.id.editClave);
        btnIniciarSesion = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        apiService = RetrofitClient.getInstance().create(ApiService.class);

        btnIniciarSesion.setOnClickListener(v -> {
            String correo = editCorreo.getText().toString().trim();
            String clave = editClave.getText().toString().trim();

            if (correo.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            LoginRequest request = new LoginRequest(correo, clave);

            apiService.login(request).enqueue(new Callback<UsuarioResponse>() {
                @Override
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                    if (response.isSuccessful()) {
                        UsuarioResponse user = response.body();
                        Toast.makeText(LoginActivity.this, "Bienvenido, " + user.getNombre(), Toast.LENGTH_SHORT).show();
                        // Dentro del onResponse del login
                        SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putLong("id", user.getId());
                        editor.putString("nombre", user.getNombre()); // ✅ COMA AGREGADA
                        editor.putString("correo", user.getCorreo()); // ✅ COMA AGREGADA
                        editor.apply();
                        // Redirigir a movimientos
                        Intent intent = new Intent(LoginActivity.this, MovimientoActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

        btnRegistrar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
