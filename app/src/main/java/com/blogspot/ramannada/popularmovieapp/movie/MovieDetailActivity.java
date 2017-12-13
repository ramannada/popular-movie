package com.blogspot.ramannada.popularmovieapp.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.ramannada.popularmovieapp.R;
import com.blogspot.ramannada.popularmovieapp.api.ApiMovie;
import com.blogspot.ramannada.popularmovieapp.model.MovieResponse;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {
    @BindView(R.id.iv_poster) ImageView ivPoster;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_genre) TextView tvGenre;
    @BindView(R.id.tv_release_date) TextView tvReleaseDate;
    @BindView(R.id.tv_duration) TextView tvDuration;
    @BindView(R.id.tv_imdb_score) TextView tvImdbScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        long id = getIntent().getLongExtra("id", -1);
        Toast.makeText(this, "id " + id, Toast.LENGTH_SHORT).show();
        requestMovie(id);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void requestMovie(long id) {
        retrofit2.Call<MovieResponse> call = ApiMovie.getMovieService()
                .getMovieDetail(id,ApiMovie.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()) {
                    MovieResponse movie = response.body();

                    if (movie != null) {
                        Glide.with(MovieDetailActivity.this)
                                .load(ApiMovie.BASE_IMAGE_URL + "w150/" + movie.getPosterPath())
                                .into(ivPoster);
                        tvTitle.setText(movie.getTitle());
                        tvReleaseDate.setText(movie.getReleaseDate());
                        tvImdbScore.setText(movie.getVoteAverage().toString());
                    } else {
                        Toast.makeText(MovieDetailActivity.this, "Gagal Dalam", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
