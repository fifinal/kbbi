package com.example.mykbbi.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mykbbi.model.Question;
import com.example.mykbbi.room.QuestionDao;

@Database(entities={Question.class},version = 1)
public abstract class KbbiDatabase extends RoomDatabase {

    private static KbbiDatabase instance;

    public abstract QuestionDao questionDao();

    public static synchronized KbbiDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    KbbiDatabase.class,"kbbi_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private QuestionDao questionDao;

        private PopulateDbAsyncTask(KbbiDatabase db){
            questionDao =db.questionDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            questionDao.insert(new Question("abjad","abjat","",0));
            questionDao.insert(new Question("aktifitas","aktivitas","",1));
            questionDao.insert(new Question("amfibi","amphibi","",0));
            questionDao.insert(new Question("handal","andal","",1));
            questionDao.insert(new Question("analisis","analisa","",0));
            questionDao.insert(new Question("antre","antri","",0));
            questionDao.insert(new Question("apotek","apotik","",0));
            questionDao.insert(new Question("azaz","asas","",1));
            questionDao.insert(new Question("atlet","atlit","",0));
            questionDao.insert(new Question("atmosfer","atmosfir","",0));
            questionDao.insert(new Question("adzan","azan","",1));
            questionDao.insert(new Question("belum","belom","",0));
            questionDao.insert(new Question("bengep","bengap","",1));
            questionDao.insert(new Question("besok","esok","",0));
            questionDao.insert(new Question("biosfer","biosfir","",0));
            questionDao.insert(new Question("blanko","blangko","",1));
            questionDao.insert(new Question("cabai","cabe","",0));
            questionDao.insert(new Question("cendekiawan","cendikiawan","",0));
            questionDao.insert(new Question("daftar","daptar","",0));
            questionDao.insert(new Question("dekoratif","dekoratip","",0));
            questionDao.insert(new Question("dekret","dekrit","",0));
            questionDao.insert(new Question("detail","detil","",0));
            questionDao.insert(new Question("diagnosis","diagnosa","",0));
            questionDao.insert(new Question("duren","durian","",1));
            questionDao.insert(new Question("efektif","efektip","",0));
            questionDao.insert(new Question("efektifitas","efektivitas","",1));
            questionDao.insert(new Question("ekstra","extra","",0));
            questionDao.insert(new Question("elite","elit","",0));
            questionDao.insert(new Question("hembus","embus","",1));
            questionDao.insert(new Question("faksimile","faksimili","",0));
            questionDao.insert(new Question("februari","pebruari","",0));
            questionDao.insert(new Question("fondasi","pondasi","",0));
            questionDao.insert(new Question("formil","formal","",1));
            questionDao.insert(new Question("foto","photo","",0));
            questionDao.insert(new Question("frekuensi","frekwensi","",0));
            questionDao.insert(new Question("gisi","gizi","",1));
            questionDao.insert(new Question("gladi","geladi","",1));
            questionDao.insert(new Question("gubuk","gubug","",0));
            questionDao.insert(new Question("hadist","hadis","",1));
            questionDao.insert(new Question("hafal","hapal","",0));
            questionDao.insert(new Question("hakikat","hakekat","",0));
            questionDao.insert(new Question("hirarki","hierarki","",1));
            questionDao.insert(new Question("hipotesis","hipotesa","",0));
            questionDao.insert(new Question("ijazah","ijasah","",0));
            questionDao.insert(new Question("imaginasi","imajinasi","",1));
            questionDao.insert(new Question("imbau","himbau","",0));
            questionDao.insert(new Question("indera","indra","",1));
            questionDao.insert(new Question("insaf","insyaf","",0));
            questionDao.insert(new Question("isap","hisap","",0));
            questionDao.insert(new Question("isteri","istri","",1));
            questionDao.insert(new Question("izin","ijin","",0));
            questionDao.insert(new Question("jadwal","jadual","",0));
            questionDao.insert(new Question("jenazah","jenasah","",0));
            questionDao.insert(new Question("jendral","jenderal","",1));
            questionDao.insert(new Question("justru","justeru","",0));
            questionDao.insert(new Question("karena","karna","",0));
            questionDao.insert(new Question("karir","karier","",1));
            questionDao.insert(new Question("karisma","kharisma","",0));
            questionDao.insert(new Question("kategori","katagori","",0));
            questionDao.insert(new Question("komoditi","komoditas","",1));
            questionDao.insert(new Question("komplet","komplit","",0));
            questionDao.insert(new Question("kreatif","kreatip","",0));
            questionDao.insert(new Question("kualitas","kwalitas","",0));
            questionDao.insert(new Question("kuitansi","kwitansi","",0));
            questionDao.insert(new Question("lembab","lembap","",1));
            questionDao.insert(new Question("lubang","lobang","",0));
            questionDao.insert(new Question("makhluk","mahluk","",0));
            questionDao.insert(new Question("mesjid","masjid","",1));
            questionDao.insert(new Question("metode","metoda","",0));
            questionDao.insert(new Question("menyolok","mencolok","",1));
            questionDao.insert(new Question("miliar","milyar","",0));
            questionDao.insert(new Question("nampak","tampak","",1));
            questionDao.insert(new Question("napas","nafas","",0));
            questionDao.insert(new Question("nasehat","nasihat","",1));
            questionDao.insert(new Question("negatip","negatif","",1));
            questionDao.insert(new Question("negeri","negri","",0));
            questionDao.insert(new Question("nomer","nomor","",1));
            questionDao.insert(new Question("november","nopember","",0));
            questionDao.insert(new Question("obyek","objek","",1));
            questionDao.insert(new Question("objektif","obyektif","",0));
            questionDao.insert(new Question("faham","paham","",1));
            questionDao.insert(new Question("pikir","fikir","",0));
            questionDao.insert(new Question("praktek","praktik","",1));
            questionDao.insert(new Question("provinsi","propinsi","",0));
            questionDao.insert(new Question("rapot","rapor","",1));
            questionDao.insert(new Question("risiko","resiko","",0));
            questionDao.insert(new Question("sekretaris","sekertaris","",0));
            questionDao.insert(new Question("sistim","sistem","",1));
            questionDao.insert(new Question("standarisasi","standardisasi","",1));
            questionDao.insert(new Question("subjek","subyek","",0));
            questionDao.insert(new Question("tehnik","teknik","",1));
            questionDao.insert(new Question("teknologi","tehnologi","",0));
            questionDao.insert(new Question("jaman","zaman","",1));
            return null;
        }
    }

}