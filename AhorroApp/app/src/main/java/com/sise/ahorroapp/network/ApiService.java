package com.sise.ahorroapp.network;

// ApiService.java
import com.sise.ahorroapp.model.LoginRequest;
import com.sise.ahorroapp.model.Movimiento;
import com.sise.ahorroapp.model.Usuario;
import com.sise.ahorroapp.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("usuarios/login") // Cambia esto si tu endpoint real es diferente
    Call<UsuarioResponse> login(@Body LoginRequest loginRequest);
    @POST("usuarios/registro")
    Call<Usuario> registrarUsuario(@Body Usuario usuario);

    @POST("movimientos/guardar")
    Call<Movimiento> registrarMovimiento(@Body Movimiento movimiento);
}
