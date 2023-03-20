package com.example.revivify3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button f1_button,f2_button,f3_button;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // COMMAND TO MAKE APP FULLSCREEN
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        f1_button = findViewById(R.id.Feature1);
        f2_button = findViewById(R.id.Feature2);
        f3_button = findViewById(R.id.Feature3);
        //fix photo button
        f1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feature1(view);
            }
        });

        // edit image button
        f2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feature2(view);
            }
        });
    }

    public void feature1(View view){
        Intent intent = new Intent(this , MainActivity2.class);
        startActivity(intent);
    }

    public void feature2(View view){
        Intent intent = new Intent(this , MainActivity3.class);
        startActivity(intent);
    }
//
//    public void feature3(View view){
//        Intent intent = new Intent(this , MainActivity4.class);
//        startActivity(intent);
//    }

}

