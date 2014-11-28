package com.app.qbik.qbik;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.app.qbik.qbik.models.Usuario;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.apache.http.Header;

/**
 * Created by Omar on 27/11/2014.
 */
public class Statics {

    public static String BASE_URL = "http://108.61.205.173/";

    public static Usuario currentUser;

    public static void logIn(final Usuario usuario){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", usuario.email);
        params.add("password", usuario.password);

        currentUser = usuario;

//        client.post(String.format("%s%s", BASE_URL, "secure/login"), params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                //TODO: Agregar funcionalidad mil
//
//            }
//
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//
//            }
//        });
    }

    public static void logOut(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(String.format("%s%s", BASE_URL, "secure/logout"), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                currentUser = null;
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public static boolean loggedId(){
        return currentUser != null;
    }


    public static void imageLoaderMemoryCache(Context context, final ImageView img, final int failImgID, String url)
    {
        ImageLoader imageLoader=ImageLoader.getInstance();
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(100, 100) // max width, max height
                .threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY+1)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .build();
        imageLoader.init(config);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.logo_blak)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .delayBeforeLoading(1)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();

        imageLoader.displayImage(url, img, options, new ImageLoadingListener()
        {
            @Override
            public void onLoadingStarted(String url, View view)
            {img.setImageResource(failImgID);}
            @Override
            public void onLoadingFailed(String url, View view, FailReason failReason)
            {img.setImageResource(failImgID);}
            @Override
            public void onLoadingComplete(String url, View view, Bitmap loadedImage)
            {}
            @Override
            public void onLoadingCancelled(String url, View view)
            {}
        });
    }

}
