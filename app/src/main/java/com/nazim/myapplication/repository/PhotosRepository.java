package com.nazim.myapplication.repository;

import com.nazim.myapplication.api.PhotosService;
import com.nazim.myapplication.model.Photo;
import com.nazim.myapplication.storage.DataSource;
import java.util.List;
import rx.Observable;

/**
 * Repository to contient all calls regarding Photos.
 */
public class PhotosRepository {

    private final DataSource dataSource;

    public PhotosRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Get all photos
     * @return An observable containing the list of the Photos
     */
    public Observable<List<Photo>> getPhotos() {
        return dataSource.getPhotos();
    }
}
