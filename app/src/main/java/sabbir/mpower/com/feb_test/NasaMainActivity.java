package sabbir.mpower.com.feb_test;

import android.mpower.com.feb_test.R;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sabbir.mpower.com.feb_test.adapters.NasaPhotoDetailsAdapter;
import sabbir.mpower.com.feb_test.model.PhotoDetails;

public class NasaMainActivity extends AppCompatActivity {
    private ArrayList<PhotoDetails> photLisit;
    @BindView(R.id.rv_nasa)
    RecyclerView mNasaRecyclerView;

    public static final String IMAG_OF_THE_DAY_URL="https://api.nasa.gov/planetary/apod?api_key=idv4ashBgAFNEVXITBLFKlwyKRPxLnUdeZ1q1yyY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_main);
        ButterKnife.bind(this);
        photLisit=new ArrayList<>();
        GetNasaImageJson(IMAG_OF_THE_DAY_URL);

        RecyclerView.LayoutManager gridLayout=new LinearLayoutManager(this);
        mNasaRecyclerView.setLayoutManager(gridLayout);

        NasaPhotoDetailsAdapter nasaPhotoDetailsAdapter=new NasaPhotoDetailsAdapter(this,photLisit);
        mNasaRecyclerView.setAdapter(nasaPhotoDetailsAdapter);


    }

    private void GetNasaImageJson(String url){
        String REQUEST_TAG="";

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("JSON",response.toString());
                PhotoDetails photoDetails=null;
                Gson gson=new Gson();
                photoDetails=gson.fromJson(response.toString(),PhotoDetails.class);
                photLisit.add(photoDetails);
                Log.i("PhotoList",photLisit.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

  private class GetNasaImagetask extends AsyncTask<Void,Void,Void>{

      @Override
      protected Void doInBackground(Void... params) {
          return null;
      }
  }
}
