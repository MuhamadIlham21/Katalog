package com.pmk.katalog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Image_picker extends AppCompatActivity {
    ImageView imgPick;
    Button btnCamera, btnGallery;
    int CODE_CAMERA = 1;
    int CODE_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        imgPick = findViewById(R.id.imgPick);
        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);

        btnCamera.setOnClickListener(view -> {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera, CODE_CAMERA);
        });

        btnGallery.setOnClickListener(view -> {
            Intent galerry = new Intent();
            galerry.setType("image/*");
            galerry.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(
                    Intent.createChooser(galerry, "Pilih gambar"),
                    CODE_GALLERY
            );
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_CAMERA){
            if(resultCode == RESULT_OK){
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                imgPick.setImageBitmap(bmp);
            }else{
                Toast.makeText(this, "User Cancel!", Toast.LENGTH_SHORT).show();
            }

        }else if(requestCode == CODE_GALLERY){
            try {
                Uri uri = data.getData();
                InputStream imgStream = getContentResolver().openInputStream(uri);
                Bitmap img = BitmapFactory.decodeStream(imgStream);
                imgPick.setImageBitmap(img);
            } catch (FileNotFoundException e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

    }


}