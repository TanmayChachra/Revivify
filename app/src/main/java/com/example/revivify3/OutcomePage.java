package com.example.revivify3;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.OutputStream;
import java.util.Objects;

public class OutcomePage extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    ImageView image;
    Button saveToGalleryBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome_page);


        // importing image from another activity
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("KEY")) {
            Uri result= Uri.parse(extras.getString("KEY"));
            image = findViewById(R.id.imageView2);
            image.setImageURI(result);
            image.setVisibility(View.VISIBLE);
        }
        else if(extras != null && extras.containsKey("Image")){
            //Toast.makeText(this, "Yaha tak chal rha hai", Toast.LENGTH_SHORT).show();
            Bitmap result = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("Image"),0,getIntent()
                            .getByteArrayExtra("Image").length);
            image = findViewById(R.id.imageView2);
            image.setImageBitmap(result);
            image.setVisibility(View.VISIBLE);
        }

        // Saving image to Gallery
        saveToGalleryBtn = findViewById(R.id.savetogallery);
        saveToGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(OutcomePage.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    saveImage();
                }
                else{
                    ActivityCompat.requestPermissions(OutcomePage.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
                }
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveImage();
            }else{
                Toast.makeText(this, "Please provide required persmission", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void saveImage(){
        Uri images;
        ContentResolver contentResolver = getContentResolver();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            images = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        }else{
            images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME,System.currentTimeMillis() + ".jpg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE,"images/*");
        Uri uri = contentResolver.insert(images,contentValues);

        try{
            BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();

            OutputStream outputStream = contentResolver.openOutputStream(Objects.requireNonNull(uri));
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            Objects.requireNonNull(outputStream);

            Toast.makeText(OutcomePage.this,"Image saved successfully",Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            Toast.makeText(this, "Images not saved", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}

