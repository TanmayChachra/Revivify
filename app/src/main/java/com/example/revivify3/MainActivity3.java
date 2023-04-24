// Image editing app
package com.example.revivify3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;

public class MainActivity3 extends AppCompatActivity {
    ImageButton finalize,cancel,brightness,saturation,contrast,warmth;
    private ImageFilterView display ;
    private Uri img1;
    private SeekBar seekBar;
    private int seekbar_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        display = findViewById(R.id.display);
        seekbar_number = 1 ;
        //brightness.

        //if arriving from home screen
        if (!MainActivity2.fromsecondpage) {
            ImagePicker.with(MainActivity3.this)
                    .crop()
                    .start();
        }
        else {
            MainActivity2.fromsecondpage = false;
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey("KEY")) {
                Uri result= Uri.parse(extras.getString("KEY"));
                display.setImageURI(result);
                display.setVisibility(View.VISIBLE);
            }
        }

        // tick button
        finalize = findViewById(R.id.imageButton7);
        finalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(MainActivity3.this , OutcomePage.class);
                display.setDrawingCacheEnabled(true);
                display.buildDrawingCache();
                display.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                Bitmap bitmap = display.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("Image",bs.toByteArray());
                startActivity(intent);
            }
        });

        //cross button
        cancel = findViewById(R.id.cancel_Button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //brightness button
        brightness = findViewById(R.id.brightness_Button);
        brightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brightness.setBackgroundColor(808080);
                seekbar_number = 1;
            }
        });


        //saturation button
        saturation = findViewById(R.id.saturation_Button);
        saturation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saturation.setBackgroundColor(808080);
                seekbar_number = 2;
            }
        });

        //contrast button
        contrast = findViewById(R.id.contrast_Button);
        contrast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contrast.setBackgroundColor(808080);
                seekbar_number = 3;
            }
        });

        //warmth button
        warmth = findViewById(R.id.warmth_Button);
        warmth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                warmth.setBackgroundColor(808080);
                seekbar_number = 4;
            }
        });


        //seekbar
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch(seekbar_number){
                    case 1:
                        display.setBrightness(progress/50.0f);
                        break;
                    case 2:
                        display.setSaturation(progress/100.0f);
                        break;
                    case 3:
                        display.setContrast(progress/100.0f);
                        break;
                    case 4:
                        display.setWarmth(progress/100.0f);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        img1 = data.getData();
        display = findViewById(R.id.display);
        display.setImageURI(img1);
        display.setVisibility(View.VISIBLE);
    }
}