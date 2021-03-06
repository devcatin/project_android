package com.erik.android.androidlean.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.UserBean;
import com.erik.android.androidlean.databinding.ListItemBinding;
import com.erik.android.androidlean.view.CustomRoundAngleImageView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<UserBean> mEntityList;

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener){
        this.mOnItemClickLitener = onItemClickLitener;
    }

    public HomeAdapter(Context context, List<UserBean> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        UserBean userBean = mEntityList.get(position);
        final HomeViewHolder homeViewHolder = (HomeViewHolder)holder;
        //Glide.with(mContext).load(userBean.getHeadimg()).into(header);
        homeViewHolder.bind(userBean);
        if (mOnItemClickLitener != null) {
            homeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = homeViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(homeViewHolder.itemView, pos);
                }
            });
            homeViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = homeViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(homeViewHolder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    @BindingAdapter({"android:src"})
    public static void setImageResource(CustomRoundAngleImageView imageView, String url) {
        Glide.with(imageView).load(url).into(imageView);
    }

    private class HomeViewHolder extends RecyclerView.ViewHolder {

        private ListItemBinding itemBinding;

        private HomeViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }

        private void bind(@NonNull UserBean userBean) {
            itemBinding.setUser(userBean);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

}
