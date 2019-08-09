package com.erik.android.androidlean.tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

public class ImageHelper {

    /**
     * 该方法用来处理图像，根据色调，饱和度，亮度来调节
     *
     * @param bitmap:要处理的图像
     * @param hue:色调
     * @param saturation:饱和度
     * @param lum:亮度
     *
     */
    public static Bitmap handleImageEffect(Bitmap bitmap, float hue, float saturation, float lum) {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setRotate(0, hue);
        matrix.setRotate(1, hue);
        matrix.setRotate(2, hue);

        ColorMatrix matrix1 = new ColorMatrix();
        matrix1.setSaturation(saturation);

        ColorMatrix matrix2 = new ColorMatrix();
        matrix2.setScale(lum, lum, lum, 1);

        ColorMatrix matrix3 = new ColorMatrix();
        matrix3.postConcat(matrix);
        matrix3.postConcat(matrix1);
        matrix3.postConcat(matrix2);

        paint.setColorFilter(new ColorMatrixColorFilter(matrix3));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bitmap1;
    }

    public Bitmap processImage(Bitmap bp, int mul, int add) {
        Bitmap bitmap = Bitmap.createBitmap(bp.getWidth(),bp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new LightingColorFilter(mul,add));
        canvas.drawBitmap(bp,0,0,paint);
        return bitmap;
    }

}
