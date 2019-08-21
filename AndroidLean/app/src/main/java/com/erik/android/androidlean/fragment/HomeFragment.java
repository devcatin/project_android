package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.erik.android.androidlean.BR;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.activity.BaseActivity;
import com.erik.android.androidlean.adapter.HomeAdapter;
import com.erik.android.androidlean.adapter.LinearItemDecoration;
import com.erik.android.androidlean.adapter.ListAdapter;
import com.erik.android.androidlean.bean.BannerBean;
import com.erik.android.androidlean.bean.UserBean;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.network.BaseResponse;
import com.erik.android.androidlean.network.NetRequest;
import com.erik.android.androidlean.network.RequestMethod;
import com.erik.android.androidlean.tool.GlideImageLoader;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;

public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private Banner banner;
    private List<String> banners = new ArrayList<>();
    private List<UserBean> beans = new ArrayList<>();
    private ListView list_new;
    private ListAdapter adapter;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        navigationBar(view);
        bindViews(view);
        loadBanner(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        requestListData();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void navigationBar(View view) {
        final NavigationBar navigationBar = view.findViewById(R.id.nav_bar);
        navigationBar.setShowBackBtn(false);
        navigationBar.setBtnOnClickListener(new NavigationBar.ButtonOnClickListener() {
            @Override
            public void onBackClick() {
                Activity activity = ActivityManager.getActivity().get();
                activity.finish();
            }
            @Override
            public void onRightClick() {
                registerForContextMenu(navigationBar.getBtn_right());
            }
        });
    }

    @Override
    public void messageEventPostThread(Message msg) {
        //设置图片集合
        banner.setImages(banners);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestData() {
        super.requestData();
        NetRequest netRequest = NetRequest.getInstance(getContext());
        netRequest.get(RequestMethod.GET_BANNER_LIST, new NetRequest.RequestCallBack() {
            @Override
            public void success(Response response) throws IOException {
                int code = response.code();
                if (code == 200) {
                    String result = response.body().string();
                    Log.i("tag", result);
                    BaseResponse baseResponse = JSON.parseObject(result, BaseResponse.class);
                    String data = baseResponse.getData();
                    List<BannerBean> list = JSON.parseArray(data, BannerBean.class);
                    if (list.size() > 0) {
                        banners.clear();
                    }
                    for (int index = 0; index < list.size(); index++) {
                        BannerBean bannerBean = list.get(index);
                        banners.add(bannerBean.getImgUrl());
                    }
                    Message message = new Message();
                    message.what = 0x123;
                    handler.sendMessage(message);
                }
            }
            @Override
            public void failure(IOException e) {
                Message message = new Message();
                message.what = 0x456;
                handler.sendMessage(message);
                e.printStackTrace();
            }
        });
    }

    public void requestListData() {
        NetRequest netRequest = NetRequest.getInstance(getContext());
        HashMap<String, String> param = new HashMap<>();
        param.put("pageIndex", String.valueOf(pageIndex));
        param.put("pageSize", String.valueOf(10));
        netRequest.get(RequestMethod.GET_USER_LIST, param, new NetRequest.RequestCallBack() {
            @Override
            public void success(Response response) throws IOException {
                int code = response.code();
                if (code == 200) {
                    String result = response.body().string();
                    Log.i("tag", result);
                    BaseResponse baseResponse = JSON.parseObject(result, BaseResponse.class);
                    String data = baseResponse.getData();
                    List<UserBean> list = JSON.parseArray(data, UserBean.class);
                    if (list.size() > 0 && !isup) {
                        beans.clear();
                    }
                    beans.addAll(list);
                    Message message = new Message();
                    message.what = 0x123;
                    handler.sendMessage(message);
                }
            }
            @Override
            public void failure(IOException e) {
                Message message = new Message();
                message.what = 0x456;
                handler.sendMessage(message);
                e.printStackTrace();
            }
        });
    }

    private void loadBanner(View view) {
        banner = view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }

    private void bindViews(View view) {
        recyclerView = view.findViewById(R.id.rv_recycler_view);
        list_new = view.findViewById(R.id.list_news);
        list_new.setOnItemClickListener(this);
        adapter = new ListAdapter(beans, getActivity(), BR.user);
        //list_new.setAdapter(adapter);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        homeAdapter = new HomeAdapter(getActivity(), beans);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.addItemDecoration(new LinearItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        homeAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                UserBean userBean = beans.get(position);
                enterDetailActivity(userBean);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                UserBean userBean = beans.get(position);
                enterDetailActivity(userBean);
            }
        });
    }

    @Override
    // 重写上下文菜单的创建方法
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflator = new MenuInflater(getContext());
        inflator.inflate(R.menu.menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // 上下文菜单被点击是触发该方法
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:
                new Thread() {
                    public void run() {

                    }
                }.start();
                break;
            case R.id.two:
                new Thread() {
                    public void run() {

                    }
                }.start();
                break;
            case R.id.three:

                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserBean bean = beans.get(position);
        enterDetailActivity(bean);
    }

    private void enterDetailActivity(UserBean bean) {
        String user = JSON.toJSONString(bean);
        ARouter.getInstance().build(ConstConfig.DETAIL_ACTIVITY)
                .withString("name", bean.getUsername())
                .withString("user", user)
                .navigation();
    }

}
