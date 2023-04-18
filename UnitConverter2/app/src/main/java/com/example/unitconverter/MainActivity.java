package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView,textView2;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView2 = findViewById(R.id.textView2);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextNumberDecimal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "After clicking submit you will get Fahrenheit degree", Toast.LENGTH_SHORT).show();

                String str =  editText.getText().toString();
                double celsius = Double.parseDouble(str);
                double fahrenheit = ((celsius*9)/5)+32;
                textView.setText("Temperature in Fahrenheit is "+fahrenheit);
            }
        });
    }
}