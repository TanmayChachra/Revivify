// Image editing app
package com.example.revivify3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class MainActivity3 extends AppCompatActivity {
    ImageButton finalize,cancel;
    ImageView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        finalize = (ImageButton) findViewById(R.id.finalize);
        cancel = (ImageButton) findViewById(R.id.cross);

        if (MainActivity2.fromsecondpage == false) {
            ImagePicker.with(MainActivity3.this)
                    .crop()
                    .start();
        }
        else {
            MainActivity2.fromsecondpage = false;
        }
        finalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this , OutcomePage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri img1 = data.getData();
        display = findViewById(R.id.imageView3);
        display.setImageURI(img1);

    }
}