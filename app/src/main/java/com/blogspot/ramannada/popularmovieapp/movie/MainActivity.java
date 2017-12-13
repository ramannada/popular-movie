package com.blogspot.ramannada.popularmovieapp.movie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.blogspot.ramannada.popularmovieapp.R;
import com.blogspot.ramannada.popularmovieapp.api.ApiMovie;
import com.blogspot.ramannada.popularmovieapp.model.Movie;
import com.blogspot.ramannada.popularmovieapp.model.MoviesResponse;
import com.blogspot.ramannada.popularmovieapp.user.UserActivity;
import com.blogspot.ramannada.popularmovieapp.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        android.widget.SearchView.OnQueryTextListener,
        RecyclerTouchListener.ClickListener,
        SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.swipe_rl_movie) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.recycler_view_movie) RecyclerView movieRecycler;

    MainAdapter adapter;
    int page = 1;
    int totalPage;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        movieRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MainAdapter(new ArrayList<Movie>(), movieRecycler, R.layout.box_movie);

        movieRecycler.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                MainAdapter.movies.add(null);
                adapter.notifyItemInserted(MainAdapter.movies.size() - 1);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainAdapter.movies.remove(MainAdapter.movies.size() - 1);
                        adapter.notifyItemRemoved(MainAdapter.movies.size());

                        requestMovies("popular",page += 1);

                    }
                }, 2000);

            }
        });
        movieRecycler.addOnItemTouchListener(
                new RecyclerTouchListener(this, movieRecycler, this)
        );




        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                    getViewMovie();
            }
        });

        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        android.widget.SearchView searchView =
                (android.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            item.getActionView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(MainActivity.this, UserActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent i = new Intent(MainActivity.this, MovieSearchActivity.class);
        startActivity(i);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onClick(View view, int position) {
        Intent i = new Intent(MainActivity.this,
                MovieDetailActivity.class);
        i.putExtra("id", adapter.movies.get(position).getId());
        startActivity(i);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public void onRefresh() {
        if(adapter.movies == null || adapter.movies.size() <= 0) {
            getViewMovie();
        }
    }

    public void requestMovies(String filter, int page) {
        retrofit2.Call<MoviesResponse> call = ApiMovie.getMovieService()
                .getListMovies(filter, page, ApiMovie.API_KEY);


        call.enqueue(new retrofit2.Callback<MoviesResponse>() {

            @Override
            public void onResponse(Call<MoviesResponse> call, retrofit2.Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    MoviesResponse result = response.body();

                    if (result != null) {
                        totalPage = result.getTotalPages();
                        List<Movie> res = result.getMovies();

                        Log.d("api response ", String.valueOf(res.size()));

                        adapter.addMultiItem(res);

                    } else {
                        Log.d("api response ", "null");
                    }

                } else {
                    Log.d("api response " ,response.message());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<MoviesResponse> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
    }

    public void getViewMovie() {
            requestMovies("popular", 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        page = 1;
    }
}
