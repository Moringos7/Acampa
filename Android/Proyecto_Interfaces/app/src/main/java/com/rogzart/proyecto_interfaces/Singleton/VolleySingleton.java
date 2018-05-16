package com.rogzart.proyecto_interfaces.Singleton;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue request;
    //private ImageLoader mImageLoader;
    private static Context context;

    private VolleySingleton(Context context) {
        this.context = context;
        request = getRequestQueue();
        /*mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });*/
    }
    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (request == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            request = Volley.newRequestQueue(context.getApplicationContext());
        }
        return request;
    }

    public <T> void addToRequestQueue(Request<T> req) {

        getRequestQueue().add(req);
    }

    /*public ImageLoader getImageLoader() {
        return mImageLoader;
    }*/
}
