package com.example.mykbbi.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mykbbi.MainActivity;
import com.example.mykbbi.model.Question;
import com.example.mykbbi.model.QuestionViewModel;
import com.example.mykbbi.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    Button btnPilihan1,btnPilihan2, btnMulai;
    TextView tvDurasi,tvNomerSoal,tvPertanyaan;
    QuestionViewModel questionViewModel;
    List<Question> mQuestions;
    int duration, numberQuestion,nilai;
    Question currenQuestion;
    CountDownTimer timer;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btnPilihan1=findViewById(R.id.btn_pilihan1);
        btnPilihan2=findViewById(R.id.btn_pilihan2);
        btnMulai=findViewById(R.id.btn_mulai);
        tvDurasi=findViewById(R.id.tv_durasi);
        tvNomerSoal=findViewById(R.id.tv_nomer_soal);
        tvPertanyaan=findViewById(R.id.tv_petanyaan);

        sharedPreferences=getApplicationContext().getSharedPreferences("Nilai",MODE_PRIVATE);
        mQuestions=new ArrayList<>();  // menampung semua pertanyaan dr database
        numberQuestion=0;             // index pertama dr pertanyaan
        nilai=0;                     // nilai awal sebelum menjawab pertanyaan

        btnMulai.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnMulai.setEnabled(true);
            }
        },2000);

        //mengamil data pertanyaan dari database
        questionViewModel= ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> question) { //variabel question adalah isi dari database
                mQuestions=question;                // mQuestion (variabel global) diisi dengan question (variable local)
            }
        });
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.fadeout2);
        btnMulai.setAnimation(animation);
        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMulai.setVisibility(View.GONE);
                btnPilihan1.setVisibility(View.VISIBLE);
                btnPilihan2.setVisibility(View.VISIBLE);
                tvPertanyaan.setVisibility(View.VISIBLE);
                tvDurasi.setVisibility(View.VISIBLE);
                tvNomerSoal.setVisibility(View.VISIBLE);
                Collections.shuffle(mQuestions);   // acak data random
                setQuestion(0);                   // set pertanyaan pertama dengan parameter 0, (fungsi ada pd baris 79)

            }
        });
        btnPilihan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0); // user mengirim 0 jika btn atas yg diklik (fungsi ada pd baris 91)
            }
        });
        btnPilihan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1); // user mengirim 1 jika btn bawah yg diklik (fungsi ada pd baris 91)
            }
        });
    }

    public void setQuestion(int i){ // untuk menampilkan pertanyaan, animasi dan nilai
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.fadeout); //initialisasi animasi
        btnPilihan1.setText(mQuestions.get(i).getOption1());                           //
        btnPilihan1.startAnimation(animation);                                        //
        btnPilihan2.setText(mQuestions.get(i).getOption2());                         //
        btnPilihan2.startAnimation(animation);
        tvNomerSoal.setText(String.valueOf(i)+"/"+String.valueOf(mQuestions.size()));

        // mulai menghitung waktu (fungsi ada pd baris 112)
        startTime();
    }

    public void checkAnswer(int answer){// variable answer adalah didapat dr user antara 0 atau 1
        timer.cancel(); // jika dijawab maka timer akan berhenti
        timer=null;

        if(answer==mQuestions.get(numberQuestion).getAnswer()){// jika jawaban user sama dgn jawaban yg didatabase
            numberQuestion++;                                // nomer pertanyaan bertambah 1
            nilai+=(int)(1000/mQuestions.size());          // nilai ditambahkan ke nilai sebelumnya
            if(numberQuestion<=mQuestions.size()) setQuestion(numberQuestion);// tampilkan pertanyaan baru sesuai nomer pertanyaan (fungsi ada pd baris 79)
            else{
                Intent intent=new Intent(GameActivity.this,InfoKataActivity.class);
                intent.putExtra(InfoKataActivity.NILAI,nilai);
                startActivity(intent);
                finish();
            }
        }
        else{ // jika jawaban salah

            // pindah ke activity informasi kata
            Intent intent=new Intent(GameActivity.this,InfoKataActivity.class);
            intent.putExtra(InfoKataActivity.ID,mQuestions.get(numberQuestion).getId());// kirim id dr pertanyaan
            intent.putExtra(InfoKataActivity.NILAI,nilai);                             // kirim nilai yg didapat
            startActivity(intent);
            finish();
        }

    }

    public void startTime(){
        duration=sharedPreferences.getInt("level",30); // durasi tiap pertanyaan
        timer=new CountDownTimer(duration*100,100) { // tiap 1 detik akan mengerjakan fungsi onTick
            @Override
            public void onTick(long millisUntilFinished) { //
                if(duration>=0) tvDurasi.setText(String.valueOf(duration)); // tampilkan tiap detik durasi yg berkurang
                duration--; // durasi berkurang terus tiap detik
            }
            @Override
            public void onFinish() {    // saat waktu terpenuhi yaitu 3000 mili atau 3 detik

                // pindah ke activity hasil
                Intent intent=new Intent(GameActivity.this,HasilActivity.class);
                intent.putExtra(HasilActivity.NILAI,nilai); // kirim nilai yg didapat ke activity hasil
                startActivity(intent);
                finish();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
        timer=null;
        Intent intent=new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
