package com.example.android.githubsearchwithsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.githubsearchwithsqlite.R;
import com.example.android.githubsearchwithsqlite.data.GitHubSearchContract;
import com.example.android.githubsearchwithsqlite.data.GitHubSearchDBHelper;
import com.example.android.githubsearchwithsqlite.utils.GitHubUtils;

import java.util.ArrayList;

public class SavedSearchResultsActivity extends AppCompatActivity implements GitHubSearchAdapter.OnSearchResultClickListener {

    private RecyclerView mSavedSearchResultsRV;
    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_search_results);

        GitHubSearchDBHelper dbHelper = new GitHubSearchDBHelper(this);
        mDB = dbHelper.getReadableDatabase();

        ArrayList<GitHubUtils.SearchResult> searchResultsList = getAllSavedSearchResults();
        GitHubSearchAdapter adapter = new GitHubSearchAdapter(this);
        adapter.updateSearchResults(searchResultsList);

        mSavedSearchResultsRV = (RecyclerView)findViewById(R.id.rv_saved_search_results);
        mSavedSearchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSavedSearchResultsRV.setHasFixedSize(true);
        mSavedSearchResultsRV.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        mDB.close();
        super.onDestroy();
    }

    @Override
    public void onSearchResultClick(GitHubUtils.SearchResult searchResult) {
        Intent intent = new Intent(this, SearchResultDetailActivity.class);
        intent.putExtra(GitHubUtils.SearchResult.EXTRA_SEARCH_RESULT, searchResult);
        startActivity(intent);
    }

    private ArrayList<GitHubUtils.SearchResult> getAllSavedSearchResults() {
        Cursor cursor = mDB.query(
                GitHubSearchContract.FavoriteRepos.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GitHubSearchContract.FavoriteRepos.COLUMN_TIMESTAMP + " DESC"
        );

        ArrayList<GitHubUtils.SearchResult> searchResultsList = new ArrayList<>();
        while (cursor.moveToNext()) {
            GitHubUtils.SearchResult searchResult = new GitHubUtils.SearchResult();
            searchResult.fullName = cursor.getString(
                    cursor.getColumnIndex(GitHubSearchContract.FavoriteRepos.COLUMN_FULL_NAME)
            );
            searchResult.description = cursor.getString(
                    cursor.getColumnIndex(GitHubSearchContract.FavoriteRepos.COLUMN_DESCRIPTION)
            );
            searchResult.htmlURL = cursor.getString(
                    cursor.getColumnIndex(GitHubSearchContract.FavoriteRepos.COLUMN_URL)
            );
            searchResult.stars = cursor.getInt(
                    cursor.getColumnIndex(GitHubSearchContract.FavoriteRepos.COLUMN_STARS)
            );
            searchResultsList.add(searchResult);
        }
        cursor.close();
        return searchResultsList;
    }
}
