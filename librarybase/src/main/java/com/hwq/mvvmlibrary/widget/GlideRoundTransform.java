package com.hwq.mvvmlibrary.widget;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

/**
 * glide圆角
 */

public class GlideRoundTransform extends BitmapTransformation {
    private static float radius = 0f;
    private static float Corners=0;

    public GlideRoundTransform() {
        this(4,0);
    }

    public GlideRoundTransform(int dp,int Corners) {
        super();
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        this.Corners=Corners;
    }


    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        //变换的时候裁切
        Bitmap bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
        return roundCrop(pool, bitmap);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }


    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) {
            return null;
        }
        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        //0f, 0f, source.getWidth(), source.getHeight()
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        //4个角   0,100f，source.getWidth(), source.getHeight()
        RectF rectRound = new RectF(Corners, Corners,Corners,Corners );
        canvas.drawRect(rectRound, paint);
        return result;
    }
}

