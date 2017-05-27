package com.nazim.myapplication.repository;

import com.nazim.myapplication.api.PhotosService;
import com.nazim.myapplication.model.Photo;
import java.util.List;
import rx.Observable;

public class PhotosRepository {

    private final PhotosService photosService;

    public PhotosRepository(PhotosService photosService) {
        this.photosService = photosService;
    }

    public Observable<List<Photo>> getPhotos() {
        return photosService.getPhotos();
    }
}
