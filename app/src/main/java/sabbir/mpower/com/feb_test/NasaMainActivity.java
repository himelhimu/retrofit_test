package sabbir.mpower.com.feb_test;

import android.app.ProgressDialog;
import android.mpower.com.feb_test.R;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;

import butterknife.BindView;
import butterknife.ButterKnife;
import sabbir.mpower.com.feb_test.adapters.NasaPhotoDetailsAdapter;
import sabbir.mpower.com.feb_test.interfaces.ImageRequestor;
import sabbir.mpower.com.feb_test.model.PhotoDetails;

public class NasaMainActivity extends AppCompatActivity implements ImageRequestor.ImageRequestorResponse {
    private ArrayList<PhotoDetails> photLisit;
   // @BindView(R.id.rv_nasa)
    RecyclerView mNasaRecyclerView;
    ProgressDialog mProgressDialog=null;
    LinearLayoutManager mLinearLayoutManager;
    NasaPhotoDetailsAdapter nasaPhotoDetailsAdapter;
    Calendar mCalendar;
    public static final String IMAG_OF_THE_DAY_URL="https://api.nasa.gov/planetary/apod?api_key=idv4ashBgAFNEVXITBLFKlwyKRPxLnUdeZ1q1yyY";
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod?";
    private static final String DATE_PARAMETER = "date=";
    private static final String API_KEY_PARAMETER = "&api_key=";
    private static final String MEDIA_TYPE_KEY = "media_type";
    private static final String MEDIA_TYPE_VIDEO_VALUE = "video";
    private static final String API_KEY="idv4ashBgAFNEVXITBLFKlwyKRPxLnUdeZ1q1yyY";
    private SimpleDateFormat mDateFormat;
    private ImageRequestor mImageRequestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_main);
     //   ButterKnife.bind(this);
        photLisit=new ArrayList<>();
        mImageRequestor=new ImageRequestor(this);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage("Loading,Please Wait...");
        mProgressDialog.setCancelable(false);


        mNasaRecyclerView=(RecyclerView) findViewById(R.id.rv_nasa);
        mLinearLayoutManager=new LinearLayoutManager(getApplicationContext());
        mNasaRecyclerView.setLayoutManager(mLinearLayoutManager);
        mNasaRecyclerView.setHasFixedSize(true);

       //new GetNasaImagetask().execute();

        mNasaRecyclerView.addOnScrollListener(new MyRecyclerScrollListener());
        mCalendar=Calendar.getInstance();
        mDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        nasaPhotoDetailsAdapter=new NasaPhotoDetailsAdapter(this,photLisit);
        mNasaRecyclerView.setAdapter(nasaPhotoDetailsAdapter);
        //GetNasaImageJson();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (photLisit.size()==0){
            requestNewPhoto();
        }
    }

    private int getLastVisableItemPosition(){
        return mLinearLayoutManager.findLastVisibleItemPosition();
    }

    private void GetNasaImageJson(){
        String REQUEST_TAG="";
        final PhotoDetails photoDetails;
        mCalendar.add(Calendar.DAY_OF_YEAR, -1);
        String date = mDateFormat.format(mCalendar.getTime());

        String urlRequest = BASE_URL + DATE_PARAMETER + date + API_KEY_PARAMETER +
                API_KEY;



        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(urlRequest,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("JSON",response.toString());
                PhotoDetails ph;
                Gson gson=new Gson();
                ph=gson.fromJson(response.toString(),PhotoDetails.class);
                photLisit.add(ph);
               // photoDetails=ph;
                Log.i("PhotoList",photLisit.toString());

                try {
                    if (!(response.getString(MEDIA_TYPE_KEY).equals(MEDIA_TYPE_VIDEO_VALUE))){

                        GetNasaImageJson();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
        //return photoDetails;
    }

    @Override
    public void onReceivedNewPhoto(final PhotoDetails photoDetails) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                photLisit.add(photoDetails);
                nasaPhotoDetailsAdapter.notifyDataSetChanged();
            }
        });
    }

    private class GetNasaImagetask extends AsyncTask<Void,Void,JSONObject>{

      @Override
      protected void onPreExecute() {
          super.onPreExecute();

          mProgressDialog.show();
      }

      @Override
      protected JSONObject doInBackground(Void... params) {
          HttpURLConnection httpURLConnection;
          try {

              URL url=new URL(IMAG_OF_THE_DAY_URL);
              httpURLConnection=(HttpURLConnection) url.openConnection();
              InputStream inputStreamReader=httpURLConnection.getInputStream();
              BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStreamReader));

              StringBuilder stringBuilder=new StringBuilder();
              String responseJson="";
              while ((responseJson=bufferedReader.readLine())!=null){
                  stringBuilder.append(responseJson);
              }
              bufferedReader.close();

              String jsonString=stringBuilder.toString();
              JSONObject jsonObject=new JSONObject(jsonString);

              return jsonObject;
          } catch (MalformedURLException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          } catch (JSONException e) {
              e.printStackTrace();
          }

          return null;
      }

      @Override
      protected void onPostExecute(JSONObject jsonObject) {
          super.onPostExecute(jsonObject);
         mProgressDialog.dismiss();

          Gson gson=new Gson();
          PhotoDetails photoDetails;
          photoDetails=gson.fromJson(jsonObject.toString(),PhotoDetails.class);
          photLisit.add(photoDetails);


        /*  for (PhotoDetails p :photLisit){
              Log.i("value from list",""+photLisit.size()+""+p.getDate()+p.getExplanation()+p.getTitle()+p.getUrl());
          }*/

          nasaPhotoDetailsAdapter=new NasaPhotoDetailsAdapter(NasaMainActivity.this,photLisit);
          mNasaRecyclerView.setAdapter(nasaPhotoDetailsAdapter);
        //  nasaPhotoDetailsAdapter.notifyDataSetChanged();

      }
  }

    private class MyRecyclerScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            int toatlItemCount=recyclerView.getLayoutManager().getItemCount();
            if (!mImageRequestor.isLoadingData() && toatlItemCount==getLastVisableItemPosition()+1){
                requestNewPhoto();
            }
        }
    }

    private void requestNewPhoto() {
        try {
            mImageRequestor.getPhoto();
            nasaPhotoDetailsAdapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Toast.makeText(this,"Will have to do",Toast.LENGTH_LONG).show();
    }
}
