// API automated app
package com.example.revivify3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {
    ImageView result;
    public Uri img1;
    Button edit,button2;
    static boolean fromsecondpage;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        ImagePicker.with(MainActivity2.this)
                .cropSquare()
                .compress(4000)
                .start();

        edit = findViewById(R.id.tofeature2);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromsecondpage = true;
                feature2(view);
            }
        });

        button2 = findViewById(R.id.button2);
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

        img1 = data.getData();
        result = findViewById(R.id.result);
        result.setImageURI(img1);
        result.setVisibility(View.VISIBLE);

    }

    public void feature2(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this , MainActivity3.class);
        String stringUri = img1.toString();
        intent.putExtra("KEY",stringUri);
        startActivity(intent);
    }

    public void lastPage(View view){
        Intent intent_lastPage = new Intent(Intent.ACTION_VIEW);
        intent_lastPage.setClass(this , OutcomePage.class);
        String stringUri = img1.toString();
        intent_lastPage.putExtra("KEY",stringUri);
        startActivity(intent_lastPage);
    }

    void callAPI(String pngImage){
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("image", pngImage);
            jsonBody.put("prompt","enhanced high quality,4k rendered photo, Nikon DSLR like image, High quality, movie like photograph, color fixed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/images/edits")
                .post(requestBody)
                .header("Authorization", "Bearer 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")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getApplicationContext(),"Failed to generate image",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.i("Response",response.body().string());
            }
        });

    }
}