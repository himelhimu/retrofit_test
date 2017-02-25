package sabbir.mpower.com.feb_test;

import android.mpower.com.feb_test.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sabbir.mpower.com.feb_test.adapters.MovieAdapter;
import sabbir.mpower.com.feb_test.listeners.MyOnClickListener;
import sabbir.mpower.com.feb_test.listeners.RecyclerOnTouch;
import sabbir.mpower.com.feb_test.model.Movie;

public class MovieViewActivity extends AppCompatActivity {
    @BindView(R.id.recyecle_v_movie_view)
    RecyclerView mRecyclerView;

    private MovieAdapter mMovieAdapter;

    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);
        ButterKnife.bind(this);
        movieList=new ArrayList<>();

       // mRecyclerView=(RecyclerView) findViewById(R.id.recyecle_v_movie_view);
        mMovieAdapter=new MovieAdapter(movieList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMovieAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerOnTouch(this, mRecyclerView, new MyOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie=movieList.get(position);
                Toast.makeText(getApplicationContext(),movie.getTitle(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Movie movie=movieList.get(position);
                Toast.makeText(getApplicationContext(),movie.getYear(),Toast.LENGTH_SHORT).show();
            }
        }));

        prepareMovieData();
    }
    private void prepareMovieData(){

        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        mMovieAdapter.notifyDataSetChanged();
    }
}
