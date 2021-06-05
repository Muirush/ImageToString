package com.gallopingtech.imagetostring;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView image1, image2;
    private Button button;
    private TextView textView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.process);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        linearLayout = (LinearLayout) findViewById(R.id.Layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
               reload();


                }


        });
    }
    public void reload(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image);
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[]imageBytes = byteArrayOutputStream.toByteArray();
        String ImageString = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        //textView.setText(ImageString);
        Toast.makeText(this, ImageString, Toast.LENGTH_SHORT).show();
        // decoding
        imageBytes = Base64.decode(ImageString,Base64.DEFAULT);
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
        image2.setImageBitmap(bitmap1);
        linearLayout.setVisibility(View.INVISIBLE);

    }
    public  void  release(){
        if (textView.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "Nice", Toast.LENGTH_SHORT).show();
            linearLayout.setVisibility(View.INVISIBLE);
        }
    }
}