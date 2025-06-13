package com.sise.ahorroapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sise.ahorroapp.model.Movimiento;
import com.sise.ahorroapp.network.MovimientoServicio;
import com.sise.ahorroapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimientoViewModel extends ViewModel {

    private MutableLiveData<List<Movimiento>> listaMovimientos;

    public LiveData<List<Movimiento>> obtenerMovimientos() {
        if (listaMovimientos == null) {
            listaMovimientos = new MutableLiveData<>();
            cargarMovimientos();
        }
        return listaMovimientos;
    }

    private void cargarMovimientos() {
        MovimientoServicio service = RetrofitClient.getInstance().create(MovimientoServicio.class);
        service.obtenerMovimientos().enqueue(new Callback<List<Movimiento>>() {
            @Override
            public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                if (response.isSuccessful()) {
                    listaMovimientos.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Movimiento>> call, Throwable t) {
                listaMovimientos.setValue(null);
            }
        });
    }
}
