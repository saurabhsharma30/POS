package com.walkover.pos.utils;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.walkover.pos.R;

/**
 * Created by saurabh on 31/1/17.
 */

public class Constants {


    public static void LoadImage(Context context, ImageView imageView, String imageUrl) {


        ImageLoader imageLoader = ImageLoader.getInstance();

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.drawable.progress)
                .showImageOnFail(R.drawable.progress)
                .showImageOnLoading(R.drawable.progress).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).defaultDisplayImageOptions(options)
                .memoryCache(new WeakMemoryCache()).diskCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);


        imageLoader.displayImage(imageUrl, imageView, options);
    }
}
