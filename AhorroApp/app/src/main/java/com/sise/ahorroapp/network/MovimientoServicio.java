package com.sise.ahorroapp.network;

import com.sise.ahorroapp.model.Movimiento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MovimientoServicio {

    @GET("/api/movimientos")
    Call<List<Movimiento>> obtenerMovimientos();

    @POST("/api/movimientos")
    Call<Movimiento> guardarMovimiento(@Body Movimiento movimiento);

    @DELETE("/api/movimientos/{id}")
    Call<Void> eliminarMovimiento(@Path("id") long id);
}
