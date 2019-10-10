package com.erik.android.androidlean.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.erik.android.androidlean.activity.BaseActivity;
import com.erik.utilslibrary.ActivityManager;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class BigImageView extends View implements GestureDetector.OnGestureListener, View.OnTouchListener {

    private Context mContext;
    //需要显示的区域
    private Rect mRect;
    //由于需要复用，所有需要option
    private BitmapFactory.Options mOption;
    //长图需要通过手势滑动来操作
    private GestureDetector mGestureDetector;
    //滑动帮助类
    private Scroller mScroller;
    //图片的宽度
    private int mImageWidth;
    //图片的高度
    private int mImageHeight;
    //控件的宽度
    private int mViewWidth;
    //控件的高度
    private int mViewHeight;
    //图片缩放因子
    private float mScale;
    //区域解码器
    private BitmapRegionDecoder mDecode;
    //需要展示的图片，是被复用的
    private Bitmap mBitmap;
    private InputStream mInputStream;

    final static int BUFFER_SIZE = 4096;

    private HttpURLConnection connection;

    private android.os.Handler handler = new android.os.Handler(new android.os.Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x123) {
                if (null != mInputStream) {
                    setImage(mInputStream);
                }
            }
            return false;
        }
    });

    public BigImageView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BigImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRect = new Rect();
        mOption = new BitmapFactory.Options();
        mGestureDetector = new GestureDetector(context, this);
        setOnTouchListener(this);
        mScroller = new Scroller(context);
        mContext = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //将onTouch事件交给手势处理
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        //如果移动还没有停止，强制停止
        if (!mScroller.isFinished()) {
            mScroller.forceFinished(true);
        }
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
        //上下移动的时候，需要改变显示的区域 改mRect
        mRect.offset(0, (int) distanceY);
        //处理上下边界问题
        if (mRect.top < 0) {
            mRect.top = 0;
            mRect.bottom = (int) (mViewHeight / mScale);
        }
        if (mRect.bottom > mImageHeight) {
            mRect.top = mImageHeight - (int) (mViewHeight / mScale);
            mRect.bottom = mImageHeight;
        }
        invalidate();
        return false;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        mScroller.fling(0, mRect.top,
                0, (int) -velocityY,
                0, 0,
                0, mImageHeight - (int) (mViewHeight / mScale));
        return false;
    }

    @Override
    public void computeScroll() {
        if (mScroller.isFinished()) {
            return;
        }
        //true 当前滑动还没有结束
        if (mScroller.computeScrollOffset()) {
            mRect.top = mScroller.getCurrY();
            mRect.bottom = mRect.top + (int) (mViewHeight / mScale);
            invalidate();
        }
    }

    @Override
    public void onShowPress(MotionEvent event) {

    }

    @Override
    public void onLongPress(MotionEvent event) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return false;
    }

    public void configWithLocalImageUrl(String imageUrl) {
        InputStream inputStream = null;
        try {
            inputStream = mContext.getAssets().open(imageUrl);
            setImage(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void configWithImageUrl(String imageUrl) {
        downLoadImage(imageUrl);
    }

    //读取网络图片
    private void downLoadImage(final String imageUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(30000);
                    connection.connect();
                    if (connection.getResponseCode() == 200) {
                        mInputStream = connection.getInputStream();
                        Message message = new Message();
                        message.what = 0x123;
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setImage(InputStream is) {
        mOption.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, mOption);

        mImageWidth = mOption.outWidth;
        mImageHeight = mOption.outHeight;
        //开启复用内存
        mOption.inMutable = true;
        //设置格式，减少内存
        mOption.inPreferredConfig = Bitmap.Config.RGB_565;
        mOption.inJustDecodeBounds = false;

        //创建一个区域解码器
        try {
            mInputStream = connection.getInputStream();
            mDecode = BitmapRegionDecoder.newInstance(mInputStream, false);
            //byte[] data = toByteArray(is);
            //mDecode = BitmapRegionDecoder.newInstance(data, 0, data.length, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //刷新
        requestLayout();
    }

    private byte[] toByteArray(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024*6];
        byte[] data = null;
        try {
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, n);
            }
            buffer = null;
            data = outputStream.toByteArray();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取测量的view的大小
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();

        mRect.top = 0;
        mRect.left = 0;
        mRect.right = mImageWidth;
        mScale = mViewWidth / (float) mImageWidth;
        mRect.bottom = (int) (mViewHeight / mScale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //如果没有解码器 说明还没有图片，不需要绘制
        if (null == mDecode) {
            return;
        }
        mOption.inBitmap = mBitmap;
        //通过解码器把图解码出来，只加载矩形区域的内容
        mBitmap = mDecode.decodeRegion(mRect, mOption);
        //把得到的矩形局域大小的图片通过缩放因子，缩放成控件大小
        Matrix matrix = new Matrix();
        matrix.setScale(mScale, mScale);
        canvas.drawBitmap(mBitmap, matrix, null);
    }

}















