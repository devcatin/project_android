package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.erik.android.androidlean.BR;
import com.erik.android.androidlean.adapter.ListAdapter;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.UserBean;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.network.BaseResponse;
import com.erik.android.androidlean.network.NetRequest;
import com.erik.android.androidlean.network.RequestMethod;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.scwang.smartrefresh.layout.header.TwoLevelHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;

public class HotFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public FragmentManager manager;
    private List<UserBean> beans = new ArrayList<>();
    private ListView list_new;
    private ListAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDoubleRefresh = true;
    }

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    @Override
    public void messageEventPostThread(Message msg) {
        updateData();
    }

    public void requestData() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newlist, container, false);
        navigationBar(view);
        bindViews(view);
        return view;
    }

    private void bindViews(View view) {
        list_new = view.findViewById(R.id.list_news);
        list_new.setOnItemClickListener(this);
        adapter = new ListAdapter(getContext(), beans);
        list_new.setAdapter(adapter);
        refreshLayout = view.findViewById(R.id.refreshLayout);
    }

    private void updateData() {
        adapter.notifyDataSetChanged();
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

            }
        });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 123:
                break;
            case 456:
                break;
                default:
                    break;
        }
    }

}
