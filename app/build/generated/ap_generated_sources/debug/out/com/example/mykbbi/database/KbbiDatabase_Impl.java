package com.example.mykbbi.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.mykbbi.room.QuestionDao;
import com.example.mykbbi.room.QuestionDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class KbbiDatabase_Impl extends KbbiDatabase {
  private volatile QuestionDao _questionDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `question_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `option1` TEXT, `option2` TEXT, `info` TEXT, `answer` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"cee6d3e64943988681e96235ddc7afbe\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `question_table`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsQuestionTable = new HashMap<String, TableInfo.Column>(5);
        _columnsQuestionTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsQuestionTable.put("option1", new TableInfo.Column("option1", "TEXT", false, 0));
        _columnsQuestionTable.put("option2", new TableInfo.Column("option2", "TEXT", false, 0));
        _columnsQuestionTable.put("info", new TableInfo.Column("info", "TEXT", false, 0));
        _columnsQuestionTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQuestionTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesQuestionTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQuestionTable = new TableInfo("question_table", _columnsQuestionTable, _foreignKeysQuestionTable, _indicesQuestionTable);
        final TableInfo _existingQuestionTable = TableInfo.read(_db, "question_table");
        if (! _infoQuestionTable.equals(_existingQuestionTable)) {
          throw new IllegalStateException("Migration didn't properly handle question_table(com.example.mykbbi.model.Question).\n"
                  + " Expected:\n" + _infoQuestionTable + "\n"
                  + " Found:\n" + _existingQuestionTable);
        }
      }
    }, "cee6d3e64943988681e96235ddc7afbe", "565d78d0863eff46f8e12e0d5d3d155e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "question_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `question_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public QuestionDao questionDao() {
    if (_questionDao != null) {
      return _questionDao;
    } else {
      synchronized(this) {
        if(_questionDao == null) {
          _questionDao = new QuestionDao_Impl(this);
        }
        return _questionDao;
      }
    }
  }
}
