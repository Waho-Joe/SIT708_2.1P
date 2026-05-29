package com.example.sit708_21p;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button currencyButton, fuelButton, temperatureButton;

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

        currencyButton = findViewById(R.id.currency);
        fuelButton = findViewById(R.id.fuel);
        temperatureButton = findViewById(R.id.temperature);

        currencyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, currency.class);
            startActivity(intent);
        });

        fuelButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, fuel.class);
            startActivity(intent);
        });

        temperatureButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, temperature.class);
            startActivity(intent);
        });
    }

}