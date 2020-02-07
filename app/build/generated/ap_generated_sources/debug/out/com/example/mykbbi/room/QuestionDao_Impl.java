package com.example.mykbbi.room;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.mykbbi.model.Question;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class QuestionDao_Impl implements QuestionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfQuestion;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfQuestion;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfQuestion;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllQuestions;

  public QuestionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestion = new EntityInsertionAdapter<Question>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `question_table`(`id`,`option1`,`option2`,`info`,`answer`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Question value) {
        stmt.bindLong(1, value.getId());
        if (value.getOption1() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getOption1());
        }
        if (value.getOption2() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getOption2());
        }
        if (value.getInfo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getInfo());
        }
        stmt.bindLong(5, value.getAnswer());
      }
    };
    this.__deletionAdapterOfQuestion = new EntityDeletionOrUpdateAdapter<Question>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `question_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Question value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfQuestion = new EntityDeletionOrUpdateAdapter<Question>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `question_table` SET `id` = ?,`option1` = ?,`option2` = ?,`info` = ?,`answer` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Question value) {
        stmt.bindLong(1, value.getId());
        if (value.getOption1() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getOption1());
        }
        if (value.getOption2() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getOption2());
        }
        if (value.getInfo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getInfo());
        }
        stmt.bindLong(5, value.getAnswer());
        stmt.bindLong(6, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllQuestions = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM question_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(Question question) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfQuestion.insert(question);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Question question) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfQuestion.handle(question);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upate(Question question) {
    __db.beginTransaction();
    try {
      __updateAdapterOfQuestion.handle(question);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllQuestions() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllQuestions.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllQuestions.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Question>> getAllQuestions() {
    final String _sql = "SELECT * FROM question_table ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Question>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Question> compute() {
        if (_observer == null) {
          _observer = new Observer("question_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfOption1 = _cursor.getColumnIndexOrThrow("option1");
          final int _cursorIndexOfOption2 = _cursor.getColumnIndexOrThrow("option2");
          final int _cursorIndexOfInfo = _cursor.getColumnIndexOrThrow("info");
          final int _cursorIndexOfAnswer = _cursor.getColumnIndexOrThrow("answer");
          final List<Question> _result = new ArrayList<Question>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Question _item;
            _item = new Question();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpOption1;
            _tmpOption1 = _cursor.getString(_cursorIndexOfOption1);
            _item.setOption1(_tmpOption1);
            final String _tmpOption2;
            _tmpOption2 = _cursor.getString(_cursorIndexOfOption2);
            _item.setOption2(_tmpOption2);
            final String _tmpInfo;
            _tmpInfo = _cursor.getString(_cursorIndexOfInfo);
            _item.setInfo(_tmpInfo);
            final int _tmpAnswer;
            _tmpAnswer = _cursor.getInt(_cursorIndexOfAnswer);
            _item.setAnswer(_tmpAnswer);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<Question> getQuestion(int id) {
    final String _sql = "SELECT * FROM question_table WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<Question>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected Question compute() {
        if (_observer == null) {
          _observer = new Observer("question_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfOption1 = _cursor.getColumnIndexOrThrow("option1");
          final int _cursorIndexOfOption2 = _cursor.getColumnIndexOrThrow("option2");
          final int _cursorIndexOfInfo = _cursor.getColumnIndexOrThrow("info");
          final int _cursorIndexOfAnswer = _cursor.getColumnIndexOrThrow("answer");
          final Question _result;
          if(_cursor.moveToFirst()) {
            _result = new Question();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _result.setId(_tmpId);
            final String _tmpOption1;
            _tmpOption1 = _cursor.getString(_cursorIndexOfOption1);
            _result.setOption1(_tmpOption1);
            final String _tmpOption2;
            _tmpOption2 = _cursor.getString(_cursorIndexOfOption2);
            _result.setOption2(_tmpOption2);
            final String _tmpInfo;
            _tmpInfo = _cursor.getString(_cursorIndexOfInfo);
            _result.setInfo(_tmpInfo);
            final int _tmpAnswer;
            _tmpAnswer = _cursor.getInt(_cursorIndexOfAnswer);
            _result.setAnswer(_tmpAnswer);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
