package com.erik.android.androidlean.activity;

import android.graphics.Bitmap;
import android.os.Handler;
import android.renderscript.Script;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.TestObject;
import com.erik.android.androidlean.view.TestWebView;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private TestWebView webview;
    private long exitTime = 0;

    private static final String APP_CACHE_DIRNAME = "/webcache";
    private static final String URL = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Button cleanBtn = findViewById(R.id.btn_clean);
        cleanBtn.setOnClickListener(this);
        Button refreshBtn = findViewById(R.id.btn_refresh);
        refreshBtn.setOnClickListener(this);

        this.loadWebView();
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
                webview.loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                webview.loadUrl("url");
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
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
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
        switch (view.getId()) {
            case R.id.btn_clean:
                webview.clearCache(true);
                break;
            case R.id.btn_refresh:
                webview.reload();
                break;
        }
    }
}
