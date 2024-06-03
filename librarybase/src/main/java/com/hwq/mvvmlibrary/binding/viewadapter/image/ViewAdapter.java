package com.hwq.mvvmlibrary.binding.viewadapter.image;


import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;
import android.view.RoundedCorner;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hwq.mvvmlibrary.base.BaseApplication;
import com.hwq.mvvmlibrary.widget.GlideRoundTransform;

/**
 * Created by king on 2018.12.21
 */
public final class ViewAdapter {
    @BindingAdapter(value = {"url","uri", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, Uri uri, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(placeholderRes) //预加载图片
                    .error(placeholderRes) //加载失败图片
                    .priority(Priority.HIGH) //优先级
                    .diskCacheStrategy(DiskCacheStrategy.ALL); //缓存
            Glide.with(imageView.getContext()).load(url).apply(options).into(imageView);
        }else if(null!=uri){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(placeholderRes) //预加载图片
                    .error(placeholderRes) //加载失败图片
                    .priority(Priority.HIGH) //优先级
                    .diskCacheStrategy(DiskCacheStrategy.ALL); //缓存
            Glide.with(imageView.getContext()).load(uri).apply(options).into(imageView);
        }
    }

    @BindingAdapter(value = {"src"}, requireAll = false)
    public static void setImageUri(ImageView imageView, int sec) {
        imageView.setImageDrawable(BaseApplication.getInstance().getDrawable(sec));
    }

    @BindingAdapter(value = {"urlRadius", "placeholderRes"}, requireAll = false)
    public static void urlRadius(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(placeholderRes) //预加载图片
                    .error(placeholderRes) //加载失败图片
                    .priority(Priority.HIGH) //优先级
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存
                    .transform(new CircleTransformation());
            Glide.with(imageView.getContext()).load(url).apply(options).into(imageView);

        }
    }
    @BindingAdapter(value = {"urlWithRadius", "placeholderRes","glideRadius"}, requireAll = false)
    public static void urlRadius(ImageView imageView, String url, int placeholderRes,int glideRadius) {
        if (!TextUtils.isEmpty(url)) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(placeholderRes) //预加载图片
                    .error(placeholderRes) //加载失败图片
                    .priority(Priority.HIGH) //优先级
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存
                    .transform(new RoundedCorners(glideRadius)); //圆角
            Glide.with(imageView.getContext()).load(url).apply(options).into(imageView);

        }
    }
    @BindingAdapter(value = {"drawable"}, requireAll = false)
    public static void setDrawable(View imageView, int url) {
        if(url!=0){
            imageView.setBackground(imageView.getContext().getResources().getDrawable(url));
        }else {
            imageView.setBackground(null);
        }
    }

}

