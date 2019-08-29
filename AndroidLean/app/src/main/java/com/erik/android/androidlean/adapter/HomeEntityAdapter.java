package com.erik.android.androidlean.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.BannerBean;
import com.erik.android.androidlean.bean.ClassBean;
import com.erik.android.androidlean.bean.ComBean;
import com.erik.android.androidlean.bean.HotelEntity;
import com.erik.android.androidlean.bean.LiveBean;
import com.erik.android.androidlean.bean.UserBean;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.utilslibrary.UtilsTools;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeEntityAdapter extends SectionedRecyclerViewAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ComBean> datas;
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }

    public HomeEntityAdapter(Context context, List<ComBean> list) {
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
        int viewType = getSectionItemViewType(section, 0);
        if (viewType == TYPE_BANNER) {
            return 1;
        } else if (viewType == TYPE_CLASS) {
            return 1;
        } else if (viewType == TYPE_LIVE) {
            return 1;
        } else {
            return 1;
        }
    }

    @Override
    protected boolean hasHeaderInSection(int section) {
        boolean hasHeader = true;
        int viewType = getSectionItemViewType(section, 0);
        if (viewType == TYPE_BANNER ||
                viewType == TYPE_CLASS ||
                viewType == TYPE_LIVE) {
            hasHeader = false;
        }
        return hasHeader;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        boolean hasFooter = true;
        int viewType = getSectionItemViewType(section, 0);
        if (viewType == TYPE_BANNER ||
                viewType == TYPE_CLASS ||
                viewType == TYPE_LIVE) {
            hasFooter = false;
        }
        return hasFooter;
    }

    @Override
    protected int getSectionItemViewType(int section, int position) {
        super.getSectionItemViewType(section, position);
        ComBean comBean = datas.get(section);
        String key = comBean.getType();
        if (key.equals("banner")) {
            return TYPE_BANNER;
        } else if (key.equals("class")) {
            return TYPE_CLASS;
        } else if (key.equals("live")) {
            return TYPE_LIVE;
        } else {
            return TYPE_ITEM;
        }
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
        int viewType = getSectionItemViewType(section, 0);
        if (viewType == TYPE_ITEM) {
            holder.titleView.setText("视频课程");
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(FooterHolder holder, int section) {
        int viewType = getSectionItemViewType(section, 0);
        if (viewType == TYPE_ITEM) {
            holder.titleView.setText("查看更多");
        }
    }

    @Override
    protected void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int section, final int position, int viewType) {
        ComBean comBean = datas.get(section);
        switch (viewType) {
            case TYPE_BANNER: {
                BannerHolder bannerHolder = (BannerHolder)holder;
                List<BannerBean> list = comBean.getBanners();
                final List<String> banners = new ArrayList<>();
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
                bannerHolder.banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        UserBean user = new UserBean();
                        user.setId(12);
                        user.setUsername("banner");
                        user.setPassword("123456");
                        user.setAge(20);
                        user.setSex("女");
                        user.setHeadimg(banners.get(position));
                        String userstr = JSON.toJSONString(user);
                        ARouter.getInstance()
                                .build(ConstConfig.DETAIL_ACTIVITY)
                                .withString("name", "banner")
                                .withString("user", userstr)
                                .navigation();
                    }
                });
                break;
            }
            case TYPE_CLASS: {
                final ClassHolder classHolder = (ClassHolder)holder;
                List<ClassBean> list = comBean.getClasses();
                GridViewAdapter adapter = new GridViewAdapter(mContext, list);
                classHolder.gridView.setAdapter(adapter);
                break;
            }
            case TYPE_LIVE: {
                final LiveHolder liveHolder = (LiveHolder)holder;
                List<LiveBean> liveBeans = comBean.getLives();
                final LiveBean liveBean = liveBeans.get(position);
                liveHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance()
                                .build(ConstConfig.DETAIL_ACTIVITY)
                                .withString("name", liveBean.getTitle())
                                .navigation();
                    }
                });
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
