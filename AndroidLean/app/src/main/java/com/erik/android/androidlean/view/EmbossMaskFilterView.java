package com.erik.android.androidlean.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.erik.android.androidlean.R;

public class EmbossMaskFilterView extends View {

    public EmbossMaskFilterView(Context context) {
        super(context);
    }

    public EmbossMaskFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EmbossMaskFilterView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //设置光源方向
        float[] direction = new float[]{1, 1, 3};
        //设置环境光亮度
        float light = 0.4f;
        //设置镜面反射系数
        float specular = 8;
        //模糊半径
        float blur = 3.0f;
        EmbossMaskFilter embossMaskFilter = new EmbossMaskFilter(direction, light, specular, blur);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(70);
        paint.setStrokeWidth(8);
        paint.setMaskFilter(embossMaskFilter);
        canvas.drawText("Hello world!", 50, 100, paint);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap, 150, 200, paint);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

}
