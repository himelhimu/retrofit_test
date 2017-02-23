package sabbir.mpower.com.feb_test.adapters;

import android.mpower.com.feb_test.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sabbir.mpower.com.feb_test.model.Movie;

/**
 * Created by Sabbir on 23,February,2017
 * mPower Social
 * Dhaka
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private List<Movie> movieList;

    public MovieAdapter(List<Movie> moviesList){
        this.movieList=moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            genre = (TextView) itemView.findViewById(R.id.genre);
            year = (TextView) itemView.findViewById(R.id.year);
        }
    }
}
