package com.example.mykbbi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mykbbi.MainActivity;
import com.example.mykbbi.R;

public class NilaiTertinggiActivity extends AppCompatActivity {

    Button btnMenuUtama;
    TextView tvNilaiTertinggi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_tertinggi);
        tvNilaiTertinggi=findViewById(R.id.tv_nilai_tertinggi);
        btnMenuUtama=findViewById(R.id.btn_menu_utama);

        SharedPreferences preferences=getApplicationContext().getSharedPreferences("Nilai",MODE_PRIVATE);
        int nilaiTertinggi=preferences.getInt("nilai",0);
        tvNilaiTertinggi.setText(String.valueOf(nilaiTertinggi));
        tvNilaiTertinggi.setTextColor(Color.GREEN);
        btnMenuUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NilaiTertinggiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(NilaiTertinggiActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
