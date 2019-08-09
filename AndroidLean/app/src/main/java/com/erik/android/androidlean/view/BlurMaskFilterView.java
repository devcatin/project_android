package com.erik.android.androidlean.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class BlurMaskFilterView extends View {

    private Typeface typeface;

    public BlurMaskFilterView(Context context) {
        super(context);
        typeface = Typeface.createFromAsset(context.getAssets(), "font/Monaco.ttf");
    }

    public BlurMaskFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        typeface = Typeface.createFromAsset(context.getAssets(), "font/Monaco.ttf");
    }

    public BlurMaskFilterView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        typeface = Typeface.createFromAsset(context.getAssets(), "font/Monaco.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BlurMaskFilter maskFilter = null;
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setTextSize(68);
//        paint.setTypeface(typeface);
//        paint.setStrokeWidth(5);
        /*maskFilter = new BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL);
        paint.setMaskFilter(maskFilter);
        canvas.drawText("Hello world!", 100, 100, paint);
        maskFilter = new BlurMaskFilter(10f, BlurMaskFilter.Blur.OUTER);
        paint.setMaskFilter(maskFilter);
        canvas.drawText("Hello world!", 100, 200, paint);
        maskFilter = new BlurMaskFilter(10f, BlurMaskFilter.Blur.SOLID);
        paint.setMaskFilter(maskFilter);
        canvas.drawText("Hello world!", 100, 300, paint);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);*/

//        Rect rect = new Rect(0, 0, 150, 150);
//        canvas.translate(300, 300);
//        for (int index = 0; index < 36; index++) {
//            canvas.rotate(10);
//            canvas.drawRect(rect, paint);
//        }

        Paint mPaint = new Paint();
        canvas.drawColor(Color.BLUE);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(new Rect(0, 0, 50, 50), mPaint);

        canvas.save();

        canvas.translate(100, 100);
        mPaint.setColor(Color.RED);
        canvas.drawRect(new Rect(0, 0, 50, 50), mPaint);

        canvas.restore();
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(new Rect(0, 0, 50, 50), mPaint);

    }

}
