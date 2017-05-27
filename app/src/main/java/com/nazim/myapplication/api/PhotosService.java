package com.nazim.myapplication.api;

import com.nazim.myapplication.model.Photo;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface PhotosService {
    @GET("photos")
    Observable<List<Photo>> getPhotos();
}
