package sabbir.mpower.com.feb_test;

import android.app.ListActivity;
import android.mpower.com.feb_test.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import retrofit2.Callback;
import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sabbir.mpower.com.feb_test.interfaces.StackAPI;
import sabbir.mpower.com.feb_test.model.Question;

/**
 * Created by Sabbir on 09,February,2017
 * mPower Social
 * Dhaka
 */
public class StackOverFlow extends ListActivity implements Callback<StackOverFlowQuestions> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);

        ArrayAdapter<Question> adapter=new ArrayAdapter<Question>(this,android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1,
                new ArrayList<Question>());

        setListAdapter(adapter);
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);

        getData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        getData();

        return true;
    }

    private void getData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StackAPI stackAPI=retrofit.create(StackAPI.class);

        Call<StackOverFlowQuestions> call=stackAPI.getAllQuestions("android");
            call.enqueue(this);

    }


    @Override
    public void onResponse(Call<StackOverFlowQuestions> call, Response<StackOverFlowQuestions> response) {
        setProgressBarIndeterminateVisibility(false);
        ArrayAdapter<Question> adapter = (ArrayAdapter<Question>) getListAdapter();
        adapter.clear();
        adapter.addAll(response.body().items);
    }


    @Override
    public void onFailure(Call<StackOverFlowQuestions> call, Throwable t) {
        Toast.makeText(this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
