package com.example.mykbbi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mykbbi.UI.GameActivity;
import com.example.mykbbi.UI.NilaiTertinggiActivity;
import com.example.mykbbi.UI.SettingActivity;
import com.example.mykbbi.UI.TentangActivity;
import com.example.mykbbi.model.Question;
import com.example.mykbbi.model.QuestionViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnMulai,btnNilaiTertinggi, btnTentang, btnLevel;
    QuestionViewModel questionViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMulai=findViewById(R.id.btn_mulai);
        btnNilaiTertinggi=findViewById(R.id.btn_nilai_tertinggi);
        btnTentang=findViewById(R.id.btn_tentang);
        btnLevel=findViewById(R.id.btn_level);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Nilai",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(!sharedPreferences.contains("level")&&!sharedPreferences.contains("nama_level")){
            editor.putInt("level",100);
            editor.putString("nama_level","dasar");
        }
        if(!sharedPreferences.contains("nilai")){
            editor.putInt("nilai",0);
        }
        editor.apply();
        String namaLevel=sharedPreferences.getString("nama_level","");

        btnLevel.setText("Level : "+namaLevel);
        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnNilaiTertinggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NilaiTertinggiActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TentangActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
