package com.example.sit708_21p;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class temperature extends AppCompatActivity {

    Spinner sourceSpinner, destinationSpinner;
    EditText inputEditText;
    TextView resultTextView;
    Button convertButton;

    String[] temperatures  = {"Celsius", "Fahrenheit", "Kelvin"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temperature);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sourceSpinner = findViewById(R.id.sourcer);
        destinationSpinner = findViewById(R.id.destination);
        inputEditText = findViewById(R.id.input);
        resultTextView = findViewById(R.id.result);
        convertButton = findViewById(R.id.convert);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temperatures );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceSpinner.setAdapter(adapter);
        destinationSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertTemperature());
    }

    private void convertTemperature() {
        String inputText = inputEditText.getText().toString().trim();

        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue;

        try {
            inputValue = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        String source = sourceSpinner.getSelectedItem().toString();
        String destination = destinationSpinner.getSelectedItem().toString();

        if ("Kelvin".equals(source) && inputValue < 0) {
            Toast.makeText(this, "Kelvin cannot be negative", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("Celsius".equals(source) && inputValue < -273.15) {
            Toast.makeText(this, "Celsius cannot be below -273.15", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("Fahrenheit".equals(source) && inputValue < -459.67) {
            Toast.makeText(this, "Fahrenheit cannot be below -459.67", Toast.LENGTH_SHORT).show();
            return;
        }

        if(source.equals(destination)){
            Toast.makeText(this,"Source is same to Destination", Toast.LENGTH_SHORT).show();
            return;
        }

        double result = convert(inputValue, source, destination);

        resultTextView.setText("Result: " + String.format("%.2f", result) + " " + destination);
    }

    private double convert(double value, String source, String destination) {
        double valueInCelsius = 0;

        if ("Celsius".equals(source)) {
            valueInCelsius = value;
        } else if ("Fahrenheit".equals(source)) {
            valueInCelsius = (value - 32) / 1.8;
        } else if ("Kelvin".equals(source)) {
            valueInCelsius = value - 273.15;
        }

        if ("Celsius".equals(destination)) {
            return valueInCelsius;
        } else if ("Fahrenheit".equals(destination)) {
            return (valueInCelsius * 1.8) + 32;
        } else if ("Kelvin".equals(destination)) {
            return valueInCelsius + 273.15;
        }

        return valueInCelsius;
    }
}