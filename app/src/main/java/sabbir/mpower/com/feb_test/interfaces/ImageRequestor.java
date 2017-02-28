package sabbir.mpower.com.feb_test.interfaces;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sabbir.mpower.com.feb_test.model.PhotoDetails;

/**
 * Created by Sabbir on 26,February,2017
 * mPower Social
 * Dhaka
 */
public class ImageRequestor {

    private Calendar mCalendar;
    private SimpleDateFormat mDateFormat;
    private ImageRequestorResponse mResponseListener;
    private Context mContext;
    private OkHttpClient mClient;
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod?";
    private static final String DATE_PARAMETER = "date=";
    private static final String API_KEY_PARAMETER = "&api_key=";
    private static final String MEDIA_TYPE_KEY = "media_type";
    private static final String MEDIA_TYPE_VIDEO_VALUE = "video";
    private static final String API_KEY="idv4ashBgAFNEVXITBLFKlwyKRPxLnUdeZ1q1yyY";
    private boolean mLoadingData=false;

    public boolean isLoadingData(){
        return mLoadingData;
    }

    public interface ImageRequestorResponse{
        void onReceivedNewPhoto(PhotoDetails photoDetails);
    }

    public ImageRequestor(Activity activity){
        mCalendar = Calendar.getInstance();
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mResponseListener = (ImageRequestorResponse) activity;
        mContext = activity.getApplicationContext();
        mClient = new OkHttpClient();
        mLoadingData = false;
    }

    public void getPhoto() throws IOException{
        String date=mDateFormat.format(mCalendar.getTime());
        String urlRequest = BASE_URL + DATE_PARAMETER + date + API_KEY_PARAMETER +API_KEY;
        Request request=new Request.Builder().url(urlRequest).build();
        mLoadingData=true;

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mLoadingData=false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    try {
                        mCalendar.add(Calendar.DAY_OF_YEAR,-1);
                        Gson gson=new Gson();
                        PhotoDetails photoDetails=gson.fromJson(response.body().charStream(),PhotoDetails.class);
                        mResponseListener.onReceivedNewPhoto(photoDetails);
                        mLoadingData=false;

                    }catch (Exception e){
                        mLoadingData=false;
                    }
            }
        });
    }



}
