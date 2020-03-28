package com.example.bookdonation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;
    private String phoneNumber;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick( int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ImageAdapter(Context context, List<Upload> uploads){
        mContext = context;
        mUploads =uploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_veiws, parent,false);
        return new ImageViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
         Upload uploadCurrent = mUploads.get(position);
         holder.nameAdapter.setText(uploadCurrent.getName());
         Picasso.with(mContext).load(uploadCurrent.getImageurl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageAdapter);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView nameAdapter;
        public ImageView imageAdapter;
        public Button buttonAdapter;

        public ImageViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            nameAdapter = itemView.findViewById(R.id.cardview_name);
            imageAdapter = itemView.findViewById(R.id.cardview_image);
            buttonAdapter = itemView.findViewById(R.id.cardview_button);
            buttonAdapter.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                            listener.onItemClick(position);
                    }
                }
            });
        }
    }
}