package it.sal.disco.unimib.filmreviewer.ui.fragment.bookmarks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.adapter.MoviesRecyclerViewAdapterLocal;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.ui.activity.EditActivity;
import it.sal.disco.unimib.filmreviewer.ui.fragment.collection.CollectionViewModel;
import it.sal.disco.unimib.filmreviewer.utils.Constants;


public class BookmarksFragment extends Fragment {

    private List<Movie> moviesList;
    private MoviesRecyclerViewAdapterLocal RecyclerViewAdapter;
    private BookmarksViewModel mBookmarksViewModel;
    private Observer<MoviesResponse> observer;

    public BookmarksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.moviesList = new ArrayList<>();
        mBookmarksViewModel = new ViewModelProvider(requireActivity()).get(BookmarksViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View this_view = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        //ProgressBar
        ProgressBar pgrbar = this_view.findViewById(R.id.bookmarks_progressBar);
        pgrbar.setVisibility(View.VISIBLE);

        //RecyclerView
        RecyclerView mRecyclerView = this_view.findViewById(R.id.bookmarks_recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        RecyclerViewAdapter = new MoviesRecyclerViewAdapterLocal(this.moviesList, new MoviesRecyclerViewAdapterLocal.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                //DEBUG print
                Log.d(
                        "BookmarksFragment",
                        "CLICKED RecycleView element " + movie.toString());

                //Set current movie as movie to be edited
                Constants.selectedMovie = movie;

                //launch intent EditActivity
                Intent intentI = new Intent(getContext(), EditActivity.class);
                startActivity(intentI);
            }
        });
        mRecyclerView.setAdapter(RecyclerViewAdapter);

        //Observer for when View model change state
        observer = moviesResponse -> {
            pgrbar.setVisibility(View.INVISIBLE);
            if (moviesResponse.getMoviesList() == null) {
                Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        R.string.list_null,
                        Snackbar.LENGTH_LONG)
                        .show();
            }else{
                if (moviesResponse.getMoviesList() != null) {
                    moviesList.clear();
                    for(int i=0; i<moviesResponse.getMoviesList().size(); i++){
                        moviesList.add(moviesResponse.getMoviesList().get(i));
                    }
                    RecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };

        //Launch
        mBookmarksViewModel.getLocalMoviesFav()
                .observe(getViewLifecycleOwner(), observer);

        return this_view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBookmarksViewModel.getLocalMoviesFav()
                .observe(getViewLifecycleOwner(), observer);
    }
}