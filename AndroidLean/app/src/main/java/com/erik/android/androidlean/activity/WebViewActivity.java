package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.TestObject;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.android.androidlean.view.TestWebView;
import com.erik.utilslibrary.ActivityManager;

@Route(path = ConstConfig.WEBVIEW_ACTIVITY)
public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    @Autowired(name = "name")
    public String name;

    private TestWebView webview;
    private long exitTime = 0;
    private static final String APP_CACHE_DIRNAME = "/webcache";
    private static final String URL = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        navigationBar();
        bindViews();
        loadWebView();
    }

    private void bindViews() {

    }

    private void navigationBar() {
        final NavigationBar navigationBar = findViewById(R.id.nav_bar);
        navigationBar.setTitleTextStr(name);
        navigationBar.setShowBackBtn(true);
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

    public void loadWebView() {
        webview = findViewById(R.id.wv_webview);
        final String url = "file:///android_asset/Test.html";
        //设置WebView属性,运行执行js脚本s
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //启动DOM storage PAI 功能
        settings.setDomStorageEnabled(true);
        //开启database storage API 功能
        settings.setDatabaseEnabled(true);
        //设置数据库缓存路径
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
        settings.setAppCachePath(cacheDirPath);
        settings.setAppCacheEnabled(true);
        webview.addJavascriptInterface(new TestObject(WebViewActivity.this), "obj");
        webview.evaluateJavascript("", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

            }
        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String utl) {
                //webview.loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //webview.loadUrl("url");
            }
        });
        //调用loadUrl方法为WebView加入链接
        webview.loadUrl(URL);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                webview.loadUrl("javascript:test()");
            }
        }, 3000);
    }

    //我们需要重写回退按钮的时间,当用户点击回退按钮：
    //1.webView.canGoBack()判断网页是否能后退,可以则goback()
    //2.如果不可以连续点击两次退出App,否则弹出提示Toast
    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            /*if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {*/
                super.onBackPressed();
            //}
        }
    }

    public class JSObject {
        @JavascriptInterface
        public void say(String words) {
            // todo
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    // 重写上下文菜单的创建方法
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflator = new MenuInflater(WebViewActivity.this);
        inflator.inflate(R.menu.webview_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // 上下文菜单被点击是触发该方法
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:
                webview.clearHistory();
                break;
            case R.id.two:
                webview.reload();
                break;
        }
        return true;
    }
}
