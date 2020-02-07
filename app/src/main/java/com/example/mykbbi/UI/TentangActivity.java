package com.example.mykbbi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mykbbi.MainActivity;
import com.example.mykbbi.R;

public class TentangActivity extends AppCompatActivity {

    Button btnMenuUtama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        btnMenuUtama=findViewById(R.id.btn_menu_utama);


        btnMenuUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TentangActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(TentangActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
