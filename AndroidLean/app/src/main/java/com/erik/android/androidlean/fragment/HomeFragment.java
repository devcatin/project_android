package com.erik.android.androidlean.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
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
import com.erik.android.androidlean.adapter.HomeEntityAdapter;
import com.erik.android.androidlean.adapter.LinearItemDecoration;
import com.erik.android.androidlean.adapter.ListAdapter;
import com.erik.android.androidlean.adapter.SectionedSpanSizeLookup;
import com.erik.android.androidlean.bean.BannerBean;
import com.erik.android.androidlean.bean.ClassBean;
import com.erik.android.androidlean.bean.ComBean;
import com.erik.android.androidlean.bean.HomeBean;
import com.erik.android.androidlean.bean.HotelEntity;
import com.erik.android.androidlean.bean.LiveBean;
import com.erik.android.androidlean.bean.UserBean;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.network.BaseResponse;
import com.erik.android.androidlean.network.NetRequest;
import com.erik.android.androidlean.network.RequestMethod;
import com.erik.android.androidlean.tool.GlideImageLoader;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;
import com.erik.utilslibrary.UtilsTools;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class HomeFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private HomeEntityAdapter homeEntityAdapter;
    private List<ComBean> datas = new ArrayList<>();

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
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void navigationBar(View view) {
//        final NavigationBar navigationBar = view.findViewById(R.id.nav_bar);
//        navigationBar.setShowBackBtn(false);
//        navigationBar.setBtnOnClickListener(new NavigationBar.ButtonOnClickListener() {
//            @Override
//            public void onBackClick() {
//                Activity activity = ActivityManager.getActivity().get();
//                activity.finish();
//            }
//            @Override
//            public void onRightClick() {
//                registerForContextMenu(navigationBar.getBtn_right());
//            }
//        });
    }

    @Override
    public void messageEventPostThread(Message msg) {
        homeEntityAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestData() {
        super.requestData();
        final NetRequest netRequest = NetRequest.getInstance(getContext());
        netRequest.get(RequestMethod.GET_HOME_DATA, new NetRequest.RequestCallBack() {
            @Override
            public void success(Response response) throws IOException {
                int code = response.code();
                if (code == 200) {
                    String result = response.body().string();
                    Log.i("tag", result);
                    BaseResponse baseResponse = JSON.parseObject(result, BaseResponse.class);
                    String data = baseResponse.getData();
                    HomeBean homeBean = JSON.parseObject(data, HomeBean.class);
                    if (homeBean != null) {
                        datas.clear();
                        List<BannerBean> banners = homeBean.getBanners();
                        List<ClassBean> classes = homeBean.getClasses();
                        List<LiveBean> liveBeans = homeBean.getLives();

                        ComBean comBean = new ComBean();
                        comBean.setType("banner");
                        comBean.setBanners(banners);

                        ComBean comBean1 = new ComBean();
                        comBean1.setType("class");
                        comBean1.setClasses(classes);

                        ComBean comBean2 = new ComBean();
                        comBean2.setType("live");
                        comBean2.setLives(liveBeans);


                        datas.add(comBean);
                        datas.add(comBean1);
                        datas.add(comBean2);
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

    private void bindViews(View view) {
        recyclerView = view.findViewById(R.id.rv_recycler_view);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        initRecyclerView();
    }

    private void initRecyclerView() {
        homeEntityAdapter = new HomeEntityAdapter(getContext(), datas);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        layoutManager.setSpanSizeLookup(new SectionedSpanSizeLookup(homeEntityAdapter, layoutManager));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeEntityAdapter);
        homeEntityAdapter.setOnItemClickLitener(new HomeEntityAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int section, int position) {

            }
        });
    }

    public HotelEntity analysisJsonFile(Context context,String fileName){
        String content = UtilsTools.readJsonFile(context,fileName);
        HotelEntity entity = JSON.parseObject(content, HotelEntity.class);
        return  entity;
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

    private void enterDetailActivity(HotelEntity.TagsEntity.TagInfo bean) {
        UserBean userBean = new UserBean();
        userBean.setUsername("美女");
        userBean.setPassword("123456");
        String user = JSON.toJSONString(userBean);
        ARouter.getInstance().build(ConstConfig.DETAIL_ACTIVITY)
                .withString("name", bean.getTagName())
                .withString("user", user)
                .navigation();
    }

}
