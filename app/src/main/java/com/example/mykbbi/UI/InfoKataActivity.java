package com.example.mykbbi.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mykbbi.MainActivity;
import com.example.mykbbi.model.Question;
import com.example.mykbbi.model.QuestionViewModel;
import com.example.mykbbi.R;

public class InfoKataActivity extends AppCompatActivity {

    public static final String NILAI = "NILAI";
    public static final String ID = "ID";
    Button btnOke;
    TextView tvNilai, tvKataBaku, tvInfoKata;
    int nilai,id;
    QuestionViewModel questionViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_kata);
        tvNilai=findViewById(R.id.tv_nilai);
        tvInfoKata=findViewById(R.id.tv_info_kata);
        tvKataBaku=findViewById(R.id.tv_kata_baku);
        btnOke=findViewById(R.id.btn_oke);

        nilai=getIntent().getIntExtra(NILAI,0);
        id=getIntent().getIntExtra(ID,0);

        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InfoKataActivity.this, HasilActivity.class);
                intent.putExtra(HasilActivity.NILAI,nilai);
                startActivity(intent);
            }
        });

        questionViewModel= ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.getQuestion(id).observe(this, new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                tvInfoKata.setText(question.getInfo());
                if ((question.getAnswer() == 0)) {
                    tvKataBaku.setText(question.getOption1());
                } else {
                    tvKataBaku.setText(question.getOption2());
                }
                tvNilai.setText(String.valueOf(nilai));
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(InfoKataActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
