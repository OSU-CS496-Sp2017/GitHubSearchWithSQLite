package com.example.android.githubsearchwithsqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hessro on 5/30/17.
 */

public class GitHubSearchDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "githubSearch.db";
    private static final int DATABASE_VERSION = 1;

    public GitHubSearchDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAVORITE_REPOS_TABLE =
                "CREATE TABLE " + GitHubSearchContract.FavoriteRepos.TABLE_NAME + " (" +
                        GitHubSearchContract.FavoriteRepos._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        GitHubSearchContract.FavoriteRepos.COLUMN_FULL_NAME + " TEXT NOT NULL, " +
                        GitHubSearchContract.FavoriteRepos.COLUMN_DESCRIPTION + " TEXT, " +
                        GitHubSearchContract.FavoriteRepos.COLUMN_URL + " TEXT NOT NULL, " +
                        GitHubSearchContract.FavoriteRepos.COLUMN_STARS + " INTEGER DEFAULT 0, " +
                        GitHubSearchContract.FavoriteRepos.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                        ");";
        db.execSQL(SQL_CREATE_FAVORITE_REPOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GitHubSearchContract.FavoriteRepos.TABLE_NAME);
        onCreate(db);
    }
}
