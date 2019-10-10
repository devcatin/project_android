package com.erik.android.androidlean.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LongImageView extends View {

    private int width, height;

    //需要绘制的Bitmap
    private Bitmap bitmap;

    /**
     * 需要绘制的图片的区域
     */
    private Rect srcRect;

    /**
     * 绘制的区域
     */
    private RectF dstRectF;

    /**
     * 画笔
     */
    private Paint paint;


    /**
     * 是否需要滑动
     */
    private boolean isNeedSlide;

    /**
     * 已经滑动过的距离
     */
    private float slideLength;


    /**
     * 绘制的Bitmap
     */
    private Bitmap drawBitmap;

    {
        srcRect = new Rect();
        dstRectF = new RectF();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1.0f);
    }

    public LongImageView(Context context) {
        super(context);
    }

    public LongImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LongImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LongImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 设置Bitmap
     *
     * @param bitmap
     *     需要绘制的Bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        width = getPaddingLeft() + getPaddingRight() + specSize;
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        height = getPaddingTop() + getPaddingBottom() + specSize;
        if (drawBitmap == null) {
            drawBitmap = resizeImage(bitmap, width);
            if (drawBitmap.getHeight() > 1.5 * height) {
                //需要滑动
                setNeedSlide(true);
            } else {
                //不需要滑动
                setNeedSlide(false);
                srcRect.left = 0;
                srcRect.top = 0;
                srcRect.right = drawBitmap.getWidth();
                srcRect.bottom = drawBitmap.getHeight();
                if (drawBitmap.getHeight() > height) {
                    drawBitmap = resizeImageH(drawBitmap, height - 20);
                } else {
                    float space = (height - drawBitmap.getHeight());
                    dstRectF.left = 0;
                    dstRectF.top = space;
                    dstRectF.right = width;
                    dstRectF.bottom = height - space;
                }
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null != drawBitmap) {
            canvas.drawBitmap(drawBitmap, (width - drawBitmap.getWidth()) / 2, slideLength, paint);
        }
    }

    /**
     * 设置是否需要滑动
     *
     * @param needSlide
     *     true or false
     */
    public void setNeedSlide(boolean needSlide) {
        isNeedSlide = needSlide;
    }

    /**
     * 触摸操作的坐标
     */
    private float lastX;
    private float lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isNeedSlide) {
            return super.onTouchEvent(event);
        }
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //按下
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                if (moveX - lastX > 50) {
                    //判断为左右滑动
                    return super.onTouchEvent(event);
                }
                float moveY = event.getY();
                float distance = moveY - lastY;
                lastY = moveY;
                slideLength += distance;
                if (slideLength >= 0) {
                    slideLength = 0;
                }
                if (slideLength <= (-1) * (drawBitmap.getHeight() - height)) {
                    slideLength = (-1) * (drawBitmap.getHeight() - height);
                }
                postInvalidate();
                break;
            default:
                break;
        }
        return true;
    }

    public Bitmap resizeImage(Bitmap bitmap, int w) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) w) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);
        return Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);
    }

    public Bitmap resizeImageH(Bitmap bitmap, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) h) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);
        return Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);
    }

    //读取网络图片
    public void downLoadImage(final String imageUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(30000);
                    connection.connect();
                    if (connection.getResponseCode() == 200) {
                        //mInputStream = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                        drawBitmap = bitmap;
                        setBitmap(bitmap);
                        invalidate();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
