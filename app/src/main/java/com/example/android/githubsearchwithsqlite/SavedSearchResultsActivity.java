package com.example.android.githubsearchwithsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.githubsearchwithsqlite.R;
import com.example.android.githubsearchwithsqlite.utils.GitHubUtils;

public class SavedSearchResultsActivity extends AppCompatActivity implements GitHubSearchAdapter.OnSearchResultClickListener {

    private RecyclerView mSavedSearchResultsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_search_results);

        mSavedSearchResultsRV = (RecyclerView)findViewById(R.id.rv_saved_search_results);
        mSavedSearchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSavedSearchResultsRV.setHasFixedSize(true);
    }

    @Override
    public void onSearchResultClick(GitHubUtils.SearchResult searchResult) {
        Intent intent = new Intent(this, SearchResultDetailActivity.class);
        intent.putExtra(GitHubUtils.SearchResult.EXTRA_SEARCH_RESULT, searchResult);
        startActivity(intent);
    }
}
