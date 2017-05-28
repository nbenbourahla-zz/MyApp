package com.nazim.myapplication.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.disklrucache.DiskLruCache;
import com.nazim.myapplication.AppApplication;
import com.nazim.myapplication.model.Photo;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to manage the cache in the app
 */
public class Cache {
    private static final String CACHE_KEY = "photoscache";
    private static final String TAG = "Cache";
    private static final int INDEX = 0;
    private static final int APP_VERSION = 1;
    private static final int VALUE_COUNT = 1;
    private final long CACHE_SIZE = 5 * 1024 * 1024; // 5MiB
    private DiskLruCache cache;

    /**
     * Init the cache
     */
    public Cache() {
        try {
            cache = DiskLruCache.open(AppApplication.getContext().getCacheDir(), APP_VERSION, VALUE_COUNT, CACHE_SIZE);
        } catch (IOException e) {
            Log.e(TAG, "Error when init the cache", e);
        }
    }

    /**
     * Save the photo list in the cache
     * @param photoList a non null photo list.
     */
    public synchronized void putInCache(@NonNull List<Photo> photoList) {
        try {
            DiskLruCache.Editor editor = cache.edit(CACHE_KEY);
            editor.set(INDEX, PhotosListToString(photoList));
            editor.commit();
        } catch (IOException e) {
            Log.e(TAG, "Error when writing in the cache", e);
        }
    }

    /**
     * Convert a photo list to json string
     * @param photoList a non null photo list.
     */
    private String PhotosListToString(List<Photo> photoList) {
        return new Gson().toJson(photoList);
    }

    /**
     * Get data from cache.
     * @return the asked data or null (if data not found or an exception occured).
     */
    @Nullable
    public synchronized List<Photo> getFromCache() {
        try {
            return StringToPhotoList(cache.get(CACHE_KEY).getString(INDEX));
        } catch (IOException e) {
            Log.e(TAG, "Error when reading from the cache", e);
        }
        return null;
    }

    /**
     * Convert a photo json to a list of Photos
     */
    @Nullable
    private List<Photo> StringToPhotoList(String photosJson) {
        Type listType = new TypeToken<ArrayList<Photo>>() {
        }.getType();
        return new Gson().fromJson(photosJson, listType);
    }
}
