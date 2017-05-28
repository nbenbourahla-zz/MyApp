package com.nazim.myapplication.listphotos;

import android.util.Log;
import com.nazim.myapplication.api.Cache;
import com.nazim.myapplication.model.Photo;
import com.nazim.myapplication.repository.PhotosRepository;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

class PhotosListPresenter {

    static final String TAG = "PhotosListPresenter";
    private final CompositeSubscription compositeSubscription;
    private PhotosRepository photosRepository;
    private final Cache cache;

    @Inject
    PhotosListPresenter(final PhotosRepository photosRepository, Cache cache) {
        this.photosRepository = photosRepository;
        this.cache = cache;
        compositeSubscription = new CompositeSubscription();
    }

    void loadData(final PhotosListAdapter repoListAdapter) {
        Observable<List<Photo>> repoObservable = photosRepository.getPhotos();
        compositeSubscription.add(repoObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(photoList -> {
                    //Add interceptor to save data to cache
                    cache.putInCache(photoList);
                    repoListAdapter.setData(photoList);
                    repoListAdapter.notifyDataSetChanged();
                }, e -> Log.e(TAG, "Error when getting data", e)
            ));
    }

    void unbind() {
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}
