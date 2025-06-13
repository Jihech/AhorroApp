package com.sise.ahorroapp.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sise.ahorroapp.model.Movimiento;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimientoRepositorio {

    private MovimientoServicio movimientoService;

    public MovimientoRepositorio() {
        movimientoService = RetrofitClient.getInstance().create(MovimientoServicio.class);
    }

    public LiveData<List<Movimiento>> obtenerMovimientos() {
        MutableLiveData<List<Movimiento>> data = new MutableLiveData<>();

        movimientoService.obtenerMovimientos().enqueue(new Callback<List<Movimiento>>() {
            @Override
            public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Movimiento>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public void guardarMovimiento(Movimiento movimiento, Callback<Movimiento> callback) {
        movimientoService.guardarMovimiento(movimiento).enqueue(callback);
    }

    public void eliminarMovimiento(long id, Callback<Void> callback) {
        movimientoService.eliminarMovimiento(id).enqueue(callback);
    }
}
