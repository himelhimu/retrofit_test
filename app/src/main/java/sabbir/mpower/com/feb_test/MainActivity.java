package sabbir.mpower.com.feb_test;

import android.mpower.com.feb_test.R;

import sabbir.mpower.com.feb_test.interfaces.MyInterface;
import sabbir.mpower.com.feb_test.model.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textView) TextView tvName;
    @BindView(R.id.textView2) TextView tvFo;
    @BindView(R.id.textView3) TextView tvA;
    @BindView(R.id.textView4) TextView tvB;
    @BindView(R.id.textView5) TextView tvC;
    @BindView(R.id.textView6) TextView tvD;
    @BindView(R.id.textView7) TextView tvE;
    @BindView(R.id.textView8) TextView tvF;

    private static final String URL="https://api.github.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint(URL).build();
        MyInterface myInterface=restAdapter.create(MyInterface.class);

        myInterface.getUser(new Callback<UserData>() {
            @Override
            public void success(UserData userData, Response response) {
                tvA.setText(userData.getName());
                tvB.setText(userData.getCompany());
                tvC.setText(userData.getCreatedAt());
            }

            @Override
            public void failure(RetrofitError error) {
                String errorMsg=error.getMessage();
                Toast.makeText(MainActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
