package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;
    Button confirmButton, addCityButton, deleteCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        confirmButton = findViewById(R.id.confirm_button);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);

        // Initially hide the input and confirm button
        cityInput.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi", "Karachi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Handle clicking on a city to select it for deletion
        cityList.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            String selectedCity = dataList.get(position);
            cityInput.setText(selectedCity); // Populate the input field with the selected city name
        });

        // Show input field and confirm button when "ADD CITY" is clicked
        addCityButton.setOnClickListener(v -> {
            cityInput.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.VISIBLE);
            cityInput.setText(""); // Clear the input field for new entry
            cityInput.requestFocus();
        });

        // Add city when "CONFIRM" is clicked
        confirmButton.setOnClickListener(v -> {
            String cityName = cityInput.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                cityInput.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
                Toast.makeText(this, "City added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "City name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete city when "DELETE CITY" is clicked
        deleteCityButton.setOnClickListener(v -> {
            String cityName = cityInput.getText().toString().trim();
            if (!cityName.isEmpty() && dataList.contains(cityName)) {
                dataList.remove(cityName);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText(""); // Clear input field
                Toast.makeText(this, "City deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "City not found or not selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}