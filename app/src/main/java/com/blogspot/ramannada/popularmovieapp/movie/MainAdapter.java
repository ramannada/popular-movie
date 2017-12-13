package com.blogspot.ramannada.popularmovieapp.movie;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blogspot.ramannada.popularmovieapp.R;
import com.blogspot.ramannada.popularmovieapp.api.ApiMovie;
import com.blogspot.ramannada.popularmovieapp.model.Movie;
import com.blogspot.ramannada.popularmovieapp.utils.RecyclerTouchListener;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ramannada on 11/25/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    static List<com.blogspot.ramannada.popularmovieapp.model.Movie> movies;
    private int itemLayout;
    private OnLoadMoreListener onLoadMoreListener;
    private int totalItem;
    private int lastVisibleItem;
    private int visibleThreshold = 20;
    private boolean loading;

    public MainAdapter(List<com.blogspot.ramannada.popularmovieapp.model.Movie> movies, RecyclerView recyclerView, int itemLayout) {
        this.movies = movies;
        this.itemLayout =  itemLayout;

        final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (layoutManager != null) {
                    totalItem = layoutManager.getItemCount();
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                    if (!loading && totalItem <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            loading = true;
                            onLoadMoreListener.onLoadMore();
                        }
                    }
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            viewHolder = new MainHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar, parent, false);
            viewHolder = new ProgressViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        com.blogspot.ramannada.popularmovieapp.model.Movie movie = movies.get(position);
        if (holder instanceof MainHolder) {
            ((MainHolder)holder).bindMovie(movie);
        } else {
            ((ProgressViewHolder)holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return movies.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public int getItemCount() {
        if (movies != null) {
            return movies.size();
        } else {
            return 0;
        }
    }

    public void addMultiItem(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
        setLoaded();
    }

    public void setLoaded() {
        loading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public static int getListItemSize() {
        return movies.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_poster) ImageView ivPoster;
        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.tv_year) TextView tvYear;

        private MainHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        private void bindMovie(com.blogspot.ramannada.popularmovieapp.model.Movie movie) {
            Glide.with(itemView)
                    .load(ApiMovie.BASE_IMAGE_URL + "w150/" + movie.getPosterPath())
                    .into(ivPoster);
            tvTitle.setText(movie.getTitle());
            tvYear.setText(movie.getReleaseDate());
        }
    }

    class ProgressViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.progress_bar) ProgressBar progressBar;


        public ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
