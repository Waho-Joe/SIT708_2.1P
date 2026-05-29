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

public class currency extends AppCompatActivity {

    Spinner sourceSpinner, destinationSpinner;
    EditText inputEditText;
    TextView resultTextView;
    Button convertButton;

    String[] currencies = {"USD", "AUD", "EUR", "JPY", "GBP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_currency);
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceSpinner.setAdapter(adapter);
        destinationSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertCurrency());
    }

    private void convertCurrency() {
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

        if (inputValue < 0) {
            Toast.makeText(this, "Currency value cannot be negative", Toast.LENGTH_SHORT).show();
            return;
        }

        String sourceCurrency = sourceSpinner.getSelectedItem().toString();
        String destinationCurrency = destinationSpinner.getSelectedItem().toString();

        if(sourceCurrency.equals(destinationCurrency)){
            Toast.makeText(this,"Source Currency is same to Destination Currency", Toast.LENGTH_SHORT).show();
            return;
        }

        double result = convert(inputValue, sourceCurrency, destinationCurrency);

        resultTextView.setText("Result: " + String.format("%.2f", result) + " " + destinationCurrency);    }

    private double convert(double value, String source, String destination) {

        double valueInUsd = 0;

        if ("USD".equals(source)) {
            valueInUsd = value;
        } else if ("AUD".equals(source)) {
            valueInUsd = value / 1.55;
        } else if ("EUR".equals(source)) {
            valueInUsd = value / 0.92;
        } else if ("JPY".equals(source)) {
            valueInUsd = value / 148.50;
        } else if ("GBP".equals(source)) {
            valueInUsd = value / 0.78;
        }

        if ("USD".equals(destination)) {
            return valueInUsd;
        } else if ("AUD".equals(destination)) {
            return valueInUsd * 1.55;
        } else if ("EUR".equals(destination)) {
            return valueInUsd * 0.92;
        } else if ("JPY".equals(destination)) {
            return valueInUsd * 148.50;
        } else if ("GBP".equals(destination)) {
            return valueInUsd * 0.78;
        }

        return valueInUsd;
    }
}