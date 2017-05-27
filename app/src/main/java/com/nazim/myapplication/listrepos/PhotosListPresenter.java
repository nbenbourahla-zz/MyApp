package com.nazim.myapplication.listrepos;

import android.util.Log;
import com.nazim.myapplication.model.Photo;
import com.nazim.myapplication.repository.PhotosRepository;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class PhotosListPresenter {

    private final CompositeSubscription compositeSubscription;
    private PhotosRepository photosRepository;

    PhotosListPresenter(final PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
        compositeSubscription = new CompositeSubscription();
    }

    void loadData(final PhotosListAdapter repoListAdapter) {
        Observable<List<Photo>> repoObservable = photosRepository.getPhotos();
        compositeSubscription.add(repoObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<Photo>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.e("RepoListActivity", "Error", e);
                }

                @Override
                public void onNext(List<Photo> repos) {
                    repoListAdapter.setData(repos);
                    repoListAdapter.notifyDataSetChanged();
                }
            }));
    }

    void unbind() {
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}
