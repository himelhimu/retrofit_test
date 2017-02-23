package sabbir.mpower.com.feb_test;

import android.content.Intent;
import android.mpower.com.feb_test.R;

import sabbir.mpower.com.feb_test.interfaces.MyInterface;
import sabbir.mpower.com.feb_test.model.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.button3)
    Button btnMovie;

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
                Picasso.with(MainActivity.this).load(userData.getAvatarUrl()).into(imageView);
            }

            @Override
            public void failure(RetrofitError error) {
                String errorMsg=error.getMessage();
                Toast.makeText(MainActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void gotoStackOverFlow(View view){
        switch (view.getId()){
            case R.id.button:
                Intent intent=new Intent(this,StackOverFlow.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2=new Intent(this,VolleyTest.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent1=new Intent(this,MovieViewActivity.class);
                startActivity(intent1);
                break;
        }

    }
}
