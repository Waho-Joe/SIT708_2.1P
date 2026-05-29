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

public class fuel extends AppCompatActivity {

    Spinner sourceSpinner, destinationSpinner;
    EditText inputEditText;
    TextView resultTextView;
    Button convertButton;

    String[] fuel = {"mpg", "km/L", "Gallon", "Liter", "Nautical Mile", "Kilometer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fuel);
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fuel);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceSpinner.setAdapter(adapter);
        destinationSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertFuel());
    }

    private void convertFuel() {
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
            Toast.makeText(this, "Input value cannot be negative", Toast.LENGTH_SHORT).show();
            return;
        }

        String source = sourceSpinner.getSelectedItem().toString();
        String destination = destinationSpinner.getSelectedItem().toString();

        if(source.equals(destination)){
            Toast.makeText(this,"Source is same to Destination", Toast.LENGTH_SHORT).show();
            return;
        }

        double result = convert(inputValue, source, destination);

        if (result == -1) {
            Toast.makeText(this, "These units cannot be converted", Toast.LENGTH_SHORT).show();
            return;
        }


        resultTextView.setText("Result: " + String.format("%.2f", result) + " " + destination);
    }
    private double convert(double value, String source, String destination) {

        if (source.equals(destination)) {
            return value;
        }

        if ("mpg".equals(source) && "km/L".equals(destination)) {
            return value * 0.425;
        } else if ("km/L".equals(source) && "mpg".equals(destination)) {
            return value / 0.425;
        }

        if ("Gallon".equals(source) && "Liter".equals(destination)) {
            return value * 3.785;
        } else if ("Liter".equals(source) && "Gallon".equals(destination)) {
            return value / 3.785;
        }

        if ("Nautical Mile".equals(source) && "Kilometer".equals(destination)) {
            return value * 1.852;
        } else if ("Kilometer".equals(source) && "Nautical Mile".equals(destination)) {
            return value / 1.852;
        }

        return -1;
    }
}