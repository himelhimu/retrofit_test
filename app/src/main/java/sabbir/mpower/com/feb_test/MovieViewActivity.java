package sabbir.mpower.com.feb_test;

import android.mpower.com.feb_test.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;

public class MovieViewActivity extends AppCompatActivity {
    @BindView(R.id.recyecle_v_movie_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);
    }
}
