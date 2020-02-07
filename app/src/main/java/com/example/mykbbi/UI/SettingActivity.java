package com.example.mykbbi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.mykbbi.MainActivity;
import com.example.mykbbi.R;

public class SettingActivity extends AppCompatActivity {

    RadioButton rbDasar,rbmenengah,rbtinggi,rbLanjut;
    Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        rbDasar=findViewById(R.id.rb_lvl_dasar);
        rbmenengah=findViewById(R.id.rb_lvl_menengah);
        rbtinggi=findViewById(R.id.rb_lvl_tinggi);
        rbLanjut=findViewById(R.id.rb_lvl_lanjut);
        btnSimpan=findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanLevel();
                Intent intent=new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public  void simpanLevel(){
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Nilai",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        if(rbDasar.isChecked()){
            editor.putInt("level",100);
            editor.putString("nama_level","dasar");
        }
        if(rbmenengah.isChecked()){
            editor.putInt("level",50);
            editor.putString("nama_level","menengah");
        }
        if(rbtinggi.isChecked()){
            editor.putInt("level",30);
            editor.putString("nama_level","tinggi");
        }
        if(rbLanjut.isChecked()){
            editor.putInt("level",20);
            editor.putString("nama_level","expert");
        }
        editor.apply();
    }
}
