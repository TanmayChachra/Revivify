// API automated app
package com.example.revivify3;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.github.dhaval2404.imagepicker.ImagePicker;
public class MainActivity2 extends AppCompatActivity {
    ImageView result;
    Button edit,button2;
    static boolean fromsecondpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImagePicker.with(MainActivity2.this)
                .crop()
                .start();

        edit = findViewById(R.id.tofeature2);
        button2 = findViewById(R.id.button2);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromsecondpage = true;
                feature2(view);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastPage(view);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri img1 = data.getData();
        result = findViewById(R.id.result);
        result.setImageURI(img1);

    }

    public void feature2(View view){
        Intent intent = new Intent(this , MainActivity3.class);
        startActivity(intent);
    }

    public void lastPage(View view){
        Intent intent = new Intent(this , OutcomePage.class);
        startActivity(intent);
    }
}