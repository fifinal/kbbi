package com.example.mykbbi.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mykbbi.database.KbbiDatabase;
import com.example.mykbbi.model.Question;
import com.example.mykbbi.room.QuestionDao;

import java.util.List;

public class QuestionRepository {
    private QuestionDao questionDao;
    private LiveData<List<Question>> allQuestions;
    private LiveData<Question> question;

    public QuestionRepository(Application application){
        KbbiDatabase database= KbbiDatabase.getInstance(application);
        questionDao = database.questionDao();
        allQuestions = questionDao.getAllQuestions();
//        question = questionDao.getQuestion();
    }

    public void insert(Question question){
        new InsertQuestionAsyncTask(questionDao).execute(question);
    }
    public void update(Question question){
        new UpdateQuestionAsyncTask(questionDao).execute(question);

    }
    public void delete(Question question){
        new DeleteQuestionAsyncTask(questionDao).execute(question);

    }
    public void deleteAllQuestions(){
        new DeleteAllQuestionAsyncTask(questionDao).execute();

    }

    public LiveData<List<Question>> getAllQuestions() {
        return allQuestions;
    }
    public LiveData<Question> getQuestion(int id) {
        return questionDao.getQuestion(id);
    }

    private static class InsertQuestionAsyncTask extends AsyncTask<Question,Void,Void>{
        private QuestionDao questionDao;

        private InsertQuestionAsyncTask(QuestionDao questionDao){
            this.questionDao = questionDao;
        }
        @Override
        protected Void doInBackground(Question... questions) {
             questionDao.insert(questions[0]);
             return null;
        }
    }
    private static class UpdateQuestionAsyncTask extends AsyncTask<Question,Void,Void>{
        private QuestionDao questionDao;

        private UpdateQuestionAsyncTask(QuestionDao questionDao){
            this.questionDao = questionDao;
        }
        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.upate(questions[0]);
            return null;
        }
    }
    private static class DeleteQuestionAsyncTask extends AsyncTask<Question,Void,Void>{
        private QuestionDao questionDao;

        private DeleteQuestionAsyncTask(QuestionDao questionDao){
            this.questionDao = questionDao;
        }
        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.delete(questions[0]);
            return null;
        }
    }
    private static class DeleteAllQuestionAsyncTask extends AsyncTask<Void,Void,Void>{
        private QuestionDao questionDao;

        private DeleteAllQuestionAsyncTask(QuestionDao questionDao){
            this.questionDao = questionDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            questionDao.deleteAllQuestions();
            return null;
        }
    }
}
