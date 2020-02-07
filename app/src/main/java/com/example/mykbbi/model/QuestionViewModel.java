package com.example.mykbbi.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mykbbi.model.Question;
import com.example.mykbbi.room.QuestionRepository;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository repository;
    private LiveData<List<Question>> allQuestion;
    private LiveData<Question> question;


    public QuestionViewModel(@NonNull Application application) {
        super(application);
        repository = new QuestionRepository(application);
        allQuestion = repository.getAllQuestions();
    }

    public void insert(Question question) {
        repository.insert(question);
    }

    public void update(Question question) {
        repository.update(question);
    }

    public void delete(Question question) {
        repository.delete(question);
    }

    public void deleteAllQuestions() {
        repository.deleteAllQuestions();
    }

    public LiveData<List<Question>> getAllQuestions() {
        return allQuestion;
    }
    public LiveData<Question> getQuestion(int id) {
        return repository.getQuestion(id);
    }

}
