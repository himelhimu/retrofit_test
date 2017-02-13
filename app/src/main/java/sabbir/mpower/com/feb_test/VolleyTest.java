package sabbir.mpower.com.feb_test;

import android.app.ProgressDialog;
import android.mpower.com.feb_test.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import butterknife.BindView;

public class VolleyTest extends AppCompatActivity {

    @BindView(R.id.button_get_image)
   Button btnGetImage;
    @BindView(R.id.button_get_string)
   Button btnGetString;
    private ProgressDialog progressDialog=null;

    private static final String IMAGE_REQUEST_URL = "http://androidtutorialpoint.com/api/lg_nexus_5x";
    private static final String STRING_REQUEST_URL = "https://androidtutorialpoint.com/api/volleyString";
    private static final String JSON_OBJECT_REQUEST_URL = "http://androidtutorialpoint.com/api/volleyJsonObject";
    private static final String JSON_ARRAY_REQUEST_URL = "http://androidtutorialpoint.com/api/volleyJsonArray";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test);
        progressDialog=new ProgressDialog(this);
    }

    public void onClicked(View view){
        switch (view.getId()){
            case R.id.button_get_string:
                volleyStringRequest(STRING_REQUEST_URL);
                break;
        }
    }

    public void volleyStringRequest(String url){
        String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        progressDialog.setMessage("Loading.....");
       // progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // progressDialog.dismiss();
                Log.i("In onResponse",response);
                Toast.makeText(VolleyTest.this,response,Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressDialog.dismiss();
                Log.i("In error onResponse",error.toString());
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
