package com.example.mykbbi.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mykbbi.model.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question question);

    @Update
    void upate(Question question);

    @Delete
    void delete(Question question);

    @Query("DELETE FROM question_table")
    void deleteAllQuestions();

    @Query("SELECT * FROM question_table ORDER BY id DESC")
    LiveData<List<Question>> getAllQuestions();

    @Query("SELECT * FROM question_table WHERE id=:id")
    LiveData<Question> getQuestion(int id);
}
