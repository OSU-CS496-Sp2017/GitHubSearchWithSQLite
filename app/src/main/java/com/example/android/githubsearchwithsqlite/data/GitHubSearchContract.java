package com.example.android.githubsearchwithsqlite.data;

import android.provider.BaseColumns;

/**
 * Created by hessro on 5/30/17.
 */

public class GitHubSearchContract {
    private GitHubSearchContract() {}

    public static class FavoriteRepos implements BaseColumns {
        public static final String TABLE_NAME = "favoriteRepos";
        public static final String COLUMN_FULL_NAME = "fullName";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_STARS = "stars";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
