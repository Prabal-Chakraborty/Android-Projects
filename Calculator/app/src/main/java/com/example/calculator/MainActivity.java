package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText n1,n2;
    Button button,button2,button3,button4;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        text = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

//        int no1 = Integer.parseInt(n1.getText().toString());
//        int no2 = Integer.parseInt(n2.getText().toString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double sum = Double.parseDouble(n1.getText().toString()) + Double.parseDouble(n2.getText().toString());
                text.setText("The sum is "+sum);
                Toast.makeText(MainActivity.this, "Summation Selected", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double sub = Double.parseDouble(n1.getText().toString()) - Double.parseDouble(n2.getText().toString());
                text.setText("The subtraction is "+sub);
                Toast.makeText(MainActivity.this, "Subtraction Selected", Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double multip = Double.parseDouble(n1.getText().toString()) * Double.parseDouble(n2.getText().toString());
                text.setText("The Multiplication is "+multip);
                Toast.makeText(MainActivity.this, "Multiplication Selected", Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double div = Double.parseDouble(n1.getText().toString()) / Double.parseDouble(n2.getText().toString());
                text.setText("The division is "+div);
                Toast.makeText(MainActivity.this, "Division Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}