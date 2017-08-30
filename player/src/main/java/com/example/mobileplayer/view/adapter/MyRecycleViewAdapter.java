package com.example.mobileplayer.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileplayer.R;
import com.example.mobileplayer.model.MediaInfo;
import com.example.mobileplayer.utils.Utils;

import java.util.List;

/**
 * Created by pzm on 2017/3/27
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<MediaInfo> mMediaInfos;

    public MyRecycleViewAdapter() {
    }

    public MyRecycleViewAdapter(Context mContext, List<MediaInfo> mMediaInfos) {
        this.mContext = mContext;
        this.mMediaInfos = mMediaInfos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_video,null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        holder.ivIcon.setImageURI(Uri.parse(mMediaInfos.get(position).getImageUrl()));
        holder.tvName.setText(mMediaInfos.get(position).getName());
        holder.tvSide.setText(Formatter.formatFileSize(mContext,mMediaInfos.get(position).getSize()));
        holder.tvTime.setText(new Utils().stringForTime((int) mMediaInfos.get(position).getDuration()));
    }

    //重新设置数据
    public void setData(List<MediaInfo> mediaInfos){
        mMediaInfos = mediaInfos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMediaInfos.size();
    }

    /**
     * 添加监听
     * @return
     */
    public interface onItemClickListener{
        void onItemClick(View v,int position);
    }

    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivIcon;
        private TextView tvName;
        private TextView tvSide;
        private TextView tvTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvSide = (TextView) itemView.findViewById(R.id.tv_size);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);

            //添加监听
            if(mOnItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v,getLayoutPosition());
                    }
                });
            }

        }
    }
}
