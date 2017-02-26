package sabbir.mpower.com.feb_test;

import android.mpower.com.feb_test.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sabbir.mpower.com.feb_test.model.PhotoDetails;

public class PhotoDetailsActivity extends AppCompatActivity {
    @BindView(R.id.photoImageView)
    ImageView imageView;
    @BindView(R.id.photoDescription)
    TextView textView;

    private PhotoDetails mSelectedPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        ButterKnife.bind(this);

        mSelectedPhoto=(PhotoDetails) getIntent().getSerializableExtra("PHOTO");
        Picasso.with(this).load(mSelectedPhoto.getUrl()).into(imageView);

        textView.setText(mSelectedPhoto.getExplanation());


    }
}
