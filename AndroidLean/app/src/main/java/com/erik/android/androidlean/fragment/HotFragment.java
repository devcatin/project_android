package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.erik.android.androidlean.BR;
import com.erik.android.androidlean.activity.DetailActivity;
import com.erik.android.androidlean.adapter.ListAdapter;
import com.erik.android.androidlean.bean.NewBean;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.UserBean;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.databinding.FragmentNewlistBinding;
import com.erik.android.androidlean.network.BaseResponse;
import com.erik.android.androidlean.network.NetRequest;
import com.erik.android.androidlean.network.RequestMethod;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class HotFragment extends Fragment implements AdapterView.OnItemClickListener {

    private FragmentManager manager;
    private List<UserBean> beans = new ArrayList<>();
    private Context context;
    private ListView list_new;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x123) {
                updateData();
            }
            return false;
        }
    });

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    private void requestData() {
        NetRequest netRequest = NetRequest.getInstance(context);
        netRequest.get(RequestMethod.GET_USER_LIST, new NetRequest.RequestCallBack() {
            @Override
            public void success(Response response) throws IOException {
                int code = response.code();
                if (code == 200) {
                    String result = response.body().string();
                    Log.i("tag", result);
                    BaseResponse baseResponse = JSON.parseObject(result, BaseResponse.class);
                    String data = baseResponse.getData();
                    List<UserBean> list = JSON.parseArray(data, UserBean.class);
                    if (list.size() > 0) {
                        beans.clear();
                    }
                    for (int index = 0; index < list.size(); index++) {
                        UserBean userBean = list.get(index);
                        beans.add(userBean);
                    }
                    Message message = new Message();
                    message.what = 0x123;
                    handler.sendMessage(message);
                }
            }
            @Override
            public void failure(IOException e) {
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
        requestData();
    }

    private void updateData() {
        ListAdapter adapter = new ListAdapter(beans, getActivity(), BR.user);
        list_new.setAdapter(adapter);
        list_new.setOnItemClickListener(this);
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
        final Activity activity = getActivity();
        String user = JSON.toJSONString(bean);
        ARouter.getInstance().build(ConstConfig.DETAIL_ACTIVITY)
                .withString("name", bean.getUsername())
                .withString("user", user)
                .navigation(activity, 123, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {

                    }

                    @Override
                    public void onLost(Postcard postcard) {

                    }

                    @Override
                    public void onArrival(Postcard postcard) {

                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {

                    }
                });
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
