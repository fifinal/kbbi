package com.example.mykbbi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mykbbi.MainActivity;
import com.example.mykbbi.R;

public class HasilActivity extends AppCompatActivity {

    public static final String NILAI = "NILAI";

    TextView tvStatus, tvUngkapan,tvUngkapan2, tvNilai;
    Button btnMainLagi, btnMenuUtama;
    ImageView imgIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        tvStatus=findViewById(R.id.tv_status);
        tvUngkapan=findViewById(R.id.tv_ungkapan);
        tvUngkapan2=findViewById(R.id.tv_ungkapan2);
        tvNilai=findViewById(R.id.tv_nilai);

        btnMainLagi=findViewById(R.id.btn_main_lagi);
        btnMenuUtama=findViewById(R.id.btn_menu_utama);
        imgIcon=findViewById(R.id.img_icon);
        checkScore();

        btnMenuUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HasilActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnMainLagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HasilActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void checkScore(){
        SharedPreferences preferences=getApplicationContext().getSharedPreferences("Nilai",MODE_PRIVATE);
        int nilaiTertinggi=preferences.getInt("nilai",0);
        int nilai=getIntent().getIntExtra(NILAI,0);
        if(nilai>nilaiTertinggi){
            SharedPreferences.Editor editor=preferences.edit();
            editor.putInt("nilai",nilai);
            editor.apply();
            tvStatus.setText("Yeay, Kamu berhasil");
            tvUngkapan.setText("Jadi cinta deh");
            tvUngkapan2.setVisibility(View.GONE);
            imgIcon.setImageResource(R.drawable.love);
            tvNilai.setTextColor(Color.GREEN);
        }else{
            tvStatus.setText("GAME OVER");
            tvStatus.setTextSize(32);
            tvUngkapan.setText("CUPU BANGET");
            tvUngkapan2.setVisibility(View.VISIBLE);
            tvUngkapan2.setText("Aku jijik, jangan sentuh aku");
            imgIcon.setImageResource(R.drawable.cry);
            tvNilai.setTextColor(Color.RED);
        }
        tvNilai.setText(String.valueOf(nilai));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(HasilActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
