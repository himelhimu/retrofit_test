package sabbir.mpower.com.feb_test.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.mpower.com.feb_test.R;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sabbir.mpower.com.feb_test.NasaMainActivity;
import sabbir.mpower.com.feb_test.PhotoDetailsActivity;
import sabbir.mpower.com.feb_test.model.PhotoDetails;

/**
 * Created by sabbir on 2/25/17.
 */

public class NasaPhotoDetailsAdapter extends RecyclerView.Adapter<NasaPhotoDetailsAdapter.MyViewHolder>{

    private JSONObject jsonObject;
    private Context mContext=null;
    private ArrayList<PhotoDetails> photoList;
    private PhotoDetails photoDetails;
   public NasaPhotoDetailsAdapter(Context context,ArrayList<PhotoDetails> list){
        this.mContext=context;
        this.photoList=list;
    }

    public NasaPhotoDetailsAdapter(NasaMainActivity nasaMainActivity, JSONObject jsonObject) {
        this.jsonObject=jsonObject;
        Log.i("received json",jsonObject.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_item_card_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
                bindPhoto(photoList.get(position));
       /*//Picasso.with(mContext).load(photoList.get(position).getUrl()).into(holder.imageView);
        try {
            holder.tvDate.setText(jsonObject.getString("date"));
            String url=jsonObject.getString("url");
            Picasso.with(mContext).load(url).into(holder.imageView);
            holder.tvDetails.setText(jsonObject.getString("explanation"));
            holder.tvTittle.setText(jsonObject.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

       // holder.tvDetails.setText(jsonObject.getString(""));*/
        Picasso.with(mContext).load(photoList.get(position).getUrl()).into(holder.imageView);
        holder.tvDate.setText(photoList.get(position).getDate());
        holder.tvDetails.setText(photoList.get(position).getExplanation());
      //  holder.tvTittle.setText(photoList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void bindPhoto(PhotoDetails photoDetails){
        this.photoDetails=photoDetails;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //@BindView(R.id.image_view)
        ImageView imageView;
        //@BindView(R.id.date)
        TextView tvDate;
        //@BindView(R.id.tv_details)
        TextView tvDetails,tvTittle;

        public MyViewHolder(View v){
        super(v);
           // ButterKnife.bind((Activity) mContext);
            imageView=(ImageView) v.findViewById(R.id.image_view);
            tvDate=(TextView) v.findViewById(R.id.date);
            tvDetails=(TextView) v.findViewById(R.id.tv_details);
            tvTittle=(TextView) v.findViewById(R.id.photo_tittle);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context=v.getContext();
                    Intent intent=new Intent(context, PhotoDetailsActivity.class);
                    intent.putExtra("PHOTO",photoDetails);
                    context.startActivity(intent);
                }
            });

        }


    }
}
