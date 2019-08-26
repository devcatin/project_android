package com.erik.android.androidlean.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.BannerBean;
import com.erik.android.androidlean.bean.ClassBean;
import com.erik.android.androidlean.bean.HotelEntity;
import com.erik.android.androidlean.bean.LiveBean;
import com.erik.utilslibrary.UtilsTools;

import java.util.ArrayList;
import java.util.List;

public class HomeEntityAdapter extends SectionedRecyclerViewAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<List> datas;
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }

    public HomeEntityAdapter(Context context, List<List> list) {
        this.mContext = context;
        this.datas = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    protected int getSectionCount() {
        return datas.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        if (section == 0 || section == 1) {
            return 1;
        }
        List<Object> list = datas.get(section);
        return list.size();
    }

    @Override
    protected boolean hasHeaderInSection(int section) {
        if (section == 0 || section == 1 || section == 2) {
            return false;
        }
        return true;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        if (section == 0 || section == 1 || section == 2) {
            return false;
        }
        return true;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.home_header_item, parent, false));
    }

    @Override
    protected FooterHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return new FooterHolder(mInflater.inflate(R.layout.home_footer_item, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerHolder(mInflater.inflate(R.layout.home_banner_item, parent, false));
        } else if (viewType == TYPE_CLASS) {
            return new ClassHolder(mInflater.inflate(R.layout.home_class_item, parent, false));
        } else if (viewType == TYPE_LIVE) {
            return new LiveHolder(mInflater.inflate(R.layout.home_live_item, parent, false));
        } else {
            return new ContentHolder(mInflater.inflate(R.layout.home_content_item, parent, false));
        }
    }

    @Override
    protected void onBindSectionHeaderViewHolder(HeaderHolder holder, int section) {
        holder.titleView.setText("Title");
    }

    @Override
    protected void onBindSectionFooterViewHolder(FooterHolder holder, int section) {
        holder.titleView.setText("查看更多");
    }

    @Override
    protected void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int section, final int position) {
        switch (section) {
            case 0: {
                BannerHolder bannerHolder = (BannerHolder)holder;
                List<BannerBean> list = datas.get(section);
                List<String> banners = new ArrayList<>();
                for (BannerBean bean: list) {
                    banners.add(bean.getImgUrl());
                }
                if (!bannerHolder.isLoaded) {
                    //设置图片集合
                    bannerHolder.banner.setImages(banners);
                    //banner设置方法全部调用完毕时最后调用
                    bannerHolder.banner.start();
                    bannerHolder.isLoaded = true;
                }
                break;
            }
            case 1: {
                final ClassHolder classHolder = (ClassHolder)holder;
                List<ClassBean> list = datas.get(section);
                GridViewAdapter adapter = new GridViewAdapter(mContext, list);
                classHolder.gridView.setAdapter(adapter);
                break;
            }
            case 2: {
                final LiveHolder liveHolder = (LiveHolder)holder;
                List<LiveBean> liveBeans = datas.get(section);
                LiveBean liveBean = liveBeans.get(position);
                liveHolder.bind(liveBean);
                break;
            }
            default: {
                break;
            }
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int section, int position);
    }

}
