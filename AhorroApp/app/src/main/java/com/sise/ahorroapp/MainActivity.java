package com.sise.ahorroapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sise.ahorroapp.activities.MovimientoActivity;

public class MainActivity extends AppCompatActivity {

    Button btnMovimientos, btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnMovimientos = findViewById(R.id.btnMovimientos);
        btnPerfil = findViewById(R.id.btnPerfil);

        btnMovimientos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MovimientoActivity.class);
            startActivity(intent);
        });
        btnPerfil.setOnClickListener(v -> {
            // Por ahora muestra un mensaje
            // Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            // startActivity(intent);
        });

    }


}