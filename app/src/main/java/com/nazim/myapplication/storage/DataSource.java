package com.nazim.myapplication.storage;

import com.nazim.myapplication.AppApplication;
import com.nazim.myapplication.api.Cache;
import com.nazim.myapplication.api.PhotosService;
import com.nazim.myapplication.common.Helper;
import com.nazim.myapplication.model.Photo;
import java.util.List;
import rx.Observable;

/**
 * Used to get data from sources (WS, cache ...)
 */
public class DataSource {
    private final Cache cache;
    private final PhotosService photosService;

    public DataSource(Cache cache, PhotosService photosService) {
        this.cache = cache;
        this.photosService = photosService;
    }

    public Observable<List<Photo>> getPhotos() {
        if (Helper.isNetworkAvailable(AppApplication.getContext())) {
            return photosService.getPhotos();
        }
        return Observable.just(cache.getFromCache());
    }
}
